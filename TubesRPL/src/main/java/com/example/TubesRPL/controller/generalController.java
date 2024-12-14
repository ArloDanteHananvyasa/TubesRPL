package com.example.TubesRPL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/login/submit")
    public String submitLogin(@RequestParam("nomorTelepon") String nohp, @RequestParam("password") String pass,
            HttpSession session) {

        loginData ld = repo.login(nohp, pass);

        System.out.println(ld);

        session.setAttribute("loginData", ld);

        String res = "redirect:/" + ld.getRole() + "/home";

        return res;
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "pasien/registrasi";
    }

    @PostMapping("/signup/submit")
    public String submitSignup(HttpSession session) {

        // check which user then redirect using switch case

        return "redirect:/dokter/home";
    }
}
