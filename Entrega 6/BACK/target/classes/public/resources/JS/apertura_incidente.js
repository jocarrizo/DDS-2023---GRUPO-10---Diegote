new Vue({
    el: '#aperturaIncidenteForm',
    data: {
        entidades: [], // Aquí se almacenarán las entidades obtenidas de la API
        establecimientos:[],
        servicios:[],
        selectedEntity: null // Almacenará la entidad seleccionada por el usuario
    },
    methods: {
        async getEntidades() {
            try {
                const response = await fetch('http://localhost:4567/api/apertura_incidenteHandler');
                const data = await response.json();
                this.entidades = data.entidades; // Asumiendo que la respuesta de la API tiene una propiedad 'entities'
            } catch (error) {
                console.error('Error al obtener las entidades:', error);
            }
        },
        async getEstablecimientos() {
            try {
                const response = await fetch('http://localhost:4567/api/apertura_incidenteHandler');
                const data = await response.json();
                this.establecimientos = data.establecimientos; // Asumiendo que la respuesta de la API tiene una propiedad 'entities'
            } catch (error) {
                console.error('Error al obtener las entidades:', error);
            }
        },
        async getServicios() {
            try {
                const response = await fetch('http://localhost:4567/api/apertura_incidenteHandler');
                const data = await response.json();
                this.servicios = data.servicios; // Asumiendo que la respuesta de la API tiene una propiedad 'entities'
            } catch (error) {
                console.error('Error al obtener las entidades:', error);
            }
        },
    },
    created() {
        this.getEntidades(); // Llama a la función para obtener las entidades al cargar la página
        this.getEstablecimientos();
        this.getServicios();
    }
});