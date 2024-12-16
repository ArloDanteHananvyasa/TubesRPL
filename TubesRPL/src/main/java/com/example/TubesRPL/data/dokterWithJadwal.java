package com.example.TubesRPL.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class dokterWithJadwal {
    doctorData dokter;
    List<jadwalData> jadwal;
}
