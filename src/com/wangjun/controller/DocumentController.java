package com.wangjun.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.DocumentInf;
import com.wangjun.pojo.UserInf;
import com.wangjun.service.DocumentService;
import com.wangjun.service.UserService;
import com.wangjun.util.common.HrmConstants;
import com.wangjun.util.tag.PageModel;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private UserService userService;
	@RequestMapping(value = "/document/selectDocument")
	public String selectDocument(Model model, Integer pageIndex, @ModelAttribute DocumentInf document) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<DocumentInf> documents = documentService.findDocument(document, pageModel);
		for (DocumentInf d : documents){
			d.setUser(userService.findUserById(d.getUserId()));
		}
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";

	}

	@RequestMapping(value = "/document/addDocument")
	public ModelAndView addDocument(String flag, @ModelAttribute DocumentInf document, ModelAndView mv,
			HttpSession session) throws Exception {
		if (flag.equals("1")) {
			mv.setViewName("document/showAddDocument");
		} else {
			String path = session.getServletContext().getRealPath("/upload/");
			String fileName = document.getFile().getOriginalFilename();
			// 将上传文件保存到一个目标文件当中
			document.getFile().transferTo(new File(path + File.separator + fileName));
			document.setFilename(fileName);
			UserInf user = (UserInf) session.getAttribute(HrmConstants.USER_SESSION);
			document.setUserId(user.getId());
			documentService.addDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}

	@RequestMapping(value = "/document/removeDocument")
	public ModelAndView removeDocument(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			documentService.removeDocumentById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/document/selectDocument");
		return mv;
	}

	@RequestMapping(value = "/document/updateDocument")
	public ModelAndView updateDocument(String flag, @ModelAttribute DocumentInf document, ModelAndView mv) {
		if (flag.equals("1")) {
			DocumentInf target = documentService.findDocumentById(document.getId());
			mv.addObject("document", target);
			mv.setViewName("document/showUpdateDocument");
		} else {
			documentService.modifyDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}

	@RequestMapping(value = "/document/downLoad")
	public ResponseEntity<byte[]> downLoad(Integer id, HttpSession session) throws Exception {
		DocumentInf target = documentService.findDocumentById(id);
		String fileName = target.getFilename();
		// 上传文件路径
		String path = session.getServletContext().getRealPath("/upload/");
		// 获得要下载文件的File对象
		File file = new File(path + File.separator + fileName);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		// 通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFielName);
		// application/octet-stream ： 二进制流数据（最常见的文件下载）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 HttpStatus.CREATED
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
