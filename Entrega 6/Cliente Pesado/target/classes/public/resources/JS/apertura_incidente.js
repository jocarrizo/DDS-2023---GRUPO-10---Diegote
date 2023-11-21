
var app = new Vue({
    el: "#aperturaIncidenteForm",
    data: {
        selectedEntity: "",
        selectedSubEntity: "",
        entities: [],       // Lista de Entidades
        subEntities: [],    // Lista de Subentidades
        services: []        // Lista de Servicios
    },
    methods: {
        // Método para cargar las Subentidades desde el backend
        loadSubEntities: function() {
            // Validar que se haya seleccionado una entidad
            if (!this.selectedEntity) {
                return;
            }
            // Pasar el ID de la entidad como parámetro en la URL
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
        // Método para cargar los Servicios desde el backend
        loadServices: function() {
            // Lógica para cargar los Servicios desde el backend utilizando this.selectedSubEntity
            // Actualiza this.services con la respuesta del backend
        },
        // Método para cargar las Entidades desde el backend
        loadEntities: function() {
            fetch("http://localhost:4567/api/Entidades")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error al cargar las entidades");
                    }
                    return response.json();
                })
                .then(data => {
                    // Actualizar la lista de entidades
                    this.entities = data;
                })
                .catch(error => {
                    // Manejar errores
                    console.error("Error al cargar las entidades: ", error);
                });
        }
    },
    mounted: function() {
        // Cargar las entidades al cargar la página
        this.loadEntities();
    }
});