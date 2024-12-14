package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class diagnosaData {
    private int idDiagnosa;
    private String hasilDiagnosa;
    private String catatanDokter;
}

// private diagnosaData mapRowToDiagnosaData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create diagnosaData object using constructor
// return new diagnosaData(
// rs.getInt("idDiagnosa"),
// rs.getString("hasilDiagnosa"),
// rs.getString("catatanDokter")
// );
// }