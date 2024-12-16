package com.example.TubesRPL.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.repository.generalRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class generalController {

    @Autowired
    private generalRepo repo;

    @GetMapping("/")
    public String showLanding() {
        return "general/LandingPage";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "general/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.setAttribute("loginData", null);

        return "redirect:/login";
    }

    @PostMapping("/login/submit")
    public String submitLogin(@RequestParam("nomorTelepon") String nohp, @RequestParam("password") String pass,
            HttpSession session, Model model) {

        loginData ld = repo.login(nohp, pass);

        if (ld == null) {
            model.addAttribute("error", "Akun kamu tidak ditemukan");

            return "general/login";
        }

        System.out.println(ld);

        session.setAttribute("loginData", ld);

        String res = "redirect:/" + ld.getRole() + "/home";

        return res;
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "pasien/registration";
    }

    @PostMapping("/signup/submit")
    public String submitSignup(
            @RequestParam("nik") String nik,
            @RequestParam("nama") String nama,
            @RequestParam("email") String email,
            @RequestParam("no_hp") String nomorTelepon,
            @RequestParam("password") String password,
            @RequestParam("jenis_kelamin") String gender,
            @RequestParam("ttl") Date tanggalLahir,
            Model model, HttpSession session) {

        repo.register(nik, nama, nomorTelepon, password, gender, tanggalLahir, email);

        return "redirect:/login";
    }
}
