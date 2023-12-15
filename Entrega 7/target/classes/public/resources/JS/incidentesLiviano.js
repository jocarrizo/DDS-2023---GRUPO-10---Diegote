function elegirFiltro(filtroSeleccionado) {
    fetch('http://localhost:4567/cookies/incidentesLiviano/filtro', {
        method: 'POST',
        body: filtroSeleccionado
    })
        .then(response => {
            if (response.ok) {
                return fetch('http://localhost:4567/incidentesLiviano/tabla');
            } else {
                throw new Error('Error al enviar el filtro.');
            }
        })
        .then(response => response.text())
        .then(data => {
            document.getElementById('tablaIncidentesContainer').innerHTML = data;
            console.log('Tabla de incidentes actualizada con filtro');
            //cargarTabla(); // Llamada a cargarTabla() después de actualizar la tabla
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function elegirComunidad(comunidadId){
    fetch('http://localhost:4567/cookies/incidentesLiviano/comunidad', {
        method: 'POST',
        body: comunidadId
    })
        .then(response => {
            if (response.ok) {
                return fetch('http://localhost:4567/incidentesLivianoFiltro.html');
            } else {
                throw new Error('Error al enviar la comunidad.');
            }
        })
        .then(response => response.text())
        .then(data => {
            document.getElementById('filtroIncidentesContainer').innerHTML = data;
            console.log('Filtro de incidentes actualizada');
            cargarTabla(); // Llamada a cargarTabla() después de actualizar el filtro
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function cargarTabla() {
    fetch('http://localhost:4567/incidentesLiviano/tabla')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar la tabla.');
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('tablaIncidentesContainer').innerHTML = data;
            console.log('Tabla de incidentes actualizada');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
