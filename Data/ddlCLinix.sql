DROP TABLE IF EXISTS resep CASCADE;
DROP TABLE IF EXISTS diagnosa CASCADE;
DROP TABLE IF EXISTS pemeriksaan CASCADE;
DROP TABLE IF EXISTS pembayaran CASCADE;
DROP TABLE IF EXISTS metode_pembayaran CASCADE;
DROP TABLE IF EXISTS pendaftaran CASCADE;
DROP TABLE IF EXISTS jadwal_dokter CASCADE;
DROP TABLE IF EXISTS resep_obat CASCADE;
DROP TABLE IF EXISTS jadwal CASCADE;
DROP TABLE IF EXISTS dokter CASCADE;
DROP TABLE IF EXISTS klinik CASCADE;
DROP TABLE IF EXISTS perawat CASCADE;
DROP TABLE IF EXISTS petugas_administrasi CASCADE;
DROP TABLE IF EXISTS pasien CASCADE;
DROP TABLE IF EXISTS obat CASCADE;
DROP TABLE IF EXISTS hasil_lab CASCADE;


CREATE TABLE pasien (
    nik CHAR(16) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    nomorTelepon VARCHAR(15),
	password VARCHAR(50),
	gender VARCHAR(10),
    tanggalLahir DATE,
	email VARCHAR(50)
);

CREATE TABLE perawat (
    nipPerawat char(18) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    nomorTelepon VARCHAR(15),
	password VARCHAR(50)
);

CREATE TABLE petugas_administrasi (
    nipPetugas char(18) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    nomorTelepon VARCHAR(15),
	password VARCHAR(50)
);

CREATE TABLE jadwal (
    idJadwal SERIAL PRIMARY KEY,
    hari VARCHAR(20) NOT NULL,
    jamMulai TIME NOT NULL,
    jamSelesai TIME NOT NULL
);

CREATE TABLE klinik (
    idKlinik SERIAL PRIMARY KEY,
    spesialisasi VARCHAR(100) NOT NULL
);

CREATE TABLE dokter (
    nipDokter CHAR(18) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    idKlinik INT REFERENCES klinik(idKlinik),
    tarif NUMERIC NOT NULL,
    kuota INT NOT NULL,
	nomorTelepon VARCHAR(15),
	password VARCHAR(50)
);

CREATE TABLE jadwal_dokter (
    idJadwal INT REFERENCES jadwal(idJadwal),
    nipDokter CHAR(18) REFERENCES dokter(nipDokter),
	ruangan VARCHAR(10) NOT NULL,
    PRIMARY KEY (idJadwal, nipDokter)
);

CREATE TABLE metode_pembayaran (
	idMetode INT PRIMARY KEY,
	metode VARCHAR(20) NOT NULL
);

CREATE TABLE pendaftaran (
    idPendaftaran SERIAL PRIMARY KEY,
    tanggalPendaftaran DATE NOT NULL,
    tanggalKunjungan DATE NOT NULL,
    sudahDatang BOOLEAN DEFAULT FALSE,
    idJadwal INT NOT NULL,
    nipDokter CHAR(18) NOT NULL,
    nikPasien CHAR(18) REFERENCES pasien(nik),
    idMetode INT REFERENCES metode_pembayaran(idMetode) DEFAULT NULL,
    FOREIGN KEY (idJadwal, nipDokter) REFERENCES jadwal_dokter(idJadwal, nipDokter)
);


CREATE TABLE pemeriksaan (
    idPemeriksaan SERIAL PRIMARY KEY,
    idPendaftaran INT REFERENCES pendaftaran(idPendaftaran),
    tekananDarah VARCHAR(20),
    tinggiBadan NUMERIC(5, 2),
    beratBadan NUMERIC(5, 2),
    suhuBadan NUMERIC(4, 2),
    keluhan TEXT,
    nipPerawat char(18) REFERENCES perawat(nipPerawat)
);

CREATE TABLE hasil_lab (
	idLab SERIAL PRIMARY KEY,
	idPendaftaran INT REFERENCES pendaftaran(idPendaftaran),
	tanggalLab DATE,
	hasilLab BYTEA
);

CREATE TABLE diagnosa (
    idDiagnosa SERIAL PRIMARY KEY,
    idPemeriksaan INT REFERENCES pemeriksaan(idPemeriksaan),
    nipDokter CHAR(18) REFERENCES dokter(nipDokter),
    hasilDiagnosa TEXT,
    catatanDokter TEXT
);

CREATE TABLE resep (
    idResep SERIAL PRIMARY KEY,
    idDiagnosa INT REFERENCES diagnosa(idDiagnosa)
);

CREATE TABLE resep_obat (
    idResep INT REFERENCES resep(idResep) ON DELETE CASCADE,
    namaObat VARCHAR(50),
    dosis VARCHAR(50),
    instruksiPenggunaan TEXT
);