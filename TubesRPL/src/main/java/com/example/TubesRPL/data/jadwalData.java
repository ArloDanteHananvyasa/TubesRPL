package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class jadwalData {
    private String hari;
    private String jamMulai;
    private String jamSelesai;
    private String ruangan;
}

// private adminData mapRowToJadwalData(ResultSet rs, int rowNum) throws
// SQLException {
// return new adminData(
// rs.getString("hari"),
// rs.getString("jammulai"),
// rs.getString("jamselesai"),
// rs.getString("ruangan"));
// }