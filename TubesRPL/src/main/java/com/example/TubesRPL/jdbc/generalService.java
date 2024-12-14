package com.example.TubesRPL.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.TubesRPL.data.adminData;
import com.example.TubesRPL.data.doctorData;
import com.example.TubesRPL.data.loginData;
import com.example.TubesRPL.data.nurseData;
import com.example.TubesRPL.data.pasienData;
import com.example.TubesRPL.repository.generalRepo;

@Repository
public class generalService implements generalRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public loginData login(String nohp, String pass) {
        int check = checkUser(nohp);

        loginData ld = new loginData("", "", "", "", "", "");

        switch (check) {
            case 0:
                List<pasienData> list = jdbc.query(
                        "SELECT * FROM pasien WHERE nomortelepon = ? AND password = ?",
                        this::mapRowToPasienData, nohp, pass);

                ld = new loginData(list.get(0).getNik(), "-", list.get(0).getNama(), nohp, pass, "pasien");
                break;

            case 1:
                List<adminData> list1 = jdbc.query(
                        "SELECT * FROM petugas_administrasi WHERE nomortelepon = ? AND password = ?",
                        this::mapRowToAdminData, nohp, pass);

                ld = new loginData("-", list1.get(0).getNip(), list1.get(0).getNama(), nohp, pass, "admin");
                break;

            case 2:
                List<nurseData> list2 = jdbc.query(
                        "SELECT * FROM perawat WHERE nomortelepon = ? AND password = ?",
                        this::mapRowToNurseData, nohp, pass);

                ld = new loginData("-", list2.get(0).getNip(), list2.get(0).getNama(), nohp, pass, "perawat");
                break;

            case 3:
                List<doctorData> list3 = jdbc.query(
                        "SELECT * FROM dokter WHERE nomortelepon = ? AND password = ?",
                        this::mapRowToDoctorData, nohp, pass);

                ld = new loginData("-", list3.get(0).getNip(), list3.get(0).getNama(), nohp, pass, "dokter");
                break;

        }

        return ld;
    }

    private int checkUser(String nohp) {
        List<adminData> list1 = jdbc.query("SELECT * FROM petugas_administrasi WHERE nomortelepon = ?",
                this::mapRowToAdminData,
                nohp);

        if (!list1.isEmpty()) {
            return 1;
        }

        List<nurseData> list2 = jdbc.query("SELECT * FROM perawat WHERE nomortelepon = ?", this::mapRowToNurseData,
                nohp);

        if (!list2.isEmpty()) {
            return 2;
        }

        List<doctorData> list3 = jdbc.query("SELECT * FROM dokter WHERE nomortelepon = ?", this::mapRowToDoctorData,
                nohp);

        if (!list3.isEmpty()) {
            return 3;
        }

        return 0;
    }

    private pasienData mapRowToPasienData(ResultSet rs, int rowNum) throws SQLException {
        // Convert DOB to LocalDate
        LocalDate dobLocalDate = null;
        if (rs.getDate("dob") != null) {
            dobLocalDate = rs.getDate("dob").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        // Calculate age
        int umur = (dobLocalDate != null) ? Period.between(dobLocalDate, LocalDate.now()).getYears() : 0;

        // Create pasienData object using @AllArgsConstructor
        return new pasienData(
                rs.getString("nik"),
                rs.getString("nama"),
                rs.getString("nomorTelepon"),
                rs.getString("password"),
                rs.getString("gender"),
                rs.getDate("tanggalLahir"),
                umur,
                rs.getString("email"));
    }

    private nurseData mapRowToNurseData(ResultSet rs, int rowNum) throws SQLException {
        // Create nurseData object using @AllArgsConstructor
        return new nurseData(
                rs.getString("nipPerawat"),
                rs.getString("nama"),
                rs.getString("nomorTelepon"),
                rs.getString("password"));
    }

    private adminData mapRowToAdminData(ResultSet rs, int rowNum) throws SQLException {
        // Create adminData object using @AllArgsConstructor
        return new adminData(
                rs.getString("nipPetugas"),
                rs.getString("nama"),
                rs.getString("nomorTelepon"),
                rs.getString("password"));
    }

    private doctorData mapRowToDoctorData(ResultSet rs, int rowNum) throws SQLException {
        // Create doctorData object using @AllArgsConstructor
        return new doctorData(
                rs.getString("nipDokter"),
                rs.getString("nama"),
                rs.getInt("idKlinik"),
                rs.getDouble("tarif"),
                rs.getInt("kuota"),
                rs.getString("nomorTelepon"),
                rs.getString("password"));
    }
}
