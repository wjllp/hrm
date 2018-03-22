package com.wangjun.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wangjun.pojo.UserInf;
import com.wangjun.service.UserService;
import com.wangjun.util.common.HrmConstants;
import com.wangjun.util.tag.PageModel;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("loginname") String loginname,@RequestParam("password") String password,HttpSession session,ModelAndView mv){
		UserInf user = userService.login(loginname, password);
		if(user != null){
			session.setAttribute(HrmConstants.USER_SESSION, user);
			mv.setViewName("redirect:/main");
		}else{
			mv.addObject("message", "登录名或密码错误!请重新输入");
			mv.setViewName("forward:/loginForm");
		}
		return mv;
	}
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,@ModelAttribute UserInf user,Model model){
		PageModel pageModel = new PageModel();
		if (pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<UserInf> users = userService.findUser(pageModel);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
	}
	
	@RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			userService.removeUserById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/user/selectUser");
		return mv;
	}
	
	@RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(String flag,@ModelAttribute UserInf user,ModelAndView mv){
		if(flag.equals("1")){
			UserInf target = userService.findUserById(user.getId());
			mv.addObject("user", target);
			mv.setViewName("user/showUpdateUser");
		}else{
			userService.modifyUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	

	@RequestMapping(value="/user/addUser")
	 public ModelAndView addUser(String flag,@ModelAttribute UserInf user,ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("user/showAddUser");
		}else{
			userService.addUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
}
