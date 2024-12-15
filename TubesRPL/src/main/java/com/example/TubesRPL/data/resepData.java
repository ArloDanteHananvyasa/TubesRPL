package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class resepData {
    private String namaObat;
    private String jumlah;
    private String dosis;
    private String catatan;
}

// private resepData mapRowToResepData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create resepData object using constructor
// return new resepData(
// rs.getString("namaObat"),
// rs.getString("dosis"),
// rs.getString("instruksiPenggunaan")
// );
// }