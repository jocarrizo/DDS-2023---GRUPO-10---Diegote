let fileContent; // Variable para almacenar el contenido del archivo
let tipoArchivo;

function prepararArchivos(fileInputId) {
    file = document.getElementById(fileInputId).files[0];
    if (file) {
        // Crear un lector de archivos
        let reader = new FileReader();

        // Leer el contenido del archivo cuando esté listo
        reader.onload = function (e) {
            fileContent = e.target.result; // Almacenar el contenido del archivo
            tipoArchivo = fileInputId;
        };

        // Leer el archivo como texto
        reader.readAsText(file);
    }
}

function enviarArchivos() {
    if (fileContent) {
        // Crear un objeto con la clave 'data' y el valor del contenido del archivo
        let requestBody = {
            tipo: tipoArchivo,
            data: fileContent
        };

        fetch('http://localhost:4567/cargaMasivaLiviano/cargar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody) // Convertir el objeto a JSON
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
