package com.example.TubesRPL.repository;

import java.sql.Date;
import java.util.List;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.dokterWithJadwal;
import com.example.TubesRPL.data.resepWithCatatan;

public interface pasienRepo {
    List<dokterWithJadwal> cariJadwal();

    void insertPendaftaran(String nipDokter, int idJadwal, String nikPasien, Date tanggalPendaftaran);

    List<appointmentData> lihatRiwayat(String nikPasien);

    List<resepWithCatatan> lihatResep(int idPendaftaran);
}
