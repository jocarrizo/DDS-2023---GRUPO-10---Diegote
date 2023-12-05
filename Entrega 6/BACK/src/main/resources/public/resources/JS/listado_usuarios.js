new Vue({
    el: '#app',
    data: {
        selectedCommunity: null,
        comunidades: [
            { id: 1, name: 'Comunidad A' },
            { id: 2, name: 'Comunidad B' },
            // Agrega más comunidades si es necesario
        ],
        users: [
            { id: 1, username: 'Usuario 1', role: 'Rol 1' },
            { id: 2, username: 'Usuario 2', role: 'Rol 2' },
            // Agrega más usuarios si es necesario
        ]
    },
    methods: {
        removeUserFromCommunity(userId) {
            // Aquí deberías implementar la lógica para eliminar al usuario de la comunidad seleccionada
            console.log(`Eliminar usuario ${userId} de la comunidad ${this.selectedCommunity}`);
        },

        getComunidades: function (idUsuario) {
            fetch("http://localhost:4567/api/comunidades", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    idUsuario: idUsuario  // Usar el parámetro idUsuario aquí
                })
            })
                .then(response => {
                    if (response.status === 401) {
                        this.mensaje = "Nombre de usuario o contraseña incorrectos.";
                        throw new Error("Desautorizado");
                    } else if (response.status === 200) {
                        return response.json();
                    } else {
                        throw new Error("Respuesta inesperada");
                    }
                })
                .then(datos => {
                    // Aquí puedes manejar los datos recibidos, por ejemplo:
                    this.comunidades = datos; // Asignar los datos de comunidades a tu variable en Vue
                })
                .catch(error => {
                    // Manejo de errores
                    console.error("Error: ", error);
                })

    }}
});
