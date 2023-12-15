var app = new Vue({
    el: "#aperturaIncidenteForm",
    data: {
        selectedEntity: "",
        selectedSubEntity: "",
        entities: [],
        subEntities: [],
        services: [],
        selectedService: null,
        observaciones: ""
    },
    methods: {
        loadEntities: function() {
            fetch("http://localhost:4567/api/Entidades")
                .then(response => {
                    if (!response.ok){
                        throw new Error("Error al cargar las entidades");
                    }
                    return response.json();
                })
                .then(data => {
                    this.entities = data;
                })
                .catch(error => {

                    console.error( error);
                });
        },

        loadSubEntities: function() {
            if (!this.selectedEntity) {
                return;
            }
            fetch(`http://localhost:4567/api/Establecimientos/${this.selectedEntity}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error al cargar las subentidades");
                    }
                    return response.json();
                })
                .then(data => {
                    // Actualizar la lista de subentidades (establecimientos)
                    this.subEntities = data;
                })
                .catch(error => {
                    // Manejar errores
                    console.error("Error al cargar las subentidades: ", error);
                });
        },

        loadServices: function() {
            if (!this.selectedEntity) {
                return;
            }

            fetch(`http://localhost:4567/api/Servicios/${this.selectedSubEntity}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error al cargar las subentidades");
                    }
                    return response.json();
                })
                .then(data => {
                    this.services = data;
                })
                .catch(error => {

                    console.error("Error al cargar las subentidades: ", error);
                });
        },
        abrirIncidente: function () {
            fetch('http://localhost:4567/api/abrirIncidente',{
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    idSesion: localStorage.getItem('IDSESION'),
                    idServicio: this.selectedService.idServicio,
                    idPerfil: JSON.parse(localStorage.getItem("PERFIL_ACTUAL")).idPerfil,
                    idEntidad: this.selectedEntity,
                    observaciones: this.observaciones
                })
            })
                .then(response => {
                    //logica de respuesta
                    if(response.status === 201)
                        window.location.reload()
                    else
                        throw new Error("Respuesta innesperada");
                })
        }
    },
    mounted: function() {
        // Cargar las entidades al cargar la página
        this.loadEntities();
    }
});