package com.example.TubesRPL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.doctorData;
import com.example.TubesRPL.data.jadwalData;
import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.repository.adminRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private adminRepo repo;

    @GetMapping("/home")
    public String showHomepageAdmin() {
        return "admin/Admin_homepage";
    }

    @GetMapping("/lihat-pasien")
    public String showPasienAdmin(@RequestParam(value = "filter", required = false) String filter, HttpSession session,
            Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }
        List<appointmentData> list;

        if (filter == null) {
            list = repo.findBooking();
        } else {
            list = repo.findBooking(filter);
        }

        model.addAttribute("results", list);

        return "admin/Admin_Konfirmasi_Booking";
    }

    @GetMapping("/atur-jadwal")
    public String showJadwal(@RequestParam(value = "filter", required = false) String filter,
            HttpSession session,
            Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        List<jadwalData> list;

        if (filter == null) {
            list = repo.findDokter();
        } else {
            list = repo.findDokter(filter);
        }

        model.addAttribute("results", list);
        model.addAttribute("filter", filter);

        return "admin/Admin_Atur_jadwal";
    }

    @GetMapping("/atur-jadwal/edit")
    public String showDetailPasien(@RequestParam("nip") String nip, @RequestParam("idJadwal") int idJadwal,
            @RequestParam(value = "filter", required = false) String filter,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        List<doctorData> list1 = repo.findDokterWithNIP(nip);
        List<jadwalData> list;

        String ruangan = repo.findRuangan(idJadwal, nip);

        if (filter == null) {
            list = repo.findDokter();
        } else {
            list = repo.findDokter(filter);
        }

        session.setAttribute("idJadwal", idJadwal);
        session.setAttribute("nipDokter", nip);

        System.out.println(nip);

        model.addAttribute("results", list);
        model.addAttribute("namaDokter", list1.get(0).getNama());
        model.addAttribute("ruangPraktek", ruangan);

        return "admin/Admin_Atur_jadwal";
    }

    @PostMapping("/atur-jadwal/submit")
    public String updateSchedule(
            @RequestParam("schedule") int idJadwalBaru,
            @RequestParam("ruangPraktek") String ruangPraktek, HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        int idJadwalLama = (int) session.getAttribute("idJadwal");

        System.out.println(session.getAttribute("nipDokter"));
        System.out.println(idJadwalBaru);

        boolean update = repo.editJadwal(idJadwalBaru, ruangPraktek, idJadwalLama,
                (String) session.getAttribute("nipDokter"));

        if (update) {
            return "redirect:/admin/atur-jadwal";
        } else {
            model.addAttribute("error", "Update tidak berhasil, Jadwal Bertabrakan");
            model.addAttribute("results", repo.findDokter());
            return "admin/Admin_Atur_jadwal";
        }

    }

    @GetMapping("/konfirmasi-booking")
    public String showBooking(@RequestParam(value = "filter", required = false) String filter, HttpSession session,
            Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        List<appointmentData> list;

        if (filter == null) {
            list = repo.findBooking();
        } else {
            list = repo.findBooking(filter);
        }

        model.addAttribute("results", list);

        return "admin/Admin_Konfirmasi_Booking";
    }

    @GetMapping("/konfirmasi-booking/submit")
    public String confirmBooking(@RequestParam(value = "idPendaftaran") int idPendaftaran, HttpSession session,
            Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        repo.confirmBooking(idPendaftaran);

        return "redirect:/admin/konfirmasi-booking";
    }

    @GetMapping("/konfirmasi-pembayaran")
    public String showPembayaran(@RequestParam(value = "filter", required = false) String filter, HttpSession session,
            Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }
        List<appointmentData> list;

        if (filter == null) {
            list = repo.findPembayaran();
        } else {
            list = repo.findPembayaran(filter);
        }

        model.addAttribute("results", list);

        return "admin/Admin_Konfirmasi_Tagihan";
    }

    @PostMapping("/konfirmasi-pembayaran/submit")
    public String confirmBooking(
            @RequestParam("idPendaftaran") int idPendaftaran,
            @RequestParam("metodePembayaran") Integer metodePembayaran, HttpSession session) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        // Handle logic for the booking confirmation
        // Example:
        System.out.println("ID Pendaftaran: " + idPendaftaran);
        System.out.println("Metode Pembayaran: " + metodePembayaran);

        repo.confirmPembayaran(idPendaftaran, metodePembayaran);

        // Redirect or return a view
        return "redirect:/admin/konfirmasi-pembayaran";
    }

}
