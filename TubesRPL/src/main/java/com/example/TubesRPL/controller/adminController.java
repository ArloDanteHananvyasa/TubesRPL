package com.example.TubesRPL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/home")
    public String showHomepageAdmin() {
        return "admin/Admin_homepage";
    }

    @GetMapping("/lihat-pasien")
    public String showPasienAdmin() {
        return "admin/Admin_Konfirmasi_Booking";
    }

    @GetMapping("/atur-jadwal")
    public String showJadwal(@RequestParam("nik") String nik, HttpSession session, Model model) {
        return "admin/atur_jadwal";
    }
}
