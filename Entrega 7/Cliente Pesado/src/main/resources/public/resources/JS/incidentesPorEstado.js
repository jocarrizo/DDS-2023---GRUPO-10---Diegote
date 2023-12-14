function elegirFiltro(filtroSeleccionado) {
    fetch('http://localhost:4567/cookies/incidentes/filtro', {
        method: 'POST',
        body: filtroSeleccionado
    })
        .then(response => {
            if (response.ok) {
                return fetch('http://localhost:4567/incidentes/tabla');
            } else {
                throw new Error('Error al enviar el filtro.');
            }
        })
        .then(response => response.text())
        .then(data => {
            document.getElementById('tablaIncidentesContainer').innerHTML = data;
            console.log('Tabla de incidentes actualizada con filtro');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function elegirComunidad(comunidadId){
    console.log(comunidadId);

    fetch('http://localhost:4567/cookies/incidentes/comunidad', {
                method: 'POST',
                body:comunidadId
            })
                .then(response => {
                    if (response.ok) {
                        return fetch('http://localhost:4567/incidentesPorEstadoFiltro.html');
                    } else {
                        throw new Error('Error al enviar la comunidad.');
                    }
                })
                .then(response => response.text())
                .then(data => {
                    document.getElementById('filtroIncidentesContainer').innerHTML = data;
                    console.log('Filtro de incidentes actualizada');
                })
                .catch(error => {
                    console.error('Error:', error);
                });
}