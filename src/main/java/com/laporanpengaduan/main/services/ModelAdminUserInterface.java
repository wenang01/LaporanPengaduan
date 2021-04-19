package com.laporanpengaduan.main.services;

import java.util.List;

import com.laporanpengaduan.main.entities.AdminUser;

public interface ModelAdminUserInterface {

	public List<AdminUser> getAllAdmin();
	
	public AdminUser addAdmin(AdminUser admin);
	public AdminUser getAdminById(String id);
	public void deleteAdmin(String id);
	
}
