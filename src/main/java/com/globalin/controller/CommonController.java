package com.globalin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	private Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	//접근권한이 없는경우 처리하는 메소드
	//getMapping에 void면 /accessError가 jsp파일 이름
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		//Authentication : 사용자의 인증정보 확인 가능
		log.info("Access Denied : " + auth);
		
		model.addAttribute("msg","Access Denied");
		
		
	}
	@GetMapping("/loginpage")
	public void loginPage(String error, String logout, Model model) {
		log.info("error : "+error);
		log.info("logout : "+logout);
		
		if(error != null) {
			model.addAttribute("error","Login Error, Check your Account.");
		}
		if(logout != null) {
			model.addAttribute("logout","Logout!");
		}
		
	}
	
	@GetMapping("/customLogout")
	public void logoutGet() {
		log.info("cutom logout");
	}
	
	
}
