function detectarCambios() {
    const selectElements = document.querySelectorAll('select');
    const cambios = [];

    selectElements.forEach((select) => {
        const comunidadId = select.parentElement.parentElement.querySelector('td:first-child').textContent.trim();
        const rolSeleccionado = select.value;

        // Verificar si el rol seleccionado es diferente de '1' (hubo cambio)
        if (rolSeleccionado !== '1') {
            cambios.push(comunidadId); // Agregar el ID de la comunidad a la lista de cambios
        }
    });
    console.log(cambios);
    // Si hay cambios, enviar la lista de IDs al servidor mediante una solicitud POST
    if (cambios.length > 0) {
        fetch('http://localhost:4567/administrarUsuarioLivianoAplicar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cambios) // Enviar la lista de IDs como datos JSON
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