package com.laporanpengaduan.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.laporanpengaduan.main.entities.Pengaduan;

public interface PengaduanRepository extends CrudRepository<Pengaduan, Long>{

	@Query(value = "select * from pengaduan where status is not null", nativeQuery = true)
	public List<Pengaduan> findStatusResponse();
	
	@Query(value = "select * from pengaduan where status is null", nativeQuery = true)
	public List<Pengaduan> findStatusPending();
	
}
