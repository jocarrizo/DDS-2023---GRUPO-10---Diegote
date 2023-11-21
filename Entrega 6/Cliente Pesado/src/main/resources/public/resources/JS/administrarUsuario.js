var app = new Vue({
    el: "#administrarUsuario",
    data: {
        IDSESION: ''
    },
    methods: {
        sendID: function() {
            this.IDSESION = localStorage.getItem('IDSESION');

            fetch('http://localhost:4567/administrarUsuario', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ IDSESION: IDSESION })
            })
                .then(response => {
                    if (response.ok) {
                        console.log('IDSESSION enviado correctamente al servidor');
                    } else {
                        throw new Error('Error al enviar IDSESSION al servidor');
                    }
                })
                .catch(error => {
                    console.error("Error al enviar IDSESSION al servidor: ", error);
                });
        },
        detectarCambios: function() {
            const selectElements = document.querySelectorAll('select');
            const cambios = {
                IDSESION: this.IDSESION
            };
            selectElements.forEach((select) => {
                const comunidad = select.parentElement.previousElementSibling.textContent.trim();
                const rolSeleccionado = select.value;
                cambios[comunidad] = (rolSeleccionado !== '1');
            });

            fetch('http://localhost:4567/administrarUsuarioAplicar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cambios)
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Cambios enviados correctamente al servidor');
                    } else {
                        console.error('Hubo un problema al enviar los cambios al servidor');
                    }
                })
                .catch(error => {
                    console.error('Error en la solicitud:', error);
                });
        }
    },
    mounted: function() {
        // Cargar las entidades al cargar la página
        this.sendID();
    }
});
