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
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function elegirComunidad(comunidadId){
    console.log(comunidadId);

    fetch('http://localhost:4567/cookies/incidentesLiviano/comunidad', {
                method: 'POST',
                body:comunidadId
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
                })
                .catch(error => {
                    console.error('Error:', error);
                });
}

var navbar = new Vue({
    el:"#navbar",
    data:{
        perfiles: null,
        perfilActual: null
    },
    mounted: function (){
        this.perfiles = JSON.parse(localStorage.getItem("PERFILES")) || []
        this.perfilActual = JSON.parse(localStorage.getItem("PERFIL_ACTUAL"))
    },
    methods: {
        guardarPerfilSeleccionado: function(perfil) {

            const indicePerfilActual = this.perfiles.findIndex(p => p.idPerfil === this.perfilActual.idPerfil)

            // Actualiza el valor anterior del perfil en la lista de perfiles
            if (indicePerfilActual !== -1) {
                this.perfiles.splice(indicePerfilActual, 1, JSON.parse(localStorage.getItem("PERFIL_ACTUAL")))
            }

            this.perfilActual = perfil;
            localStorage.setItem("PERFIL_ACTUAL", JSON.stringify(this.perfilActual))
            localStorage.setItem("PERFILES",JSON.stringify(this.perfiles))


            console.log(JSON.stringify(this.perfiles))

            console.log("Cambiando a perfil: ", perfil.nombrePerfil)
            window.location.reload()
        }
    }
})