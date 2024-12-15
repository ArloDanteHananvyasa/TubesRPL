package com.example.TubesRPL.repository;

import java.util.List;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.hasilLabData;
import com.example.TubesRPL.data.pemeriksaanData;
import com.example.TubesRPL.data.resepData;

public interface dokterRepo {
    List<appointmentData> findPasien(String nip);

    List<pemeriksaanData> findPemeriksaan(int idPendaftaran);

    List<hasilLabData> findHasilLab(int idPendaftaran);

    void insertResep(List<resepData> listObat, int idPendaftaran);

    byte[] findLabFileByIdLab(int idLab);
}
