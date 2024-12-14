package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class resepData {
    private String namaObat;
    private String dosis;
    private String instruksiPenggunaan;
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