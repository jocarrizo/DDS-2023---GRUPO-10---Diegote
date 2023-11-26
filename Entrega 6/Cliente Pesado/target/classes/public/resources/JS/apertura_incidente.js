
var app = new Vue({
    el: "#aperturaIncidenteForm",
    data: {
        selectedEntity: "",
        selectedSubEntity: "",
        entities: [],
        subEntities: [],
        services: []
    },
    methods: {
        loadEntities: function() {
            fetch("http://localhost:4567/api/Entidades")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error al cargar las entidades");
                    }
                    return response.json();
                })
                .then(data => {

                    this.entities = data;
                })
                .catch(error => {

                    console.error("Error al cargar las entidades: ", error);
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
                    this.subEntities = data;
                })
                .catch(error => {

                    console.error("Error al cargar las subentidades: ", error);
                });
        }
    },
    mounted: function() {
        // Cargar las entidades al cargar la página
        this.loadEntities();
    }
});