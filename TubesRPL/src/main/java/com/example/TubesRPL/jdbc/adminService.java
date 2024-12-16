package com.example.TubesRPL.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.doctorData;
import com.example.TubesRPL.data.jadwalData;
import com.example.TubesRPL.repository.adminRepo;

@Repository
public class adminService implements adminRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void confirmBooking(int idPendaftaran) {
        jdbc.update("UPDATE pendaftaran SET sudahDatang = true WHERE idPendaftaran = ?", idPendaftaran);
    }

    @Override
    public void confirmPembayaran(int idPendaftaran, int metodePembayaran) {
        jdbc.update("UPDATE pendaftaran SET idMetode = ? WHERE idPendaftaran = ?", metodePembayaran, idPendaftaran);
    }

    @Override
    public List<appointmentData> findBooking() {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE pendaftaran.sudahDatang = false ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData);
    }

    @Override
    public List<appointmentData> findBooking(String filter) {
        String name = "%" + filter + "%";
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE pendaftaran.sudahDatang = false AND pasien.nama ILIKE ? ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData, name);
    }

    @Override
    public List<appointmentData> findPasien() {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData);

    }

    @Override
    public List<appointmentData> findPasien(String filter) {
        String name = "%" + filter + "%";
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE pasien.nama ILIKE ? ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData, name);

    }

    @Override
    public List<appointmentData> findPembayaran() {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE pendaftaran.sudahDatang = true AND pendaftaran.idMetode ISNULL ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData);

    }

    @Override
    public List<appointmentData> findPembayaran(String filter) {
        String name = "%" + filter + "%";
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE pasien.nama ILIKE ? AND pendaftaran.sudahDatang = true AND pendaftaran.idMetode ISNULL ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData, name);
    }

    @Override
    public List<jadwalData> findDokter() {
        return jdbc.query(
                "SELECT dokter.nipDokter AS nipDokter, dokter.nama AS namaDokter, jadwal.idJadwal AS idJadwal, jadwal.hari AS hariPraktek, jadwal.jamMulai AS jamMulai, jadwal.jamSelesai AS jamSelesai, jadwal_dokter.ruangan AS ruangPraktek, CASE jadwal.hari WHEN 'Senin' THEN 1 WHEN 'Selasa' THEN 2 WHEN 'Rabu' THEN 3 WHEN 'Kamis' THEN 4 WHEN 'Jumat' THEN 5 WHEN 'Sabtu' THEN 6 WHEN 'Minggu' THEN 7 ELSE 8 END AS day_order FROM jadwal_dokter JOIN dokter ON jadwal_dokter.nipDokter = dokter.nipDokter JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal ORDER BY dokter.nama, day_order",
                this::mapRowToJadwalData);
    }

    @Override
    public List<jadwalData> findDokter(String filter) {
        String name = "%" + filter + "%";
        return jdbc.query(
                "SELECT dokter.nipDokter AS nipDokter, dokter.nama AS namaDokter, jadwal.idJadwal AS idJadwal, jadwal.hari AS hariPraktek, jadwal.jamMulai AS jamMulai, jadwal.jamSelesai AS jamSelesai, jadwal_dokter.ruangan AS ruangPraktek, CASE jadwal.hari WHEN 'Senin' THEN 1 WHEN 'Selasa' THEN 2 WHEN 'Rabu' THEN 3 WHEN 'Kamis' THEN 4 WHEN 'Jumat' THEN 5 WHEN 'Sabtu' THEN 6 WHEN 'Minggu' THEN 7 ELSE 8 END AS day_order FROM jadwal_dokter JOIN dokter ON jadwal_dokter.nipDokter = dokter.nipDokter JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal WHERE dokter.nama ILIKE ? ORDER BY dokter.nama, day_order",
                this::mapRowToJadwalData, name);
    }

    @Override
    public List<doctorData> findDokterWithNIP(String nip) {
        return jdbc.query(
                "SELECT dokter.nipDokter, dokter.nama, dokter.idKlinik, klinik.spesialisasi, dokter.tarif, dokter.kuota, dokter.nomorTelepon, dokter.password FROM dokter JOIN klinik ON dokter.idKlinik = klinik.idKlinik WHERE dokter.nipDokter = ?",
                this::mapRowToDoctorData,
                nip);
    }

    @Override
    public boolean editJadwal(int idJadwalBaru, String ruangan, int idJadwalLama, String nip) {
        if (checkIfCanOrNot(idJadwalBaru, ruangan, idJadwalLama, nip)) {
            jdbc.update(
                    "UPDATE pendaftaran SET idJadwal = ? WHERE idJadwal = ? AND nipDokter = ? AND sudahDatang = false",
                    idJadwalBaru,
                    idJadwalLama, nip);

            jdbc.update("UPDATE jadwal_dokter SET idJadwal = ?, ruangan = ? WHERE idJadwal = ? AND nipDokter = ?",
                    idJadwalBaru, ruangan,
                    idJadwalLama, nip);

            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfCanOrNot(int idJadwalBaru, String ruangan, int idJadwalLama, String nip) {
        // Check for conflicts in room
        int roomConflictCount = jdbc.queryForObject(
                "SELECT COUNT(*) FROM jadwal_dokter WHERE idJadwal = ? AND ruangan = ?",
                Integer.class,
                idJadwalBaru,
                ruangan);

        // Check for conflicts with the same doctor
        int doctorConflictCount = jdbc.queryForObject(
                "SELECT COUNT(*) FROM jadwal_dokter WHERE idJadwal = ? AND nipDokter = ?",
                Integer.class,
                idJadwalBaru,
                nip);

        // If both counts are zero, no conflict
        return roomConflictCount == 0 && doctorConflictCount == 0;
    }

    @Override
    public String findRuangan(int idJadwalLama, String nipDokter) {
        try {
            return jdbc.queryForObject(
                    "SELECT ruangan FROM jadwal_dokter WHERE idJadwal = ? AND nipDokter = ?",
                    String.class, idJadwalLama, nipDokter);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where no result is found
            return null; // Or return an empty string ""
        }
    }

    private doctorData mapRowToDoctorData(ResultSet rs, int rowNum) throws SQLException {
        // Create doctorData object using @AllArgsConstructor
        return new doctorData(
                rs.getString("nipDokter"),
                rs.getString("nama"),
                rs.getInt("idKlinik"),
                rs.getString("spesialisasi"),
                rs.getDouble("tarif"),
                rs.getInt("kuota"),
                rs.getString("nomorTelepon"),
                rs.getString("password"));
    }

    private jadwalData mapRowToJadwalData(ResultSet rs, int rowNum) throws SQLException {
        return new jadwalData(
                rs.getString("nipDokter"),
                rs.getString("namaDokter"),
                rs.getInt("idJadwal"),
                rs.getString("hariPraktek"),
                rs.getString("jammulai"),
                rs.getString("jamselesai"),
                rs.getString("ruangPraktek"));
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
