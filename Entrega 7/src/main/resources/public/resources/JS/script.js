document.addEventListener('DOMContentLoaded', function() {
    const closeButtons = document.querySelectorAll('.close-incident');

    closeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const incidentRow = this.parentNode.parentNode;
            incidentRow.remove();
            // Aquí puedes añadir código adicional para marcar el incidente como cerrado en el backend.
        });
    });

    document.getElementById('aperturaIncidenteForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const usuario = document.getElementById('usuario').value;
        const observaciones = document.getElementById('observaciones').value;

        // Crear una nueva fila en la tabla de incidentes
        const tbody = document.querySelector('.incident-table tbody');
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td>ID</td>
            <td>${usuario}</td>
            <td>Fecha y Hora Actual</td>
            <td>${observaciones}</td>
            <td><button class="close-incident">Dar de Baja</button></td>
        `;
        tbody.appendChild(newRow);

        // Limpiar campos del formulario
        document.getElementById('observaciones').value = '';
    });
});
customElements.define('yus-html',
    class extends HTMLElement {
        constructor() {
            super();
        }
        connectedCallback() {
            fetch(this.getAttribute("src"))
                .then(r => r.text())
                .then(t => {
                    let parser = new DOMParser();
                    let html = parser.parseFromString(t, "text/html");
                    this.innerHTML = html.body.innerHTML;
                }).catch(e => console.error(e));

        }
    });


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