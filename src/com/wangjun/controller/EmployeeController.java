package com.wangjun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.DeptInf;
import com.wangjun.pojo.EmployeeInf;
import com.wangjun.pojo.JobInf;
import com.wangjun.service.DeptService;
import com.wangjun.service.EmployeeService;
import com.wangjun.service.JobService;
import com.wangjun.util.tag.PageModel;

@Controller
public class EmployeeController {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, @ModelAttribute EmployeeInf employee,
			Model model) {
		this.genericAssociation(job_id, dept_id, employee);
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<JobInf> jobs = jobService.findAllJob();
		List<DeptInf> depts = deptService.findAllDept();
		List<EmployeeInf> employees = employeeService.findEmployee(employee, pageModel);
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "employee/employee";
	}

	@RequestMapping(value = "/employee/addEmployee")
	public ModelAndView addEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute EmployeeInf employee,
			ModelAndView mv) {
		if (flag.equals("1")) {
			List<JobInf> jobs = jobService.findAllJob();
			List<DeptInf> depts = deptService.findAllDept();
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.setViewName("employee/showAddEmployee");
		} else {
			this.genericAssociation(job_id, dept_id, employee);
			employeeService.addEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		return mv;

	}

	@RequestMapping(value = "/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			employeeService.removeEmployeeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/employee/selectEmployee");
		return mv;
	}

	@RequestMapping(value = "/employee/updateEmployee")
	public ModelAndView updateEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute EmployeeInf employee,
			ModelAndView mv) {
		if (flag.equals("1")) {
			EmployeeInf target = employeeService.findEmployeeById(employee.getId());
			List<JobInf> jobs = jobService.findAllJob();
			List<DeptInf> depts = deptService.findAllDept();
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.addObject("employee", target);
			mv.setViewName("employee/showUpdateEmployee");
		} else {
			this.genericAssociation(job_id, dept_id, employee);
			System.out.println("updateEmployee -->> " + employee);
			employeeService.modifyEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		return mv;
	}

	private void genericAssociation(Integer job_id, Integer dept_id, EmployeeInf employee) {
		if (job_id != null) {
			JobInf job = jobService.findJobById(job_id);
			employee.setJob(job);
		}
		if (dept_id != null) {
			DeptInf dept = deptService.findDeptById(dept_id);
			employee.setDept(dept);
		}
	}

}
