package com.example.TubesRPL.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.TubesRPL.data.appointmentData;

public interface perawatRepo {
    void sumbitDataPasien(int idPendaftaran, String tekananDarah, double tinggiBadan, double beratBadan,
            double suhuBadan, String keluhan, String nipPerawat);

    void sumbitLabPasien(int idPendaftaran, Date tanggal, MultipartFile file);

    List<appointmentData> lihatPasien();
}
