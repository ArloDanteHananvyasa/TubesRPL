DROP TABLE IF EXISTS Resep CASCADE;
DROP TABLE IF EXISTS Diagnosa CASCADE;
DROP TABLE IF EXISTS Pemeriksaan CASCADE;
DROP TABLE IF EXISTS Pembayaran CASCADE;
DROP TABLE IF EXISTS Pendaftaran CASCADE;
DROP TABLE IF EXISTS jadwalDokter CASCADE;
DROP TABLE IF EXISTS jadwal CASCADE;
DROP TABLE IF EXISTS Dokter CASCADE;
DROP TABLE IF EXISTS Klinik CASCADE;
DROP TABLE IF EXISTS Perawat CASCADE;
DROP TABLE IF EXISTS Petugas_Administrasi CASCADE;
DROP TABLE IF EXISTS Pasien CASCADE;


CREATE TABLE Pasien (
    NIK CHAR(16) PRIMARY KEY,
    Nama VARCHAR(100) NOT NULL,
    Nomor_Telepon VARCHAR(15),
    Tanggal_Lahir DATE
);

CREATE TABLE Perawat (
    ID_Perawat SERIAL PRIMARY KEY,
    Nama VARCHAR(100) NOT NULL,
    Nomor_Telepon VARCHAR(15)
);

CREATE TABLE Petugas_Administrasi (
    ID_Petugas SERIAL PRIMARY KEY,
    Nama VARCHAR(100) NOT NULL,
    Nomor_Telepon VARCHAR(15)
);

CREATE TABLE jadwal (
    idJadwal SERIAL PRIMARY KEY,
    hari VARCHAR(20) NOT NULL,
    jamMulai TIME NOT NULL,
    jamSelesai TIME NOT NULL
);

CREATE TABLE Klinik (
    IDklinik SERIAL PRIMARY KEY,
    spesialisasi VARCHAR(100) NOT NULL
);

CREATE TABLE Dokter (
    NIP CHAR(16) PRIMARY KEY,
    Nama VARCHAR(100) NOT NULL,
    idKlinik INT REFERENCES Klinik(IDklinik),
    Tarif NUMERIC(12, 2) NOT NULL,
    kuotabefore INT NOT NULL,
    kuotaToday INT NOT NULL
);

CREATE TABLE Pendaftaran (
    ID_Pendaftaran SERIAL PRIMARY KEY,
    Tanggal_Pendaftaran DATE NOT NULL,
    Tanggal_Kunjungan DATE NOT NULL,
    NIKPasien CHAR(16) REFERENCES Pasien(NIK) ON DELETE CASCADE,
    NIP_Dokter CHAR(16) REFERENCES Dokter(NIP) ON DELETE CASCADE
);

CREATE TABLE Pemeriksaan (
    ID_Pemeriksaan SERIAL PRIMARY KEY,
    ID_Pendaftaran INT REFERENCES Pendaftaran(ID_Pendaftaran) ON DELETE CASCADE,
    Tekanan_Darah VARCHAR(20),
    Tinggi_Badan NUMERIC(5, 2),
    Berat_Badan NUMERIC(5, 2),
    Suhu_Badan NUMERIC(4, 2),
    Keluhan TEXT,
    ID_Perawat INT REFERENCES Perawat(ID_Perawat) ON DELETE CASCADE
);

CREATE TABLE Diagnosa (
    ID_Diagnosa SERIAL PRIMARY KEY,
    ID_Pemeriksaan INT REFERENCES Pemeriksaan(ID_Pemeriksaan) ON DELETE CASCADE,
    ID_Dokter CHAR(16) REFERENCES Dokter(NIP) ON DELETE CASCADE,
    Hasil_Diagnosa TEXT,
    Catatan_Dokter TEXT
);

CREATE TABLE Resep (
    ID_Resep SERIAL PRIMARY KEY,
    ID_Diagnosa INT REFERENCES Diagnosa(ID_Diagnosa) ON DELETE CASCADE,
    Nama_Obat VARCHAR(100) NOT NULL,
    Dosis VARCHAR(50) NOT NULL,
    Instruksi_Penggunaan TEXT NOT NULL
);

CREATE TABLE Pembayaran (
    ID_Pembayaran SERIAL PRIMARY KEY,
    ID_Pendaftaran INT REFERENCES Pendaftaran(ID_Pendaftaran) ON DELETE CASCADE,
    Tanggal_Pembayaran DATE NOT NULL,
    Total_Biaya NUMERIC(12, 2) NOT NULL,
    Metode_Pembayaran VARCHAR(50) NOT NULL,
    ID_Petugas INT REFERENCES Petugas_Administrasi(ID_Petugas) ON DELETE CASCADE
);


CREATE TABLE jadwalDokter (
    idJadwal INT REFERENCES jadwal(idJadwal) ON DELETE CASCADE,
    NIP CHAR(16) REFERENCES Dokter(NIP) ON DELETE CASCADE,
    PRIMARY KEY (idJadwal, NIP)
);


