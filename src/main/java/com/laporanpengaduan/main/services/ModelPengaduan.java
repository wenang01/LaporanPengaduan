package com.laporanpengaduan.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laporanpengaduan.main.entities.Pengaduan;
import com.laporanpengaduan.main.repositories.PengaduanRepository;

@Service
public class ModelPengaduan implements ModelPengaduanInterface{

	@Autowired
	PengaduanRepository pengaduanRepository;
	
	@Override
	public List<Pengaduan> getAllPengaduan() {
		// TODO Auto-generated method stub
		return (List<Pengaduan>) this.pengaduanRepository.findAll();
	}

	@Override
	public Pengaduan addPengaduan(Pengaduan pengaduan) {
		// TODO Auto-generated method stub
		return this.pengaduanRepository.save(pengaduan);
	}

	@Override
	public void deletePengaduan(String id) {
		// TODO Auto-generated method stub
		this.pengaduanRepository.deleteById(Long.parseLong(id));
	}

	@Override
	public Pengaduan cariPengaduan(String id) {
		// TODO Auto-generated method stub
		return this.pengaduanRepository.findById(Long.parseLong(id)).get();
	}

	public void save(Pengaduan pengaduan) {
		// TODO Auto-generated method stub
		this.pengaduanRepository.save(pengaduan);
	}

	@Override
	public List<Pengaduan> getStatusResponse() {
		// TODO Auto-generated method stub
		return this.pengaduanRepository.findStatusResponse();
	}

	@Override
	public List<Pengaduan> getStatusPending() {
		// TODO Auto-generated method stub
		return this.pengaduanRepository.findStatusPending();
	}

}
