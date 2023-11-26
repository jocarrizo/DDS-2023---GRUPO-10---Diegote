function enviarArchivos(fileInputId) {
    var file = document.getElementById(fileInputId).files[0];

    if (file) {
        var formData = new FormData();
        formData.append(fileInputId, file);

        fetch('localhost:4567/cargaMasiva', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                }
                throw new Error('Error al enviar el archivo.');
            })
            .then(data => {
                console.log(data); // Puedes hacer algo con la respuesta del servidor
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        console.log('Por favor selecciona un archivo.');
    }
}
