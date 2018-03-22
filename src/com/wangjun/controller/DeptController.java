package com.wangjun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.DeptInf;
import com.wangjun.service.DeptService;
import com.wangjun.util.tag.PageModel;

@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping("/dept/selectDept")
	public String selectDept(Model model, Integer pageIndex, @ModelAttribute DeptInf dept) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<DeptInf> depts = deptService.findDept(dept, pageModel);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
	}

	@RequestMapping(value = "/dept/removeDept")
	public ModelAndView removeDept(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			deptService.removeDeptById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/dept/selectDept");
		return mv;
	}

	@RequestMapping(value = "/dept/addDept")
	public ModelAndView addDept(String flag, @ModelAttribute DeptInf dept, ModelAndView mv) {
		if (flag.equals("1")) {
			mv.setViewName("dept/showAddDept");
		} else {
			deptService.addDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}
		return mv;
	}

	@RequestMapping(value = "/dept/updateDept")
	public ModelAndView updateDpet(String flag, @ModelAttribute DeptInf dept, ModelAndView mv) {
		if (flag.equals("1")) {
			DeptInf target = deptService.findDeptById(dept.getId());
			mv.addObject("dept", target);
			mv.setViewName("dept/showUpdateDept");
		} else {
			deptService.modifyDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}
		return mv;
	}
}
