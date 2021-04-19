package com.laporanpengaduan.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laporanpengaduan.main.entities.AdminUser;
import com.laporanpengaduan.main.repositories.AdminUserRepository;

@Service
public class ModelAdminUser implements ModelAdminUserInterface{

	@Autowired
	AdminUserRepository adminRepository;
	
	@Override
	public List<AdminUser> getAllAdmin() {
		return (List<AdminUser>) this.adminRepository.findAll();
	}

	@Override
	public AdminUser addAdmin(AdminUser admin) {
		return this.adminRepository.save(admin);
	}

	@Override
	public AdminUser getAdminById(String id) {
		return this.adminRepository.findById(Long.parseLong(id)).get();
	}

	@Override
	public void deleteAdmin(String id) {
		this.adminRepository.deleteById(Long.parseLong(id));
	}

	
	
}
