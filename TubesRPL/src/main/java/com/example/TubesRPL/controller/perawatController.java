package com.example.TubesRPL.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.repository.perawatRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/perawat")
public class perawatController {

    @Autowired
    private perawatRepo repo;

    @GetMapping("/home")
    public String homepage() {
        return "perawat/homepage";
    }

    @GetMapping("/lihat-pasien")
    public String lihatPasien(Model model, HttpSession session) {

        model.addAttribute("results", repo.lihatPasien());

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "general/login";
        }

        return "perawat/lihat_list_pasien";
    }

    @GetMapping("/rekam")
    public String rekamPasien(@RequestParam("nik") String nik, @RequestParam("idPendaftaran") int idPendaftaran,
            HttpSession session) {

        session.setAttribute("nik", nik);
        session.setAttribute("idPendaftaran", idPendaftaran);

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "general/login";
        }

        return "perawat/rekam_informasi_pasien";
    }

    @PostMapping("/rekam/submit")
    public String rekamPasienSubmit(@RequestParam("tekananDarah") String tekananDarah,
            @RequestParam("tinggiBadan") double tinggiBadan,
            @RequestParam("beratBadan") double beratBadan,
            @RequestParam("suhuBadan") double suhuBadan,
            @RequestParam("keluhan") String keluhan,
            HttpSession session) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "general/login";
        }

        int idPendaftaran = (int) session.getAttribute("idPendaftaran");

        repo.sumbitDataPasien(idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan, ld.getNip());

        return "perawat/lihat_list_pasien";
    }

    @GetMapping("/upload")
    public String uploadPasien(@RequestParam("nik") String nik, @RequestParam("idPendaftaran") int idPendaftaran,
            HttpSession session) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "general/login";
        }

        session.setAttribute("nik", nik);
        session.setAttribute("idPendaftaran", idPendaftaran);

        return "perawat/upload_lab";
    }

    @PostMapping("/upload/submit")
    public String uploadPasienSubmit(@RequestParam("tanggalPemeriksaan") String tanggal,
            @RequestParam("inputFile") MultipartFile file,
            HttpSession session) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "general/login";
        }

        int idPendaftaran = (int) session.getAttribute("idPendaftaran");

        repo.sumbitLabPasien(idPendaftaran, Date.valueOf(LocalDate.parse(tanggal)), file);

        return "perawat/lihat_list_pasien";
    }

}
