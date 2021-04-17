package com.laporanpengaduan.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.laporanpengaduan.main.entities.Admin;
import com.laporanpengaduan.main.repositories.AdminRepository;

@SpringBootApplication
public class LaporanPengaduanApplication implements CommandLineRunner{

	@Autowired
	private AdminRepository adminRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LaporanPengaduanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Admin admin = new Admin();
		admin.setIdAdmin(1);
		admin.setUsername("admin");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "12345";
		String encodedPassword = passwordEncoder.encode(plainPassword);
        admin.setPassword(encodedPassword);		
        
		admin.setRole("admin");
		
		
		adminRepository.save(admin);
	}

}
