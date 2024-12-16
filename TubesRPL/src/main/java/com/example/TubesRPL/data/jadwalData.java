package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class jadwalData {
    private String nipDokter;
    private String namaDokter;
    private int idJadwal;
    private String hari;
    private String jamMulai;
    private String jamSelesai;
    private String ruangan;
}

// private jadwalData mapRowToJadwalData(ResultSet rs, int rowNum) throws
// SQLException {
// return new jadwalData(
// rs.getString("nipDokter"),
// rs.getString("namaDokter"),
// rs.getInt("idJadwal"),
// rs.getString("hariPraktek"),
// rs.getString("jammulai"),
// rs.getString("jamselesai"),
// rs.getString("ruangPraktek"));
// }