package com.laporanpengaduan.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.laporanpengaduan.main.entities.AdminUser;
import com.laporanpengaduan.main.repositories.AdminUserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AdminUserRepository adminRepoository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUser admin = adminRepoository.findByUsername(username);
		if(admin == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomUserDetails(admin);
	}

}
