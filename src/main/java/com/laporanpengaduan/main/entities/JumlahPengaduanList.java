package com.laporanpengaduan.main.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JumlahPengaduanList {

	private List<Pengaduan> pengaduan;
	
}
