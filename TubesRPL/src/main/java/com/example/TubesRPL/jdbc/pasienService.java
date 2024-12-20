package com.example.TubesRPL.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
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
        // Get today's date and determine the day of the week
        LocalDate today = LocalDate.now();
        int todayDayOfWeek = today.getDayOfWeek().getValue(); // 1=Monday, 7=Sunday

        // Determine the target tanggalKunjungan based on idJadwal
        LocalDate targetDate = today;

        if (idJadwal == 1 || idJadwal == 2) { // Monday sessions
            if (todayDayOfWeek == 1) { // Today is Monday
                targetDate = today;
            } else if (todayDayOfWeek < 1) { // If today is before Monday, set to this Monday
                targetDate = today.with(DayOfWeek.MONDAY);
            } else { // If today is after Monday, set to next Monday
                targetDate = today.plusWeeks(1).with(DayOfWeek.MONDAY);
            }
        } else if (idJadwal == 3 || idJadwal == 4) { // Tuesday sessions
            if (todayDayOfWeek == 2) { // Today is Tuesday
                targetDate = today;
            } else if (todayDayOfWeek < 2) { // If today is before Tuesday, set to this Tuesday
                targetDate = today.with(DayOfWeek.TUESDAY);
            } else { // If today is after Tuesday, set to next Tuesday
                targetDate = today.plusWeeks(1).with(DayOfWeek.TUESDAY);
            }
        } else if (idJadwal == 5 || idJadwal == 6) { // Wednesday sessions
            if (todayDayOfWeek == 3) { // Today is Wednesday
                targetDate = today;
            } else if (todayDayOfWeek < 3) { // If today is before Wednesday, set to this Wednesday
                targetDate = today.with(DayOfWeek.WEDNESDAY);
            } else { // If today is after Wednesday, set to next Wednesday
                targetDate = today.plusWeeks(1).with(DayOfWeek.WEDNESDAY);
            }
        } else if (idJadwal == 7 || idJadwal == 8) { // Thursday sessions
            if (todayDayOfWeek == 4) { // Today is Thursday
                targetDate = today;
            } else if (todayDayOfWeek < 4) { // If today is before Thursday, set to this Thursday
                targetDate = today.with(DayOfWeek.THURSDAY);
            } else { // If today is after Thursday, set to next Thursday
                targetDate = today.plusWeeks(1).with(DayOfWeek.THURSDAY);
            }
        } else if (idJadwal == 9 || idJadwal == 10) { // Friday sessions
            if (todayDayOfWeek == 5) { // Today is Friday
                targetDate = today;
            } else if (todayDayOfWeek < 5) { // If today is before Friday, set to this Friday
                targetDate = today.with(DayOfWeek.FRIDAY);
            } else { // If today is after Friday, set to next Friday
                targetDate = today.plusWeeks(1).with(DayOfWeek.FRIDAY);
            }
        } else if (idJadwal == 11 || idJadwal == 12) { // Saturday sessions
            if (todayDayOfWeek == 6) { // Today is Saturday
                targetDate = today;
            } else if (todayDayOfWeek < 6) { // If today is before Saturday, set to this Saturday
                targetDate = today.with(DayOfWeek.SATURDAY);
            } else { // If today is after Saturday, set to next Saturday
                targetDate = today.plusWeeks(1).with(DayOfWeek.SATURDAY);
            }
        } else if (idJadwal == 13) { // Sunday session
            if (todayDayOfWeek == 7) { // Today is Sunday
                targetDate = today;
            } else if (todayDayOfWeek < 7) { // If today is before Sunday, set to this Sunday
                targetDate = today.with(DayOfWeek.SUNDAY);
            } else { // If today is after Sunday, set to next Sunday
                targetDate = today.plusWeeks(1).with(DayOfWeek.SUNDAY);
            }
        }

        // Insert into pendaftaran with the calculated tanggalKunjungan
        jdbc.update(
                "INSERT INTO pendaftaran (tanggalPendaftaran, idJadwal, nipDokter, nikPasien, tanggalKunjungan) VALUES (?, ?, ?, ?, ?)",
                tanggalPendaftaran, idJadwal, nipDokter, nikPasien, Date.valueOf(targetDate));
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
