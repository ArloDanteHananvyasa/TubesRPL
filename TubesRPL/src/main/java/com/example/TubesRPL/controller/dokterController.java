package com.example.TubesRPL.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.hasilLabData;
import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.data.pemeriksaanData;
import com.example.TubesRPL.data.resepData;
import com.example.TubesRPL.repository.dokterRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dokter")
public class dokterController {

    @Autowired
    private dokterRepo repo;

    @GetMapping("/home")
    public String showHomepageDokter(HttpSession session) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        return "dokter/dokter_Homepage";
    }

    @GetMapping("/lihat-pasien")
    public String showPasienDokter(HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        List<appointmentData> ap = repo.findPasien(ld.getNip());

        model.addAttribute("rows", ap);

        return "dokter/listPasienDokter";
    }

    @GetMapping("/detail-pasien")
    public String showDetailPasien(@RequestParam("nik") String nik, @RequestParam("idPendaftaran") int idPendaftaran,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        List<appointmentData> ap = repo.findPasien(ld.getNip());
        List<pemeriksaanData> pd = repo.findPemeriksaan(idPendaftaran);
        List<hasilLabData> hl = repo.findHasilLab(idPendaftaran);

        model.addAttribute("rows", ap);
        model.addAttribute("pasienDetail", pd.get(0));
        model.addAttribute("hasilLab", hl);

        return "dokter/listPasienDokter";
    }

    @GetMapping("/download-lab")
    public ResponseEntity<byte[]> downloadLabFile(
            @RequestParam int idLab) {

        // Fetch the file data from the repository
        byte[] fileData = repo.findLabFileByIdLab(idLab);

        // Generate a file name using the idLab or other metadata
        String fileName = "hasil_lab_" + idLab + ".pdf"; // Adjust extension if needed

        // Set headers for file download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF); // Change if not a PDF
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(fileName).build());

        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }

    @GetMapping("/diagnosa")
    public String showDiagnosa(@RequestParam("nik") String nik, @RequestParam("idPendaftaran") String idPendaftaran,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        session.setAttribute("nik", nik);
        session.setAttribute("idPendaftaran", idPendaftaran);

        return "dokter/inputDiagnosa";
    }

    @PostMapping("/diagnosa/submit")
    public String diagnosaSubmit(@RequestParam("diagnosa") String diagnosa, @RequestParam("catatan") String catatan,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        return "dokter/listPasienDokter";
    }

    @GetMapping("/resep")
    public String showResep(@RequestParam("nik") String nik, @RequestParam("idPendaftaran") String idPendaftaran,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        session.setAttribute("nik", nik);
        session.setAttribute("idPendaftaran", idPendaftaran);

        return "dokter/inputResep";
    }

    @PostMapping("/resep/add")
    public String addPrescription(@RequestParam("namaObat") String namaObat,
            @RequestParam("jumlahObat") String jumlahObat,
            @RequestParam("dosisObat") String dosisObat,
            @RequestParam("catatan") String catatan,
            HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        resepData obat = new resepData(namaObat, jumlahObat, dosisObat, catatan);

        @SuppressWarnings("unchecked")
        List<resepData> listObat = (List<resepData>) session.getAttribute("listObat");

        if (listObat == null) {
            listObat = new ArrayList<>();
        }
        listObat.add(obat);

        session.setAttribute("listObat", listObat);

        model.addAttribute("listObat", listObat);

        return "dokter/inputResep";
    }

    @GetMapping("/resep/submit")
    public String resepSubmit(HttpSession session, Model model) {

        loginData ld = (loginData) session.getAttribute("loginData");

        if (ld == null) {
            return "redirect:/login";
        }

        @SuppressWarnings("unchecked")
        List<resepData> listObat = (List<resepData>) session.getAttribute("listObat");

        repo.insertResep(listObat, (int) session.getAttribute("idPendaftaran"));

        return "dokter/listPasienDokter";
    }
}
