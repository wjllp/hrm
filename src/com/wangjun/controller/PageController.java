package com.wangjun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController{

	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName,HttpServletRequest request){
		// 动态跳转页面
		if ("logout".equals(formName)){
			request.getSession().invalidate();
			return "loginForm";
		}
		return formName;
	}
}

