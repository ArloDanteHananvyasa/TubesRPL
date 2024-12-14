package com.example.TubesRPL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dokter")
public class dokterController {

    @GetMapping("/home")
    public String showHomepageDokter() {
        return "dokter/dokter_Homepage";
    }

    @GetMapping("/lihat-pasien")
    public String showPasienDokter() {
        return "dokter/listPasienDokter";
    }

    @GetMapping("/diagnosa")
    public String showPasienDokter(@RequestParam("nik") String nik, HttpSession session, Model model) {
        return "dokter/listPasienDokter";
    }
}
