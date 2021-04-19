package com.laporanpengaduan.main.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.laporanpengaduan.main.entities.AdminUser;

public class CustomUserDetails implements UserDetails{

	private AdminUser admin;
	
	public CustomUserDetails(AdminUser admin) {
		this.admin = admin;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		switch(this.admin.getRole()) {
		
			case "admin":
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				break;
	
			case "user":
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				break;
		
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return admin.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFullName() {
		return admin.getUsername();
	}

}
