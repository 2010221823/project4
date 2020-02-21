package com.accp.project4.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.project4.biz.EmployeeBiz;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/c/employee")
public class EmployeeAction {
	@Resource
	private EmployeeBiz biz;

	@RequestMapping("/login")
	public String queryLogin(HttpSession session, Integer id, String pwd) {
		session.setAttribute("user", biz.findLogin(id, pwd));
		return "redirect:/new";
	}
	@GetMapping("/loginOut")
	@ResponseBody
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
		return "ok";
	}
}
