package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class nurseData {
    private String nip;
    private String nama;
    private String nomorTelepon;
    private String password;
}

// private nurseData mapRowToNurseData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create nurseData object using @AllArgsConstructor
// return new nurseData(
// rs.getString("nipPerawat"),
// rs.getString("nama"),
// rs.getString("nomorTelepon"),
// rs.getString("password"));
// }