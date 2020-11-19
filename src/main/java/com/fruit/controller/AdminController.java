package com.fruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fruit.model.Admins;
import com.fruit.model.Users;
import com.fruit.service.IAdminService;
import com.fruit.service.IUserService;
import com.fruit.utils.MD5Utils;

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IUserService  userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String name,String pass,String role,HttpServletRequest request){
		if(role.equals("1")) {
			Users user = userService.login(name, MD5Utils.passToMD5(pass));
			HttpSession session = request.getSession();
			if(user!=null){
				session.setAttribute("user", user);
				return "successUser";
			}
			return "fail";
			
		}
		Admins admin = adminService.login(name, pass);		
		HttpSession session = request.getSession();
		if(admin!=null){
			session.setAttribute("admin", admin);
			System.out.println("admin");
			return "successAdmin";
			
		}
		return "fail";
		
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return "forward:/view/index";
	}
	@RequestMapping("updatePass")
	@ResponseBody
	public String updatePass(String oldPass,String newPass,HttpServletRequest request){
		HttpSession session = request.getSession();
		Admins admin = (Admins) session.getAttribute("admin");
		if(oldPass.equals(admin.getAdminPass())){
			admin.setAdminPass(newPass);
			adminService.updateAdmin(admin);
			session.removeAttribute("admin");
			return "success";
		}
		return "fail";
	}
	
	
}
