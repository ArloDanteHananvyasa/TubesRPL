package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class pemeriksaanData {
    private int idPemeriksaan;
    private int idPendaftaran;
    private String tekananDarah;
    private double tinggiBadan;
    private double beratBadan;
    private double suhuBadan;
    private String keluhan;
    private String nipPerawat;
    private String namaPerawat;
}

// private pemeriksaanData mapRowToPemeriksaanData(ResultSet rs, int rowNum)
// throws SQLException {
// // Create pemeriksaanData object using constructor
// return new pemeriksaanData(
// rs.getInt("idPemeriksaan"),
// rs.getInt("idPendaftaran"),
// rs.getString("tekananDarah"),
// rs.getDouble("tinggiBadan"),
// rs.getDouble("beratBadan"),
// rs.getDouble("suhuBadan"),
// rs.getString("keluhan"),
// rs.getString("nipPerawat")
// );
// }