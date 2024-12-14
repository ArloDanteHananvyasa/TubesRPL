package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class adminData {
    private String nip;
    private String nama;
    private String nomorTelepon;
    private String password;
}

// private adminData mapRowToAdminData(ResultSet rs, int rowNum) throws
// SQLException {
// // Create adminData object using @AllArgsConstructor
// return new adminData(
// rs.getString("nipPetugas"),
// rs.getString("nama"),
// rs.getString("nomorTelepon"),
// rs.getString("password"));
// }