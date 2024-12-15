package com.example.TubesRPL.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.hasilLabData;
import com.example.TubesRPL.data.pemeriksaanData;
import com.example.TubesRPL.data.resepData;
import com.example.TubesRPL.repository.dokterRepo;

@Repository
public class dokterService implements dokterRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<appointmentData> findPasien(String nip) {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE dokter.nipDokter = ? AND sudahDatang = true ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData, nip);
    }

    @Override
    public void insertResep(List<resepData> listObat, int idPendaftaran) {
        int idPemeriksaan = findIdPemeriksaan(idPendaftaran);

        int idDiagnosa = findIdDiagnosa(idPemeriksaan);

        jdbc.update("INSERT into resep (idDiagnosa) VALUES (?)", idDiagnosa);

        int idResep = findIdResep(idDiagnosa);

        for (resepData obat : listObat) {
            insertIntoResepObat(obat, idResep);
        }
    }

    @Override
    public byte[] findLabFileByIdLab(int idLab) {
        return jdbc.queryForObject("SELECT hasilLab FROM hasil_lab WHERE idLab = ?", byte[].class, idLab);
    }

    @Override
    public List<hasilLabData> findHasilLab(int idPendaftaran) {
        return jdbc.query("SELECT * FROM hasil_lab WHERE idPendaftaran = ?", this::mapRowToHasilLabData, idPendaftaran);
    }

    @Override
    public List<pemeriksaanData> findPemeriksaan(int idPendaftaran) {
        return jdbc.query(
                "SELECT p.idPemeriksaan, p.idPendaftaran, p.tekananDarah, p.tinggiBadan, p.beratBadan, p.suhuBadan, p.keluhan, p.nipPerawat, pr.nama AS namaPerawat FROM pemeriksaan p JOIN perawat pr ON p.nipPerawat = pr.nipPerawat WHERE p.idPendaftaran = ?",
                this::mapRowToPemeriksaanData, idPendaftaran);
    }

    private int findIdPemeriksaan(int idPendaftaran) {
        return jdbc.queryForObject(
                "SELECT idPemeriksaan FROM pemeriksaan JOIN pendaftaran ON pendaftaran.idPendaftaran = pemeriksaan.idPemeriksaan WHERE idPendaftaran = ?",
                Integer.class,
                idPendaftaran);
    }

    private int findIdDiagnosa(int idPemeriksaan) {
        return jdbc.queryForObject(
                "SELECT idDiagnosa FROM diagnosa JOIN pemeriksaan ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan WHERE idPemeriksaan = ?",
                Integer.class,
                idPemeriksaan);
    }

    private int findIdResep(int idDiagnosa) {
        return jdbc.queryForObject(
                "SELECT idResep FROM resep WHERE idDiagnosa = ?",
                Integer.class,
                idDiagnosa);
    }

    private void insertIntoResepObat(resepData r, int idResep) {
        jdbc.update("INSERT INTO resep_obat (idResep, namaObat, jumlah, dosis, instruksiPenggunaan) VALUES (?,?,?,?,?)",
                idResep, r.getNamaObat(), r.getJumlah(), r.getDosis(), r.getCatatan());
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

    private pemeriksaanData mapRowToPemeriksaanData(ResultSet rs, int rowNum)
            throws SQLException {
        // Create pemeriksaanData object using constructor
        return new pemeriksaanData(
                rs.getInt("idPemeriksaan"),
                rs.getInt("idPendaftaran"),
                rs.getString("tekananDarah"),
                rs.getDouble("tinggiBadan"),
                rs.getDouble("beratBadan"),
                rs.getDouble("suhuBadan"),
                rs.getString("keluhan"),
                rs.getString("nipPerawat"),
                rs.getString("namaPerawat"));
    }

    private hasilLabData mapRowToHasilLabData(ResultSet rs, int rowNum) throws SQLException {
        // Create adminData object using @AllArgsConstructor
        return new hasilLabData(
                rs.getInt("idLab"),
                rs.getInt("idPendaftaran"),
                rs.getDate("tanggalLab"),
                rs.getBytes("hasilLab"),
                rs.getString("namaFile"));
    }

}
