package com.laporanpengaduan.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.laporanpengaduan.main.entities.Admin;
import com.laporanpengaduan.main.repositories.AdminRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepoository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepoository.findByUsername(username);
		if(admin == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomUserDetails(admin);
	}

}
