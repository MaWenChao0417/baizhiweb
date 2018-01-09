package com.baizhi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.baizhi.service.BaiZhiPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baizhi.entity.BaiZhiUser;
import com.baizhi.service.BaiZhiUserService;

@Controller
@RequestMapping("/user")
public class BaiZhiUserController {
	@Autowired
	private BaiZhiUserService service;
	@Autowired
	private BaiZhiPageService baiZhiPageService;
	public BaiZhiUserService getService() {
		return service;
	}

	public void setService(BaiZhiUserService service) {
		this.service = service;
	}
	@RequestMapping("tologin")
	public String toupload(HttpServletRequest request){
		String status = (String) request.getSession().getAttribute("languageStatus");
		String content = baiZhiPageService.queryContentByPageNameAndStatus("login.jsp", status);
		String[] contents = content.split("_");
		request.setAttribute("contents",contents);
		return "login";
	}
	@RequestMapping("toreg")
	public String toreg(HttpServletRequest request){
		String status = (String) request.getSession().getAttribute("languageStatus");
		String content = baiZhiPageService.queryContentByPageNameAndStatus("reg.jsp", status);
		String[] contents = content.split("_");
		request.setAttribute("contents",contents);
		return "reg";
	}
	@RequestMapping("login")
	public String login(HttpSession session,String username,String password){
		BaiZhiUser user = service.login(username, password);
		session.setAttribute("user", user);
		return "/main";
	}
	@RequestMapping("reg")
	public String reg(HttpSession session,BaiZhiUser user){
		BaiZhiUser reg = service.reg(user);
		session.setAttribute("user", reg);
		return "/main";
	}
}
