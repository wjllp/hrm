package com.wangjun.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.NoticeInf;
import com.wangjun.pojo.UserInf;
import com.wangjun.service.NoticeService;
import com.wangjun.service.UserService;
import com.wangjun.util.common.HrmConstants;
import com.wangjun.util.tag.PageModel;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/notice/selectNotice")
	public String selectNotice(Model model, Integer pageIndex, @ModelAttribute NoticeInf notice) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<NoticeInf> notices = noticeService.findNotice(notice, pageModel);
		for (NoticeInf n : notices){
			n.setUser(userService.findUserById(n.getUserId()));
		}
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";

	}

	@RequestMapping(value = "/notice/previewNotice")
	public String previewNotice(Integer id, Model model) {
		NoticeInf notice = noticeService.findNoticeById(id);
		model.addAttribute("notice", notice);
		return "notice/previewNotice";
	}

	@RequestMapping(value = "/notice/removeNotice")
	public ModelAndView removeNotice(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			noticeService.removeNoticeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/notice/selectNotice");
		return mv;
	}

	@RequestMapping(value = "/notice/addNotice")
	public ModelAndView addNotice(String flag, @ModelAttribute NoticeInf notice, ModelAndView mv, HttpSession session) {
		if (flag.equals("1")) {
			mv.setViewName("notice/showAddNotice");
		} else {
			UserInf user = (UserInf) session.getAttribute(HrmConstants.USER_SESSION);
			notice.setUserId(user.getId());
			noticeService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}

	@RequestMapping(value = "/notice/updateNotice")
	public ModelAndView updateNotice(String flag, @ModelAttribute NoticeInf notice, ModelAndView mv,
			HttpSession session) {
		if (flag.equals("1")) {
			NoticeInf target = noticeService.findNoticeById(notice.getId());
			mv.addObject("notice", target);
			mv.setViewName("notice/showUpdateNotice");
		} else {
			noticeService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}

}
