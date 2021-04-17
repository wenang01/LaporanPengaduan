package com.laporanpengaduan.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.laporanpengaduan.main.entities.Admin;
import com.laporanpengaduan.main.services.ModelAdmin;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class AdminPage {

	@Autowired
	ModelAdmin modelAdmin;
	
	@GetMapping("/admin/view")
	public String viewIndexAdmin(Model model) {
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		model.addAttribute("active", 1);
		model.addAttribute("admin", "Admin Page");
		
		return "view_admin";
	}
	
	@GetMapping("/admin/add")
	public String viewAddAdmin(Model model) {
		model.addAttribute("admin", new Admin());
		return "add_admin";
	}
	
	@PostMapping("/admin/view")
	public String AddAdmin(@ModelAttribute Admin admin, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String palinPassword = admin.getPassword();
		String encodedPassword = passwordEncoder.encode(palinPassword);
		admin.setPassword(encodedPassword);
		
		this.modelAdmin.addAdmin(admin);
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		
		return "redirect:/admin/view";
	}
	
	@GetMapping("/admin/update/{id}")
	public String viewUpdateAdmin(@PathVariable String id, Model model) {
		Admin admin = modelAdmin.getAdminById(id);
		model.addAttribute("admin", admin);
		
		return "add_admin";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteAdmin(@PathVariable String id, Model model) {
		this.modelAdmin.deleteAdmin(id);
		model.addAttribute("listAdmin", modelAdmin.getAllAdmin());
		
		return "redirect:/admin/view";
	}
	
}
