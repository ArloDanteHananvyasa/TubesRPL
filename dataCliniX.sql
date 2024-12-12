INSERT INTO obat (idObat, namaObat, deskripsi) VALUES
(1, 'Paracetamol', 'Digunakan untuk meredakan nyeri dan menurunkan demam'),
(2, 'Ibuprofen', 'Meredakan nyeri, peradangan, dan demam'),
(3, 'Amoxicillin', 'Antibiotik untuk mengobati infeksi bakteri'),
(4, 'Ciprofloxacin', 'Antibiotik spektrum luas untuk infeksi bakteri'),
(5, 'Metformin', 'Obat untuk mengontrol kadar gula darah pada diabetes tipe 2'),
(6, 'Omeprazole', 'Mengurangi produksi asam lambung untuk mengatasi maag dan refluks asam'),
(7, 'Losartan', 'Digunakan untuk mengontrol tekanan darah tinggi'),
(8, 'Atorvastatin', 'Menurunkan kadar kolesterol jahat dalam darah'),
(9, 'Amlodipine', 'Mengobati tekanan darah tinggi dan nyeri dada akibat angina'),
(10, 'Salbutamol', 'Obat untuk melegakan saluran napas pada penderita asma'),
(11, 'Cetirizine', 'Meredakan gejala alergi seperti gatal dan bersin'),
(12, 'Metronidazole', 'Antibiotik dan antiprotozoa untuk infeksi tertentu'),
(13, 'Prednisolone', 'Steroid untuk mengurangi peradangan dan reaksi alergi'),
(14, 'Furosemide', 'Diuretik untuk mengurangi retensi cairan pada tubuh'),
(15, 'Dexamethasone', 'Steroid untuk mengatasi peradangan dan kondisi autoimun'),
(16, 'Ranitidine', 'Mengatasi asam lambung tinggi dan maag'),
(17, 'Clopidogrel', 'Mencegah pembekuan darah pada kondisi tertentu'),
(18, 'Hydrochlorothiazide', 'Diuretik untuk tekanan darah tinggi dan retensi cairan'),
(19, 'Azithromycin', 'Antibiotik untuk infeksi bakteri saluran napas dan kulit'),
(20, 'Gabapentin', 'Mengobati nyeri saraf dan kejang tertentu');

INSERT INTO pasien (nik, nama, nomorTelepon, tanggalLahir, email) VALUES
('3201012001010001', 'Andi Saputra', '081234567890', '2001-01-20', 'andi.saputra@gmail.com'),
('3201021999123456', 'Siti Nurhaliza', '081298765432', '1999-12-15', 'siti.nurhaliza@yahoo.com'),
('3201031998032109', 'Budi Santoso', '082112345678', '1998-03-21', 'budi.santoso@hotmail.com');

INSERT INTO perawat (nipPerawat, nama, nomorTelepon) VALUES
('198405162021011001', 'Rina Kurniawati', '085234567890'),
('198902102019051002', 'Dewi Lestari', '085678901234'),
('199003052018031003', 'Ahmad Fauzan', '082345678901');

INSERT INTO petugas_administrasi (nipPetugas, nama, nomorTelepon) VALUES
('197512152020111001', 'Eko Prasetyo', '081345678901'),
('198010012019081002', 'Lina Marlina', '085123456789'),
('198706232018091003', 'Hendra Wijaya', '082123456789');

INSERT INTO jadwal (idJadwal, hari, jamMulai, jamSelesai) VALUES
-- Monday
(1, 'Senin', '06:00:00', '11:00:00'),
(2, 'Senin', '15:00:00', '19:00:00'),
-- Tuesday
(3, 'Selasa', '06:00:00', '11:00:00'),
(4, 'Selasa', '15:00:00', '19:00:00'),
-- Wednesday
(5, 'Rabu', '06:00:00', '11:00:00'),
(6, 'Rabu', '15:00:00', '19:00:00'),
-- Thursday
(7, 'Kamis', '06:00:00', '11:00:00'),
(8, 'Kamis', '15:00:00', '19:00:00'),
-- Friday
(9, 'Jumat', '06:00:00', '11:00:00'),
(10, 'Jumat', '15:00:00', '19:00:00'),
-- Saturday
(11, 'Sabtu', '06:00:00', '11:00:00'),
(12, 'Sabtu', '15:00:00', '19:00:00'),
-- Sunday
(13, 'Minggu', '06:00:00', '11:00:00');

INSERT INTO klinik (idKlinik, spesialisasi) VALUES
(1, 'Penyakit Dalam'),
(2, 'Kardiologi'),
(3, 'Pediatri'),
(4, 'Ortopedi'),
(5, 'Dermatologi');

INSERT INTO dokter (nipDokter, nama, idKlinik, tarif, kuotaBefore, kuotaToday) VALUES
-- Klinik Penyakit Dalam
('198305162021011001', 'Dr. Andi Pratama, Sp.PD', 1, 250000, 10, 8),
('197809122019081002', 'Dr. Siti Hidayati, Sp.PD', 1, 275000, 12, 10),
-- Klinik Kardiologi
('197506202018031003', 'Dr. Budi Santoso, Sp.JP', 2, 300000, 15, 13),
('198012152020111004', 'Dr. Lina Kusuma, Sp.JP', 2, 325000, 10, 8),
-- Klinik Pediatri
('199010052019051005', 'Dr. Dewa Mahardika, Sp.A', 3, 200000, 20, 18),
('198702012017071006', 'Dr. Maya Wulandari, Sp.A', 3, 220000, 18, 15),
-- Klinik Ortopedi
('197906162018081007', 'Dr. Fauzi Rahman, Sp.OT', 4, 350000, 12, 9),
('198508172019101008', 'Dr. Sri Wahyuni, Sp.OT', 4, 360000, 10, 8),
-- Klinik Dermatologi
('198312012020021009', 'Dr. Arif Nugroho, Sp.KK', 5, 280000, 15, 12),
('197611302018091010', 'Dr. Indah Permata, Sp.KK', 5, 300000, 10, 7);

INSERT INTO jadwal_dokter (nipDokter, idJadwal, ruangan) VALUES
-- Dr. Andi Pratama (Penyakit Dalam)
('198305162021011001', 1, '101'), -- Monday Morning
('198305162021011001', 4, '102'), -- Tuesday Afternoon
('198305162021011001', 7, '103'), -- Thursday Morning

-- Dr. Siti Hidayati (Penyakit Dalam)
('197809122019081002', 2, '104'), -- Monday Afternoon
('197809122019081002', 5, '105'), -- Wednesday Morning
('197809122019081002', 8, '106'), -- Thursday Afternoon

-- Dr. Budi Santoso (Kardiologi)
('197506202018031003', 3, '201'), -- Tuesday Morning
('197506202018031003', 6, '202'), -- Wednesday Afternoon
('197506202018031003', 9, '203'), -- Friday Morning

-- Dr. Lina Kusuma (Kardiologi)
('198012152020111004', 4, '204'), -- Tuesday Afternoon
('198012152020111004', 7, '205'), -- Thursday Morning
('198012152020111004', 10, '206'), -- Friday Afternoon

-- Dr. Dewa Mahardika (Pediatri)
('199010052019051005', 5, '301'), -- Wednesday Morning
('199010052019051005', 8, '302'), -- Thursday Afternoon
('199010052019051005', 11, '303'), -- Saturday Morning

-- Dr. Maya Wulandari (Pediatri)
('198702012017071006', 6, '304'), -- Wednesday Afternoon
('198702012017071006', 9, '305'), -- Friday Morning
('198702012017071006', 12, '306'), -- Saturday Afternoon

-- Dr. Fauzi Rahman (Ortopedi)
('197906162018081007', 7, '307'), -- Thursday Morning
('197906162018081007', 10, '308'), -- Friday Afternoon
('197906162018081007', 13, '309'), -- Sunday Morning

-- Dr. Sri Wahyuni (Ortopedi)
('198508172019101008', 8, '310'), -- Thursday Afternoon
('198508172019101008', 11, '401'), -- Saturday Morning
('198508172019101008', 2, '402'), -- Monday Afternoon

-- Dr. Arif Nugroho (Dermatologi)
('198312012020021009', 1, '403'), -- Monday Morning
('198312012020021009', 5, '404'), -- Wednesday Morning
('198312012020021009', 9, '405'), -- Friday Morning

-- Dr. Indah Permata (Dermatologi)
('197611302018091010', 2, '406'), -- Monday Afternoon
('197611302018091010', 6, '407'), -- Wednesday Afternoon
('197611302018091010', 12, '408'); -- Saturday Afternoon


INSERT INTO pendaftaran (idPendaftaran, tanggalPendaftaran, tanggalKunjungan, nikPasien, nipDokter, idJadwal) 
VALUES 
-- For Andi Saputra
(1, '2024-12-01', '2024-12-02', '3201012001010001', '198305162021011001', 1),  -- Dr. Andi Pratama
(2, '2024-12-05', '2024-12-06', '3201012001010001', '198012152020111004', 7),  -- Dr. Lina Kusuma

-- For Siti Nurhaliza
(3, '2024-12-03', '2024-12-04', '3201021999123456', '197506202018031003', 3),  -- Dr. Budi Santoso
(4, '2024-12-07', '2024-12-08', '3201021999123456', '198508172019101008', 11),  -- Dr. Sri Wahyuni

-- For Budi Santoso
(5, '2024-12-02', '2024-12-03', '3201031998032109', '198305162021011001', 1),  -- Dr. Andi Pratama
(6, '2024-12-06', '2024-12-07', '3201031998032109', '198312012020021009', 9);  -- Dr. Arif Nugroho

INSERT INTO pemeriksaan (idPemeriksaan, idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan, nipPerawat) 
VALUES 
-- For Andi Saputra
(1, 1, '120/80', 170.5, 70.2, 36.5, 'Sakit kepala ringan, badan terasa lelah', '198405162021011001'),
(2, 2, '130/85', 169.2, 68.5, 37.0, 'Batuk berdahak, demam', '198902102019051002'),

-- For Siti Nurhaliza
(3, 3, '125/80', 165.0, 60.0, 36.7, 'Pusing dan mual', '199003052018031003'),
(4, 4, '120/75', 163.0, 58.5, 36.3, 'Nyeri punggung bawah, kelelahan', '198902102019051002'),

-- For Budi Santoso
(5, 5, '140/90', 175.0, 80.0, 37.5, 'Sakit di dada, sesak napas', '199003052018031003'),
(6, 6, '125/80', 172.0, 75.0, 36.5, 'Flu biasa', '199003052018031003');

INSERT INTO diagnosa (idDiagnosa, idPemeriksaan, nipDokter, hasilDiagnosa, catatanDokter) 
VALUES 
-- For Andi Saputra
(1, 1, '198305162021011001', 'Sakit kepala ringan, kemungkinan akibat kelelahan', 'Anjurkan pasien untuk banyak istirahat'),
(2, 2, '198012152020111004', 'Bronkitis akut, disertai demam', 'Pasien dianjurkan untuk istirahat dan diberikan obat flu'),

-- For Siti Nurhaliza
(3, 3, '197506202018031003', 'Vertigo ringan', 'Pasien disarankan untuk tidur cukup dan menghindari gerakan mendadak'),
(4, 4, '198508172019101008', 'Nyeri otot punggung, mungkin akibat postur yang salah', 'Pasien dianjurkan untuk melakukan peregangan otot'),

-- For Budi Santoso
(5, 5, '198305162021011001', 'Angina, gejala nyeri dada dan sesak napas', 'Pasien perlu dilakukan pemeriksaan lebih lanjut'),
(6, 6, '198312012020021009', 'Flu ringan', 'Pasien dianjurkan untuk banyak minum air hangat');

-- Insert into resep table
INSERT INTO resep (idResep, idDiagnosa) 
VALUES 
(1, 1), -- For Sakit Kepala Ringan
(2, 2), -- For Bronkitis Akut
(3, 3), -- For Vertigo Ringan
(4, 5); -- For Angina


-- Insert into resep_obat table
INSERT INTO resep_obat (idResep, idObat, dosis, instruksiPenggunaan)
VALUES 
-- For Sakit Kepala Ringan
(1, 1, '1 tablet/8 jam', 'Konsumsi setelah makan dengan segelas air putih'),
-- For Bronkitis Akut
(2, 3, '1 tablet/8 jam', 'Konsumsi setelah makan hingga habis'),
(2, 1, '1 tablet/8 jam', 'Konsumsi setelah makan untuk meredakan demam'),
-- For Vertigo Ringan
(3, 1, '1 tablet/hari', 'Konsumsi setelah makan jika diperlukan'),
-- For Angina
(4, 9, '1 tablet/hari', 'Dikonsumsi pada waktu yang sama setiap hari untuk mencegah angina'),
(4, 17, '1 tablet/hari', 'Dikonsumsi setelah makan untuk mencegah pembekuan darah');

INSERT INTO metode_pembayaran (idMetode, metode) VALUES
	(1, 'Cash'),
	(2, 'Debit'),
	(3, 'Credit'),
	(4, 'BPJS'),
	(5, 'QRIS');

