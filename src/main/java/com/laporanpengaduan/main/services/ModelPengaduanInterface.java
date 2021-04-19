package com.laporanpengaduan.main.services;

import java.util.List;

import com.laporanpengaduan.main.entities.Pengaduan;

public interface ModelPengaduanInterface{

	public List<Pengaduan> getAllPengaduan();
	public Pengaduan addPengaduan(Pengaduan pengaduan);
	public void deletePengaduan(String id);
	public Pengaduan cariPengaduan(String id);
	public List<Pengaduan> getStatusResponse();
	public List<Pengaduan> getStatusPending();
	
}
