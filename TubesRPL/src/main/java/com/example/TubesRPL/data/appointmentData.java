package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class appointmentData {
    private int idPendaftaran;
    private Date tanggalPendaftaran;
    private Date tanggalKunjungan;
    private boolean sudahDatang;
    private String pasienNik;
    private String pasienNama;
    private String pasienGender;
    private String pasienNomorTelepon;
    private String pasienEmail;
    private int pasienUmur; // Calculated age
    private String dokterNip;
    private String dokterNama;
    private double dokterTarif;
    private String jadwalHari;
    private String jadwalJamMulai;
    private String jadwalJamSelesai;
    private String klinikSpesialisasi;
    private String dokterRuangan;
    private String metodePembayaran;
}

// private appointmentData mapRowToAppointmentData(ResultSet rs, int rowNum)
// throws SQLException {
// // Convert patient's date of birth to LocalDate
// LocalDate dobLocalDate = null;
// if (rs.getDate("tanggalLahir") != null) {
// dobLocalDate = rs.getDate("tanggalLahir").toLocalDate();
// }

// // Calculate patient's age
// int pasienUmur = (dobLocalDate != null) ? Period.between(dobLocalDate,
// LocalDate.now()).getYears() : 0;

// // Map result set to AppointmentData object
// return new appointmentData(
// rs.getInt("idPendaftaran"),
// rs.getDate("tanggalPendaftaran"),
// rs.getDate("tanggalKunjungan"),
// rs.getBoolean("sudahDatang"),
// rs.getString("pasienNik"),
// rs.getString("pasienNama"),
// rs.getString("genderPasien"),
// rs.getString("hpPasien"),
// rs.getString("emailPasien"),
// pasienUmur,
// rs.getString("dokterNip"),
// rs.getString("dokterNama"),
// rs.getDouble("dokterTarif"),
// rs.getString("jadwalHari"),
// rs.getString("jadwalJamMulai"),
// rs.getString("jadwalJamSelesai"),
// rs.getString("klinikSpesialisasi"),
// rs.getString("dokterRuangan"),
// rs.getString("metodePembayaran"));
// }