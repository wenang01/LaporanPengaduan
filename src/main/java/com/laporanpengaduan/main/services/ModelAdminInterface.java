package com.laporanpengaduan.main.services;

import java.util.List;

import com.laporanpengaduan.main.entities.Admin;

public interface ModelAdminInterface {

	public List<Admin> getAllAdmin();
	
	public Admin addAdmin(Admin admin);
	public Admin getAdminById(String id);
	public void deleteAdmin(String id);
	
}
