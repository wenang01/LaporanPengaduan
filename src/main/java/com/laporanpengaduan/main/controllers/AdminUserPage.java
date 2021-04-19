package com.laporanpengaduan.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.laporanpengaduan.main.entities.AdminUser;
import com.laporanpengaduan.main.services.ModelAdminUser;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class AdminUserPage {

	@Autowired
	ModelAdminUser modelAdmin;
	
	@GetMapping("/adminuser/view")
	public String viewIndexAdmin(Model model) {
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		model.addAttribute("active", 1);
		model.addAttribute("admin", "Dashboard");
		
		return "view_admin";
	}
	
	@GetMapping("/adminuser/add")
	public String viewAddAdmin(Model model) {
		model.addAttribute("admin", new AdminUser());
		return "add_admin";
	}
	
	@PostMapping("/adminuser/view")
	public String AddAdmin(@ModelAttribute AdminUser admin, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String palinPassword = admin.getPassword();
		String encodedPassword = passwordEncoder.encode(palinPassword);
		admin.setPassword(encodedPassword);
		
		this.modelAdmin.addAdmin(admin);
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		
		return "redirect:/adminuser/view";
	}
	
	@GetMapping("/adminuser/update/{id}")
	public String viewUpdateAdmin(@PathVariable String id, Model model) {
		AdminUser admin = modelAdmin.getAdminById(id);
		model.addAttribute("admin", admin);
		
		return "add_admin";
	}
	
	@GetMapping("/adminuser/delete/{id}")
	public String deleteAdmin(@PathVariable String id, Model model) {
		this.modelAdmin.deleteAdmin(id);
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		
		return "redirect:/adminuser/view";
	}
	
}
