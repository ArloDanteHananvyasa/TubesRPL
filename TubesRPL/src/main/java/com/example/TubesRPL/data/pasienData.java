package com.example.TubesRPL.data;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class pasienData {
    private String nik;
    private String nama;
    private String nomorTelepon;
    private String password;
    private String gender;
    private Date dob;
    private int umur;
    private String email;
}

// private pasienData mapRowToPasienData(ResultSet rs, int rowNum) throws
// SQLException {
// // Convert DOB to LocalDate
// LocalDate dobLocalDate = null;
// if (rs.getDate("dob") != null) {
// dobLocalDate =
// rs.getDate("dob").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
// }

// // Calculate age
// int umur = (dobLocalDate != null) ? Period.between(dobLocalDate,
// LocalDate.now()).getYears() : 0;

// // Create pasienData object using @AllArgsConstructor
// return new pasienData(
// rs.getString("nik"),
// rs.getString("nama"),
// rs.getString("nomorTelepon"),
// rs.getString("password"),
// rs.getString("gender"),
// rs.getDate("tanggalLahir"),
// umur,
// rs.getString("email"));
// }