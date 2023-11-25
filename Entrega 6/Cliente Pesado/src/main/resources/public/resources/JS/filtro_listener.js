let data = {
    IDSESION: localStorage.getItem('IDSESION'),
    FILTER: '',
    COMUNIDAD: ''
};
window.onload = function() {


    fetch('http://localhost:4567/incidentes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            // Manejar la respuesta del backend si es necesaria
            console.log('Mensaje enviado al backend al cargar la página');
        })
        .catch(error => {
            console.error('Error al enviar el mensaje al backend:', error);
        });

};

document.addEventListener('DOMContentLoaded', function () {
    const comunidadRadios = document.querySelectorAll('.comunidad-selection');

    // Almacenar el estado del radio seleccionado
    let selectedRadio = sessionStorage.getItem('selectedRadio');

    // Si hay un radio seleccionado almacenado, marcarlo
    if (selectedRadio !== null) {
        const radioToSelect = document.querySelector(`input[data-comunidad="${selectedRadio}"]`);
        if (radioToSelect) {
            radioToSelect.checked = true;
        }
    }

    comunidadRadios.forEach(function (radio) {
        radio.addEventListener('change', function () {
            if (this.checked) {
                const selectedComunidad = this.getAttribute('data-comunidad');
                sessionStorage.setItem('selectedRadio', selectedComunidad); // Almacenar la selección en sessionStorage
                location.reload(); // Recargar la página
            }
        });
    });
});



document.getElementById('Filtro').addEventListener('change', function() {
    // Obtener el valor seleccionado
    var selectedValue = this.value;
    var data = {
        IDSESION: localStorage.getItem('IDSESION'),
        FILTER: ''
    };

    if (selectedValue === 'Abiertos') {
        data.FILTER = 'abierto';
    } else if (selectedValue === 'Cerrados') {
        data.FILTER = 'cerrado';
    } else {
        data.FILTER = '';
    }

    // Guardar el valor seleccionado en localStorage
    localStorage.setItem('selectedValue', selectedValue);
    location.reload();
});
