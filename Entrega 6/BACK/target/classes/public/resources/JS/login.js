
 var app = new Vue({
    el: "#appVue",
    data: {
        username: "",
        password: "",
        mensaje: "",
    },
    methods: {
        // LOGIN
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
            }else if(response.status === 200)
                return response.json();
            else
                throw new Error("Respuesta innesperada");
        })
        .then(datos => {
            var idSesion = datos.idSesion
            var idUsuario = datos.idUsuario

            localStorage.setItem("IDSESION", idSesion)
            localStorage.setItem("IDUSUARIO", idUsuario) //guarda ID
            this.mensaje ="Login logrado. Redirigiendote..."
            window.location.href = "home.html"
        }).catch(error => {
            //Manejo de errores
            console.error("Error: ",error)
        })
    },
    }
})