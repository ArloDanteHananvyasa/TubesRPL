package com.example.TubesRPL.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class resepWithCatatan {
    private String namaObat;
    private String jumlah;
    private String dosis;
    private String instruksiPenggunaan;
    private String catatanDokter;
}
