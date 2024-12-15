--inserts
INSERT INTO pasien (nik, nama, nomorTelepon, password, tanggalLahir, email) 
VALUES (?, ?, ?, ?, ?, ?);

INSERT INTO perawat (nipPerawat, nama, nomorTelepon, password) 
VALUES (?, ?, ?, ?);

INSERT INTO petugas_administrasi (nipPetugas, nama, nomorTelepon, password) 
VALUES (?, ?, ?, ?);

INSERT INTO klinik (spesialisasi) 
VALUES (?);

--find id first to insert doctor
SELECT idKlinik 
FROM klinik 
WHERE spesialisasi = ?;

INSERT INTO dokter (nipDokter, nama, idKlinik, tarif, kuotaBefore, kuotaToday, nomorTelepon, password) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?);

--check for clash in schedule
SELECT * 
FROM jadwal_dokter 
WHERE idJadwal = ? 
  AND ruangan = ?;

--insert new schedule
INSERT INTO jadwal_dokter (idJadwal, nipDokter, ruangan) 
VALUES (?, ?, ?);

--delete schedule
DELETE FROM jadwal_dokter 
WHERE idJadwal = ? 
  AND nipDokter = ?;

--find all clinics:
SELECT idKlinik, spesialisasi FROM klinik;

--find all pendaftaran and all related info:
SELECT 
    -- Appointment Details
    pendaftaran.idPendaftaran,
    pendaftaran.tanggalPendaftaran,
    pendaftaran.tanggalKunjungan,
    pendaftaran.sudahDatang,
    pasien.nik AS pasienNik,
    pasien.nama AS pasienNama,
    pasien.tanggalLahir AS tanggalLahir,
	pasien.gender AS genderPasien,
	pasien.nomorTelepon AS hpPasien,
	pasien.email AS emailPasien,
    dokter.nipDokter AS dokterNip,
    dokter.nama AS dokterNama,
	dokter.tarif AS dokterTarif,
	--Jadwal
	jadwal.hari AS jadwalHari,
    jadwal.jamMulai AS jadwalJamMulai,
    jadwal.jamSelesai AS jadwalJamSelesai,
	jadwal_dokter.ruangan AS dokterRuangan,
    -- Diagnosis
    diagnosa.idDiagnosa,
    diagnosa.hasilDiagnosa,
    diagnosa.catatanDokter,
    -- Pemeriksaan
    pemeriksaan.idPemeriksaan,
    pemeriksaan.tekananDarah,
    pemeriksaan.tinggiBadan,
    pemeriksaan.beratBadan,
    pemeriksaan.suhuBadan,
    pemeriksaan.keluhan,
    -- Prescription
    resep_obat.namaObat,
    resep_obat.dosis,
    resep_obat.instruksiPenggunaan,
	--others
	klinik.spesialisasi AS klinikSpesialisasi,
	metode_pembayaran.metode AS metodePembayaran
FROM 
    pendaftaran
LEFT JOIN pasien ON pendaftaran.nikPasien = pasien.nik
LEFT JOIN pemeriksaan ON pendaftaran.idPendaftaran = pemeriksaan.idPendaftaran
LEFT JOIN diagnosa ON diagnosa.idPemeriksaan = pemeriksaan.idPemeriksaan
LEFT JOIN dokter ON pendaftaran.nipDokter = dokter.nipDokter
LEFT JOIN jadwal_dokter ON pendaftaran.idJadwal = jadwal_dokter.idJadwal AND pendaftaran.nipDokter = jadwal_dokter.nipDokter
LEFT JOIN jadwal ON jadwal_dokter.idJadwal = jadwal.idJadwal
LEFT JOIN klinik ON dokter.idKlinik = klinik.idKlinik
LEFT JOIN resep ON diagnosa.idDiagnosa = resep.idDiagnosa
LEFT JOIN resep_obat ON resep_obat.idResep = resep.idResep
LEFT JOIN metode_pembayaran ON pendaftaran.idMetode = metode_pembayaran.idMetode
ORDER BY idPendaftaran ASC

WHERE 
    pendaftaran.idPendaftaran = 2

SELECT * FROM pendaftaran

--rekam medis
INSERT INTO pemeriksaan (idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan) VALUES (?,?,?,?,?,?)

--cari jadwal
SELECT DISTINCT
    jadwal.hari,
    jadwal.jamMulai,
    jadwal.jamSelesai,
    jadwal_dokter.ruangan,
    CASE jadwal.hari 
        WHEN 'Senin' THEN 1
        WHEN 'Selasa' THEN 2
        WHEN 'Rabu' THEN 3
        WHEN 'Kamis' THEN 4
        WHEN 'Jumat' THEN 5
        WHEN 'Sabtu' THEN 6
        WHEN 'Minggu' THEN 7
        ELSE 8
    END AS day_order
FROM jadwal
JOIN jadwal_dokter ON jadwal.idJadwal = jadwal_dokter.idJadwal
JOIN dokter ON jadwal_dokter.nipDokter = dokter.nipDokter
JOIN klinik ON dokter.idKlinik = klinik.idKlinik
WHERE dokter.nipDokter = '198305162021011001'
ORDER BY day_order;





