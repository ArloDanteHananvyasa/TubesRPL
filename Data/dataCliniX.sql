INSERT INTO pasien (nik, nama, nomorTelepon, tanggalLahir, gender, email, password) VALUES
('3201012001010001', 'Arlo Dante', '081296000921', '2001-01-20', 'pria', 'arlo.dante@gmail.com', 'password'),
('3201021999123456', 'Siti Nurhaliza', '081298765432', '1999-12-15', 'wanita', 'siti.nurhaliza@yahoo.com', 'password'),
('3201031998032109', 'Budi Santoso', '082112345678', '1998-03-21',  'pria', 'budi.santoso@hotmail.com', 'password');

INSERT INTO perawat (nipPerawat, nama, nomorTelepon, password) VALUES
('198405162021011001', 'Rina Kurniawati', '081', 'password'),
('198902102019051002', 'Dewi Lestari', '085678901234', 'password'),
('199003052018031003', 'Ahmad Fauzan', '082345678901', 'password');

INSERT INTO petugas_administrasi (nipPetugas, nama, nomorTelepon, password) VALUES
('197512152020111001', 'Eko Prasetyo', '082', 'password'),
('198010012019081002', 'Lina Marlina', '085123456789', 'password'),
('198706232018091003', 'Hendra Wijaya', '082123456789', 'password');

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

INSERT INTO dokter (nipDokter, nama, idKlinik, tarif, kuota, password, nomorTelepon) VALUES
-- Klinik Penyakit Dalam
('198305162021011001', 'Dr. Andi Pratama, Sp.PD', 1, 250000, 10, 'password', '083'),
('197809122019081002', 'Dr. Siti Hidayati, Sp.PD', 1, 275000, 12, 'password', '087654321098'),
-- Klinik Kardiologi
('197506202018031003', 'Dr. Budi Santoso, Sp.JP', 2, 300000, 15, 'password', '082345678901'),
('198012152020111004', 'Dr. Lina Kusuma, Sp.JP', 2, 325000, 10, 'password', '085678901234'),
-- Klinik Pediatri
('199010052019051005', 'Dr. Dewa Mahardika, Sp.A', 3, 200000, 20, 'password', '089012345678'),
('198702012017071006', 'Dr. Maya Wulandari, Sp.A', 3, 220000, 18, 'password', '086789012345'),
-- Klinik Ortopedi
('197906162018081007', 'Dr. Fauzi Rahman, Sp.OT', 4, 350000, 12, 'password', '084567890123'),
('198508172019101008', 'Dr. Sri Wahyuni, Sp.OT', 4, 360000, 10, 'password', '088901234567'),
-- Klinik Dermatologi
('198312012020021009', 'Dr. Arif Nugroho, Sp.KK', 5, 280000, 15, 'password', '083456789012'),
('197611302018091010', 'Dr. Indah Permata, Sp.KK', 5, 300000, 10, 'password', '089876543210');

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

INSERT INTO metode_pembayaran (idMetode, metode) VALUES
	(1, 'Cash'),
	(2, 'Debit'),
	(3, 'Credit'),
	(4, 'BPJS'),
	(5, 'QRIS');

INSERT INTO pendaftaran (tanggalPendaftaran, tanggalKunjungan, nikPasien, nipDokter, idJadwal, sudahDatang, idMetode) 
VALUES 
-- For Andi Saputra
('2024-12-01', '2024-12-02', '3201012001010001', '198305162021011001', 1, true, 1),  -- Dr. Andi Pratama
('2024-12-05', '2024-12-06', '3201012001010001', '198012152020111004', 7, true, 2),  -- Dr. Lina Kusuma

-- For Siti Nurhaliza
('2024-12-03', '2024-12-04', '3201021999123456', '197506202018031003', 3, true, 3),  -- Dr. Budi Santoso
('2024-12-07', '2024-12-08', '3201021999123456', '198508172019101008', 11, true, 2),  -- Dr. Sri Wahyuni

-- For Budi Santoso
('2024-12-02', '2024-12-03', '3201031998032109', '198305162021011001', 1, true, 5),  -- Dr. Andi Pratama
('2024-12-06', '2024-12-07', '3201031998032109', '198312012020021009', 9, true, 4),  -- Dr. Arif Nugroho
('2024-12-06', '2024-12-07', '3201012001010001', '198312012020021009', 9, true, 4);  -- Dr. Arif Nugroho

INSERT INTO pemeriksaan (idPendaftaran, tekananDarah, tinggiBadan, beratBadan, suhuBadan, keluhan, nipPerawat) 
VALUES 
-- For Andi Saputra
(1, '120/80', 170.5, 70.2, 36.5, 'Sakit kepala ringan, badan terasa lelah', '198405162021011001'),
(2, '130/85', 169.2, 68.5, 37.0, 'Batuk berdahak, demam', '198902102019051002'),

-- For Siti Nurhaliza
(3, '125/80', 165.0, 60.0, 36.7, 'Pusing dan mual', '199003052018031003'),
(4, '120/75', 163.0, 58.5, 36.3, 'Nyeri punggung bawah, kelelahan', '198902102019051002'),

-- For Budi Santoso
(5, '140/90', 175.0, 80.0, 37.5, 'Sakit di dada, sesak napas', '199003052018031003'),
(6, '125/80', 172.0, 75.0, 36.5, 'Flu biasa', '199003052018031003');

INSERT INTO diagnosa (idPemeriksaan, nipDokter, hasilDiagnosa, catatanDokter) 
VALUES 
-- For Andi Saputra
(1, '198305162021011001', 'Sakit kepala ringan, kemungkinan akibat kelelahan', 'Anjurkan pasien untuk banyak istirahat'),
(2, '198012152020111004', 'Bronkitis akut, disertai demam', 'Pasien dianjurkan untuk istirahat dan diberikan obat flu'),

-- For Siti Nurhaliza
(3, '197506202018031003', 'Vertigo ringan', 'Pasien disarankan untuk tidur cukup dan menghindari gerakan mendadak'),
(4, '198508172019101008', 'Nyeri otot punggung, mungkin akibat postur yang salah', 'Pasien dianjurkan untuk melakukan peregangan otot'),

-- For Budi Santoso
(5, '198305162021011001', 'Angina, gejala nyeri dada dan sesak napas', 'Pasien perlu dilakukan pemeriksaan lebih lanjut'),
(6, '198312012020021009', 'Flu ringan', 'Pasien dianjurkan untuk banyak minum air hangat');

-- Insert into resep table
INSERT INTO resep (idDiagnosa) 
VALUES 
(1), -- For Sakit Kepala Ringan
(2), -- For Bronkitis Akut
(3), -- For Vertigo Ringan
(5); -- For Angina


-- Insert into resep_obat table
INSERT INTO resep_obat (idResep, namaObat, jumlah, dosis, instruksiPenggunaan)
VALUES 
-- For Sakit Kepala Ringan
(1, 'Paracetamol', '14 tablet', '1 tablet/8 jam', 'Konsumsi setelah makan dengan segelas air putih'),
-- For Bronkitis Akut
(2, 'Amoxicillin', '10 tablet', '1 tablet/8 jam', 'Konsumsi setelah makan hingga habis'),
(2, 'Paracetamol', '8 tablet', '1 tablet/8 jam', 'Konsumsi setelah makan untuk meredakan demam'),
-- For Vertigo Ringan
(3, 'Paracetamol', '14 tablet', '1 tablet/hari', 'Konsumsi setelah makan jika diperlukan'),
-- For Angina
(4, 'Amlodipine', '7 tablet', '1 tablet/hari', 'Dikonsumsi pada waktu yang sama setiap hari untuk mencegah angina'),
(4, 'Clopidogrel', '7 tablet', '1 tablet/hari', 'Dikonsumsi setelah makan untuk mencegah pembekuan darah');

