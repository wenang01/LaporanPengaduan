package com.laporanpengaduan.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

	
	@GetMapping("/")
	public String viewHomePage(Model model) {

		return "index";
		
	}
	
	
	@GetMapping("/login")
	public String loginPage(Model model) {

		return "login";
		
	}

}