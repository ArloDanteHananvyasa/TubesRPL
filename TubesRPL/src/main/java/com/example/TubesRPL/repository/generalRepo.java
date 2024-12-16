package com.example.TubesRPL.repository;

import java.sql.Date;

import com.example.TubesRPL.data.loginData;

public interface generalRepo {

    loginData login(String nohp, String pass);

    void register(String nik, String nama, String nomorTelepon, String password, String gender, Date tanggalLahir,
            String email);
}
