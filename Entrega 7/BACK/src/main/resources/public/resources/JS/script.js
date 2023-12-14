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