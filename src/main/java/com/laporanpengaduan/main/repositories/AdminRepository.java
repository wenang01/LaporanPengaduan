package com.laporanpengaduan.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.laporanpengaduan.main.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	public Admin findByUsername(String username);
	
}
