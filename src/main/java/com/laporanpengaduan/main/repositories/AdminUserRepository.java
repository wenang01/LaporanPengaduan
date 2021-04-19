package com.laporanpengaduan.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.laporanpengaduan.main.entities.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long>{

	public AdminUser findByUsername(String username);
	
}
