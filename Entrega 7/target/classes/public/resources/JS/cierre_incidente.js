new Vue({
    el: '#cierreIncidenteForm',
    data: {
        incidentes: [],
        incidenteSeleccionado: null,
        errorMessage: null, // New variable to store the error message
    },
    methods: {
        cargarIncidentes: function () {
            fetch(`http://localhost:4567/api/Incidentes`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error al cargar los Incidentes");
                    }
                    return response.json();
                })
                .then(data => {
                    // Actualizar la lista de incidentes
                    this.incidentes = data;
                })
                .catch(error => {
                    // Manejar errores
                    console.error("Error al cargar los Incidentes ", error);
                });
        },
        informarCierre: function (idIncidente) {
            if (idIncidente !== null) {
                fetch(`http://localhost:4567/api/cerrarIncidente`, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        idSesion : localStorage.getItem("IDSESION"),
                        idIncidente : idIncidente,
                        idPerfil : JSON.parse(localStorage.getItem("PERFIL_ACTUAL")).idPerfil
                    })
                }).then(response => {
                    if (response.status === 400){
                        this.errorMessage = "Error: Respuesta inesperada"; // Set error message
                        throw new Error("Respuesta inesperada");
                    }
                    else{

                        this.errorMessage = null; // Clear error message on success
                        const index = this.incidentes.findIndex(incidente => incidente.id_incidente === idIncidente);
                        if (index !== -1) {
                            this.incidentes.splice(index, 1);
                        }
                    }
                }).catch(error => {
                    // Manejo de errores
                    console.error("Error: ", error);
                })
                console.log('Informar cierre del incidente:', idIncidente);
            } else {
                alert('Por favor, seleccione un incidente antes de informar el cierre.');
            }
        }
    },
    mounted: function () {
        // Cargar las entidades al cargar la página
        this.cargarIncidentes();
    }
});