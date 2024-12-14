package com.example.TubesRPL.data;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class hasilLabData {
    private Date tanggalLab;
    private byte[] file;
}
