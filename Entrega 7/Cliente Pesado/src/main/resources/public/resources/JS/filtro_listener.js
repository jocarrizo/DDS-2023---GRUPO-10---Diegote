document.addEventListener('DOMContentLoaded', function() {
    // Función para obtener el valor de la comunidad seleccionada
    function obtenerComunidadSeleccionada() {
        const comunidadRadios = document.querySelectorAll('.comunidad-selection');
        let comunidadSeleccionada = -1; // Valor por defecto si no hay ninguna seleccionada

        comunidadRadios.forEach(radio => {
            if (radio.checked) {
                comunidadSeleccionada = radio.getAttribute('data-comunidad');
            }
        });

        return comunidadSeleccionada;
    }

    // Función para obtener el filtro seleccionado
    function obtenerFiltroSeleccionado() {
        const filtroSelect = document.getElementById('Filtro');
        return filtroSelect.value === 'Sin Filtro' ? '' : filtroSelect.value;
    }

    // Función para redirigir a la URL con los parámetros
    function redirigirConParametros() {
        const idUsuario = localStorage.getItem('IDUSUARIO');
        const comunidad = obtenerComunidadSeleccionada();
        const filtro = obtenerFiltroSeleccionado();

        let newURL = `https://localhost:4567/incidente/${idUsuario}/${comunidad}/${filtro}`;

        window.location.href = newURL;
    }

    // Eventos cuando se cambia la selección de comunidad o filtro
    const comunidadRadios = document.querySelectorAll('.comunidad-selection');
    comunidadRadios.forEach(radio => {
        radio.addEventListener('change', redirigirConParametros);
    });

    const filtroSelect = document.getElementById('Filtro');
    filtroSelect.addEventListener('change', redirigirConParametros);
});
