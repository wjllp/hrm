package com.wangjun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.JobInf;
import com.wangjun.service.JobService;
import com.wangjun.util.tag.PageModel;

@Controller
public class JobController {

	@Autowired
	private JobService jobService;

	@RequestMapping(value = "/job/selectJob")
	public String selectJob(Model model, Integer pageIndex, @ModelAttribute JobInf job) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<JobInf> jobs = jobService.findJob(job, pageModel);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		return "job/job";

	}

	@RequestMapping(value = "/job/removeJob")
	public ModelAndView removeJob(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			jobService.removeJobById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/job/selectJob");
		return mv;
	}

	@RequestMapping(value = "/job/addJob")
	public ModelAndView addJob(String flag, @ModelAttribute JobInf job, ModelAndView mv) {
		if (flag.equals("1")) {
			mv.setViewName("job/showAddJob");
		} else {
			jobService.addJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}

	@RequestMapping(value = "/job/updateJob")
	public ModelAndView updateDpet(String flag, @ModelAttribute JobInf job, ModelAndView mv) {
		if (flag.equals("1")) {
			JobInf target = jobService.findJobById(job.getId());
			mv.addObject("job", target);
			mv.setViewName("job/showUpdateJob");
		} else {
			jobService.modifyJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}
}
