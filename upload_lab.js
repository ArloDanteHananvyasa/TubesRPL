document.addEventListener('DOMContentLoaded', function () {
    const uploadButton = document.getElementById('uploadButton');
    const actualFileInput = document.getElementById('actual-file');
    const textInput = document.getElementById('uploadHasilLab');

    uploadButton.addEventListener('click', function () {
        // Memunculkan file explorer
        actualFileInput.click();
    });

    actualFileInput.addEventListener('change', function () {
        // Menampilkan nama file yang dipilih pada input text
        if (actualFileInput.files.length > 0) {
            textInput.value = actualFileInput.files[0].name;
        }
    });
});
