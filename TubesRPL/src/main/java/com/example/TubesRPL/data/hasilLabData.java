package com.example.TubesRPL.data;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class hasilLabData {
    private int idLab;
    private int idPendaftaran;
    private Date tanggalLab;
    private byte[] file;
    private String namaFile;
}

// private hasilLabData mapRowToAdminData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create adminData object using @AllArgsConstructor
// return new hasilLabData(
// rs.getInt("idLab"),
// rs.getInt("idPendaftaran"),
// rs.getDate("tanggalLab"),
// rs.getBytes("hasilLab"),
// rs.getString("namaFile"));
// }
