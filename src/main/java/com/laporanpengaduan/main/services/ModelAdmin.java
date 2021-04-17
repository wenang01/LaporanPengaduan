package com.laporanpengaduan.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laporanpengaduan.main.entities.Admin;
import com.laporanpengaduan.main.repositories.AdminRepository;

@Service
public class ModelAdmin implements ModelAdminInterface{

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public List<Admin> getAllAdmin() {
		return (List<Admin>) this.adminRepository.findAll();
	}

	@Override
	public Admin addAdmin(Admin admin) {
		return this.adminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(String id) {
		return this.adminRepository.findById(Long.parseLong(id)).get();
	}

	@Override
	public void deleteAdmin(String id) {
		this.adminRepository.deleteById(Long.parseLong(id));
	}

	
	
}
