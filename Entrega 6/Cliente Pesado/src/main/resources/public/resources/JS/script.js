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
