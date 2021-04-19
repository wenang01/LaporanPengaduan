package com.laporanpengaduan.main.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laporanpengaduan.main.entities.JumlahPengaduan;
import com.laporanpengaduan.main.entities.JumlahPengaduanList;
import com.laporanpengaduan.main.entities.Pengaduan;
import com.laporanpengaduan.main.repositories.PengaduanRepository;
import com.laporanpengaduan.main.services.ModelPengaduan;
import com.laporanpengaduan.main.utilities.FileUtility;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class PengaduanPage {

	@Autowired
	ModelPengaduan modelPengaduan;
	
	@Autowired
	PengaduanRepository pengaduanRepository;
	
	private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";
	
	@GetMapping("/pengaduan/view")
	public String viewIndexPengaduan(Model model) {
		model.addAttribute("listpengaduan",modelPengaduan.getAllPengaduan());
//		model.addAttribute("active",4);
		return "view_pengaduan";
	}
	
	@GetMapping("/pengaduan/add")
	public String viewAddPertanyaan(Model model) {
		model.addAttribute("pengaduan",new Pengaduan());
		return "add_pengaduan";
	}
	
	
	
	@PostMapping("/pengaduan/view")
	public String addPengaduan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Pengaduan pengaduan, Model model) throws IOException { {
		   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		  
	         
	        String uploadDir = "user-photos/" ;
	
	        FileUtility.saveFile(uploadDir, fileName, file);
	 
	        pengaduan.setFotoKejadian("/"+uploadDir + fileName);
	        this.modelPengaduan.addPengaduan(pengaduan);

		model.addAttribute("listpengaduan",modelPengaduan.getAllPengaduan());
		
		return "redirect:/pengaduan/view";
	  }
	}

	@GetMapping("/pengaduan/approve/{id}")
	public String viewApprovePengaduan(@PathVariable String id, Model model) {

		Pengaduan pengaduan = modelPengaduan.cariPengaduan(id);
		pengaduan.setStatus("Approved");
		modelPengaduan.save(pengaduan);
		
		return "redirect:/pengaduan/view";
	}
	
	@GetMapping("/pengaduan/reject/{id}")
	public String viewRejectPertanyaan(@PathVariable String id, Model model) {
		
		Pengaduan pengaduan = modelPengaduan.cariPengaduan(id);
		pengaduan.setStatus("Rejected");
		modelPengaduan.save(pengaduan);
		
		return "redirect:/pengaduan/view";
	}
	
	@GetMapping("/pengaduan/report/")
	public String exportPDF() {
		try {
			File file = ResourceUtils.getFile("classpath:templatelaporanpengaduan.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			List<Pengaduan> lstPengaduan = modelPengaduan.getAllPengaduan();
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstPengaduan);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy","Dedi");
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        String path = "D:\\templatelaporanpengaduan.pdf";
	        JasperExportManager.exportReportToPdfFile(jasperPrint,path);
	        

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/pengaduan/view";
	}
	
	@GetMapping("/dashboard")
	public String viewJumlahLaporan(@ModelAttribute JumlahPengaduanList jmlPengaduanList, Model model) {
		
		List<Pengaduan> lstPengaduan = new ArrayList<Pengaduan>();
		Pengaduan pengaduan = (Pengaduan) modelPengaduan.getAllPengaduan();
		
		JumlahPengaduan entPengaduan = new JumlahPengaduan();
		
		for (int i = 0; i < modelPengaduan.getAllPengaduan().size(); i++) {
			if(pengaduan.getStatus().isEmpty()) {
				int proses = entPengaduan.getDalamProses() + 1;
				entPengaduan.setDalamProses(proses);
			}else {
				int ditanggapi = entPengaduan.getDitanggapi() + 1;
				entPengaduan.setDitanggapi(ditanggapi);
			}
		}

		entPengaduan.setJumlahLaporan(jmlPengaduanList.getPengaduan().size());
		
		model.addAttribute("jmlhlaporan", entPengaduan);
		
		return "dashboard";
	}
	
	private JumlahPengaduan hitungPengaduan(JumlahPengaduanList jmlPengaduanList) {

		JumlahPengaduan entPengaduan = new JumlahPengaduan();
		
		for (int i = 0; i < jmlPengaduanList.getPengaduan().size(); i++) {
			if(jmlPengaduanList.getPengaduan().get(i).getStatus().isBlank()) {
				int proses = entPengaduan.getDalamProses() + 1;
				entPengaduan.setDalamProses(proses);
			}else {
				int ditanggapi = entPengaduan.getDitanggapi() + 1;
				entPengaduan.setDitanggapi(ditanggapi);
			}
		}

		entPengaduan.setJumlahLaporan(jmlPengaduanList.getPengaduan().size());
		
		return entPengaduan;
	}

}
