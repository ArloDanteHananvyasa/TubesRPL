const modal = document.getElementById('editModal');
const editButtons = document.querySelectorAll('.action-button');
const closeButton = document.querySelector('.close-button');

editButtons.forEach(button => {
    button.addEventListener('click', () => {
        modal.style.display = 'flex'; // Tampilkan modal
    });
});

closeButton.addEventListener('click', () => {
    modal.style.display = 'none'; // Sembunyikan modal
});

window.addEventListener('click', (e) => {
    if (e.target === modal) {
        modal.style.display = 'none';
    }
});
