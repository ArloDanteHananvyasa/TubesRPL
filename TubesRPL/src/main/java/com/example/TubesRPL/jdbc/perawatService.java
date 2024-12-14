package com.example.TubesRPL.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.repository.perawatRepo;

@Repository
public class perawatService implements perawatRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void sumbitDataPasien(int idPendaftaran, String tekananDarah, double tinggiBadan, double beratBadan,
            double suhuBadan, String keluhan, String nipPerawat) {

        jdbc.update(
                "INSERT INTO pemeriksaan (idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan, nipPerawat) VALUES (?,?,?,?,?,?,?)",
                idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan, nipPerawat);
    }

    @Override
    public void sumbitLabPasien(int idPendaftaran, Date tanggal, MultipartFile file) {
        try {
            byte[] filebytes = file.getBytes();

            jdbc.update("INSERT INTO hasil_lab (idPendaftaran, tanggalLab, hasilLab) VALUES (?,?,?)", idPendaftaran,
                    tanggal,
                    filebytes);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<appointmentData> lihatPasien() {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData);
    }

    private appointmentData mapRowToAppointmentData(ResultSet rs, int rowNum)
            throws SQLException {
        // Convert patient's date of birth to LocalDate
        LocalDate dobLocalDate = null;
        if (rs.getDate("tanggalLahir") != null) {
            dobLocalDate = rs.getDate("tanggalLahir").toLocalDate();
        }

        // Calculate patient's age
        int pasienUmur = (dobLocalDate != null) ? Period.between(dobLocalDate,
                LocalDate.now()).getYears() : 0;

        // Map result set to AppointmentData object
        return new appointmentData(
                rs.getInt("idPendaftaran"),
                rs.getDate("tanggalPendaftaran"),
                rs.getDate("tanggalKunjungan"),
                rs.getBoolean("sudahDatang"),
                rs.getString("pasienNik"),
                rs.getString("pasienNama"),
                rs.getString("genderPasien"),
                rs.getString("hpPasien"),
                rs.getString("emailPasien"),
                pasienUmur,
                rs.getString("dokterNip"),
                rs.getString("dokterNama"),
                rs.getDouble("dokterTarif"),
                rs.getString("jadwalHari"),
                rs.getString("jadwalJamMulai"),
                rs.getString("jadwalJamSelesai"),
                rs.getString("klinikSpesialisasi"),
                rs.getString("dokterRuangan"),
                rs.getString("metodePembayaran"));
    }
}
