package com.laporanpengaduan.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="pengaduan")
public class Pengaduan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPengaduan;
	
	private String nama;
	private String alamat;
	private String kejadian;
	private String fotoKejadian;
	private String status;
	private String keterangan;
	
}
