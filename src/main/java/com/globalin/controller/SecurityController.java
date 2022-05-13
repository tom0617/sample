package com.globalin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security/*")
public class SecurityController {
	
	private Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	//security/all : 로그인안해도가능
	@GetMapping("/all")
	public void doAll() {
		log.info("everyone can access this page");
	}		
	
	//security/member : 로그인한애들만가능
	@GetMapping("/member")
	public void doMember() {
		log.info("member can access this page");
	}
	
	//security/admin : 관리자만
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin page");
	}
}
