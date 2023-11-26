function detectarCambios() {
    //Deberia devolverme un diccionario con únicamente con el id de las comunidades que fueron cambiadas
    const selectElements = document.querySelectorAll('select');
    const cambios = {};

    selectElements.forEach((select) => {
        const comunidad = select.parentElement.previousElementSibling.textContent.trim();
        const rolSeleccionado = select.value;
        cambios[comunidad] = (rolSeleccionado !== '1');
    });

    fetch('http://localhost:4567/administrarUsuarioAplicar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cambios)
    })
        .then(response => {
            if (response.ok) {
                console.log('Cambios enviados correctamente al servidor');
            } else {
                console.error('Hubo un problema al enviar los cambios al servidor');
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}
