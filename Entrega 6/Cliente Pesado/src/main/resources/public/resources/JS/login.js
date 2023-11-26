var app = new Vue({
    el: "#appVue",
    data: {
        username: "",
        password: "",
        mensaje: "",
    },
    methods: {
        login: function () {
            fetch("http://localhost:4567/api/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: this.username,
                    password: this.password
                })
            })
                .then(response => {
                    if (response.status === 401) {
                        this.mensaje = "Nombre de usuario o contraseña incorrectos.";
                        throw new Error("Desautorizado");
                    } else if(response.status === 200) {
                        return response.json();
                    } else {
                        throw new Error("Respuesta inesperada");
                    }
                })
                .then(datos => {
                    // Almacenar datos en localStorage
                    localStorage.setItem("IDSESION", datos.idSesion);
                    localStorage.setItem("PERFILES", JSON.stringify(datos.listadoPerfiles));

                    // Elegir perfil de inicio de la sesion.
                    if (datos.listadoPerfiles && datos.listadoPerfiles.length > 0) {
                        const primerPerfil = datos.listadoPerfiles[0];
                        localStorage.setItem("PERFIL_ACTUAL", JSON.stringify(primerPerfil));
                    }

                    // Redirigir a la página home.html
                    this.mensaje = "Login logrado. Redirigiendo...";
                    window.location.href = "home.html";
                })
                .catch(error => {
                    // Manejo de errores
                    console.error(error);
                })
        }
    }
});