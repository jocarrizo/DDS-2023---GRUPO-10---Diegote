new Vue({
    el: '#aperturaIncidenteForm',
    data: {
        entidades: [], // Aquí se almacenarán las entidades obtenidas de la API
        establecimientos: [],
        servicios: [],
        selectedEntity: null // Almacenará la entidad seleccionada por el usuario
    },
    methods: {
        async getEntities() {
            try {
                const response = await fetch('http://localhost:4567/api/apertura_incidenteHandler');
                const data = await response.json();
                this.entities = data.entities; // Asumiendo que la respuesta de la API tiene una propiedad 'entities'
            } catch (error) {
                console.error('Error al obtener las entidades:', error);
            }
        },
        getEstablecimientos() {
            // Aquí podrías realizar una llamada similar para obtener los establecimientos
            // basados en la entidad seleccionada y luego llenar otro select con esos datos
        }
    },
        created() {
            this.getEntities(); // Llama a la función para obtener las entidades al cargar la página
        },


});