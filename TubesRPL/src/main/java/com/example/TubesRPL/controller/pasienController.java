package com.example.TubesRPL.controller;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.dokterWithJadwal;
import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.data.resepWithCatatan;
import com.example.TubesRPL.repository.pasienRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pasien")
public class pasienController {

    @Autowired
    private pasienRepo repo;

    @GetMapping("/home")
    public String showHomepage() {
        return "pasien/homepage";
    }

    @GetMapping("/jadwal-dokter")
    public String showJadwalDokter(Model model) {
        List<dokterWithJadwal> dokter = repo.cariJadwal();

        model.addAttribute("doctorsWithSchedules", dokter);

        return "pasien/jadwalDokter";
    }

    @GetMapping("/book")
    public String bookAppointment(@RequestParam("nip") String nip, @RequestParam("idJadwal") int idJadwal,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        String nikPasien = ld.getNik();

        Date tanggalPendaftaran = Date.valueOf(LocalDate.now());

        repo.insertPendaftaran(nip, idJadwal, nikPasien, tanggalPendaftaran);

        return "redirect:/pasien/lihat-riwayat"; // Redirect or show confirmation
    }

    @GetMapping("/lihat-riwayat")
    public String showRiwayat(HttpSession session, Model model) {
        loginData ld = (loginData) session.getAttribute("loginData");

        String nikPasien = ld.getNik();

        List<appointmentData> list = repo.lihatRiwayat(nikPasien);

        model.addAttribute("results", list);

        return "pasien/riwayatBooking";
    }

    @GetMapping("/lihat-resep")
    public String showResep(@RequestParam("id") int idPendaftaran, Model model) {

        List<resepWithCatatan> list = repo.lihatResep(idPendaftaran);

        model.addAttribute("results", list);

        return "pasien/Resep_Obat_Pasien";
    }
}
