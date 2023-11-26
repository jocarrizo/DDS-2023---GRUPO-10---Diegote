let file;
let formData;
function prepararArchivos(fileInputId){
    file = document.getElementById(fileInputId).files[0];
    if (file){
        formData = new FormData();
        formData.append(fileInputId, file);
    }
}

function enviarArchivos() {
    if (file) {

        fetch('http://localhost:4567/cargaMasiva/cargar', {
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


function handleFileSelect(inputId, labelId, otherLabelId, btnId, otherBtnId) {
    var input = document.getElementById(inputId);
    var label = document.getElementById(labelId);
    var submitButton = document.getElementById('submitButton');
    var otherLabel = document.getElementById(otherLabelId);
    var btn = document.getElementById(btnId);
    var otherBtn = document.getElementById(otherBtnId);

    if (input.files.length > 0) {
        label.innerHTML = 'Archivo seleccionado: ' + input.files[0].name;
        input.style.display = 'none';  // Oculta el botón de carga de archivos
        otherLabel.style.display = 'none';  // Oculta el otro botón de carga de archivos
        btn.style.display = 'none';  // Oculta el botón actual
        otherBtn.style.display = 'none';  // Oculta el otro botón
        submitButton.style.display = 'inline-block';  // Muestra el botón de enviar
    }
}