package com.example.TubesRPL.repository;

import java.util.List;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.doctorData;
import com.example.TubesRPL.data.jadwalData;

public interface adminRepo {
    List<appointmentData> findPasien();

    List<appointmentData> findPasien(String filter);

    String findRuangan(int idJadwal, String nipDokter);

    List<jadwalData> findDokter();

    List<jadwalData> findDokter(String filter);

    List<doctorData> findDokterWithNIP(String nip);

    List<appointmentData> findBooking();

    List<appointmentData> findBooking(String filter);

    List<appointmentData> findPembayaran();

    List<appointmentData> findPembayaran(String filter);

    void confirmBooking(int idPendaftaran);

    void confirmPembayaran(int idPendaftaran, int metodePembayaran);

    boolean editJadwal(int idJadwalLama, String ruangan, int idJadwalBaru, String nip);
}
