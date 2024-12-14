// scripts.js

function addResep() {
  const namaObat = document.getElementById('nama-obat').value;
  const jumlahDosis = document.getElementById('jumlah-dosis').value;
  const waktuMinum = document.getElementById('waktu-minum').value;

  // Validasi input
  if (namaObat === '' || jumlahDosis === '' || waktuMinum === '') {
    alert('Semua field harus diisi!');
    return;
  }

  // Tambahkan data ke tabel
  const table = document.getElementById('resep-list');
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${namaObat}</td>
    <td>${jumlahDosis} kali/hari</td>
    <td>${waktuMinum === 'sebelum-makan' ? 'Sebelum Makan' : 'Sesudah Makan'}</td>
    <td><button class="action-btn" onclick="deleteRow(this)">Hapus</button></td>
  `;
  table.appendChild(newRow);

  // Bersihkan input form
  document.getElementById('nama-obat').value = '';
  document.getElementById('jumlah-dosis').value = '';
  document.getElementById('waktu-minum').value = '';
}

function deleteRow(button) {
  // Hapus baris tabel
  const row = button.parentNode.parentNode;
  row.parentNode.removeChild(row);
}
