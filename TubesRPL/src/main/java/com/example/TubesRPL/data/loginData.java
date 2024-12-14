package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class loginData {
    private String nik;
    private String nip;
    private String nama;
    private String nomorTelepon;
    private String password;
    private String role;
}
