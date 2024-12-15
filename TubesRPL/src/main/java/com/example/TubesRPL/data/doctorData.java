package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class doctorData {
    private String nip;
    private String nama;
    private int idKlinik;
    private String spesialisasi;
    private double tarif;
    private int kuota;
    private String nomorTelepon;
    private String password;
}

// private doctorData mapRowToDoctorData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create doctorData object using @AllArgsConstructor
// return new doctorData(
// rs.getString("nipDokter"),
// rs.getString("nama"),
// rs.getInt("idKlinik"),
// rs.getString("spesialisasi"),
// rs.getDouble("tarif"),
// rs.getInt("kuota"),
// rs.getString("nomorTelepon"),
// rs.getString("password"));
// }