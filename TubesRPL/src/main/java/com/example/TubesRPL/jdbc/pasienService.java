package com.example.TubesRPL.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.TubesRPL.data.appointmentData;
import com.example.TubesRPL.data.doctorData;
import com.example.TubesRPL.data.dokterWithJadwal;
import com.example.TubesRPL.data.jadwalData;
import com.example.TubesRPL.data.resepWithCatatan;
import com.example.TubesRPL.repository.pasienRepo;

@Repository
public class pasienService implements pasienRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<dokterWithJadwal> cariJadwal() {
        List<doctorData> doctors = findAllDoctors();
        List<jadwalData> schedules = findAllSchedules();

        // Group schedules by doctor NIP
        Map<String, List<jadwalData>> schedulesByDoctor = schedules.stream()
                .collect(Collectors.groupingBy(jadwalData::getNipDokter));

        // Combine data into dokterWithJadwal objects
        List<dokterWithJadwal> result = new ArrayList<>();
        for (doctorData doctor : doctors) {
            List<jadwalData> doctorSchedules = schedulesByDoctor.getOrDefault(doctor.getNip(), new ArrayList<>());
            result.add(new dokterWithJadwal(doctor, doctorSchedules));
        }

        return result;
    }

    @Override
    public void insertPendaftaran(String nipDokter, int idJadwal, String nikPasien, Date tanggalPendaftaran) {
        jdbc.update(
                "INSERT INTO pendaftaran (tanggalPendaftaran, idJadwal, nipDokter, nikPasien, tanggalKunjungan) VALUES (?, ?, ?, ?, ?)",
                tanggalPendaftaran, idJadwal, nipDokter, nikPasien, tanggalPendaftaran);
    }

    private List<doctorData> findAllDoctors() {
        return jdbc.query(
                "SELECT dokter.nipDokter, dokter.nama AS namaDokter, dokter.idKlinik, klinik.spesialisasi, dokter.tarif, dokter.kuota, dokter.nomorTelepon, dokter.password FROM dokter JOIN klinik ON klinik.idKlinik = dokter.idKlinik",
                this::mapRowToDoctorData);
    }

    private List<jadwalData> findAllSchedules() {
        return jdbc.query(
                "SELECT jadwal_dokter.nipDokter, dokter.nama AS namaDokter, jadwal_dokter.ruangan, jadwal.idJadwal, jadwal.hari, jadwal.jamMulai, jadwal.jamSelesai FROM jadwal_dokter JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal JOIN dokter ON dokter.nipDokter = jadwal_dokter.nipDokter",
                this::mapRowToJadwalData);
    }

    @Override
    public List<appointmentData> lihatRiwayat(String nikPasien) {
        return jdbc.query(
                "SELECT DISTINCT pendaftaran.idPendaftaran, pendaftaran.tanggalPendaftaran, pendaftaran.tanggalKunjungan, pendaftaran.sudahDatang, pasien.nik AS pasienNik, pasien.nama AS pasienNama, pasien.tanggalLahir AS tanggalLahir, pasien.gender AS genderPasien, pasien.nomorTelepon AS hpPasien, pasien.email AS emailPasien, dokter.nipDokter AS dokterNip, dokter.nama AS dokterNama, dokter.tarif AS dokterTarif, jadwal.hari AS jadwalHari, jadwal.jamMulai AS jadwalJamMulai, jadwal.jamSelesai AS jadwalJamSelesai, jadwal_dokter.ruangan AS dokterRuangan, diagnosa.idDiagnosa, diagnosa.hasilDiagnosa, diagnosa.catatanDokter, pemeriksaan.idPemeriksaan, pemeriksaan.tekananDarah, pemeriksaan.tinggiBadan, pemeriksaan.beratBadan, pemeriksaan.suhuBadan, pemeriksaan.keluhan, klinik.spesialisasi AS klinikSpesialisasi, metode_pembayaran.metode AS metodePembayaran FROM pendaftaran LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode WHERE nikPasien = ? ORDER BY idPendaftaran ASC",
                this::mapRowToAppointmentData, nikPasien);
    }

    @Override
    public List<resepWithCatatan> lihatResep(int idPendaftaran) {
        return jdbc.query(
                "SELECT ro.namaObat, ro.jumlah, ro.dosis, ro.instruksiPenggunaan, d.catatanDokter FROM resep_obat ro JOIN resep r ON ro.idResep = r.idResep JOIN diagnosa d ON r.idDiagnosa = d.idDiagnosa JOIN pemeriksaan p ON d.idPemeriksaan = p.idPemeriksaan JOIN pendaftaran pd ON p.idPendaftaran = pd.idPendaftaran WHERE pd.idPendaftaran = ?",
                this::mapRowToResepWithCatatanData, idPendaftaran);
    }

    private resepWithCatatan mapRowToResepWithCatatanData(ResultSet rs, int rowNum)
            throws SQLException {
        return new resepWithCatatan(
                rs.getString("namaObat"),
                rs.getString("jumlah"),
                rs.getString("dosis"),
                rs.getString("instruksiPenggunaan"),
                rs.getString("catatanDokter"));
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

    private jadwalData mapRowToJadwalData(ResultSet rs, int rowNum) throws SQLException {
        return new jadwalData(
                rs.getString("nipDokter"),
                rs.getString("namaDokter"),
                rs.getInt("idJadwal"),
                rs.getString("hari"),
                rs.getString("jammulai"),
                rs.getString("jamselesai"),
                rs.getString("ruangan"));
    }

    private doctorData mapRowToDoctorData(ResultSet rs, int rowNum) throws SQLException {
        // Create doctorData object using @AllArgsConstructor
        return new doctorData(
                rs.getString("nipDokter"),
                rs.getString("namaDokter"),
                rs.getInt("idKlinik"),
                rs.getString("spesialisasi"),
                rs.getDouble("tarif"),
                rs.getInt("kuota"),
                rs.getString("nomorTelepon"),
                rs.getString("password"));
    }

}
