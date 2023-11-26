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

function handleFileSelect(inputId, labelId, otherLabelId, btnId, otherBtnId) {
    var input = document.getElementById(inputId);
    var label = document.getElementById(labelId);
    var submitButton = document.getElementById('submitButton');
    var otherLabel = document.getElementById(otherLabelId);
    var btn = document.getElementById(btnId);
    var otherBtn = document.getElementById(otherBtnId);

    if (input.files.length > 0) {
        label.innerHTML = 'Archivo seleccionado: ' + input.files[0].name;
        input.style.display = 'none';  // Oculta el botón de carga de archivos
        otherLabel.style.display = 'none';  // Oculta el otro botón de carga de archivos
        btn.style.display = 'none';  // Oculta el botón actual
        otherBtn.style.display = 'none';  // Oculta el otro botón
        submitButton.style.display = 'inline-block';  // Muestra el botón de enviar
    }
}