package presentation.dto;

public class RolComunidad {

    public Long nombre;
    public String rol;
    public String norol;

    public RolComunidad(long nombre, Boolean esAfectado) {
        this.nombre = nombre;
        if (esAfectado) {
            this.rol = "Afectado";
            this.norol = "Observador";
        } else {
            this.rol = "Observador";
            this.norol = "Afectado";
        }
    }
    public Long getNombre() {
        return nombre;
    }

    public void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNorol() {
        return norol;
    }

    public void setNorol(String norol) {
        this.norol = norol;
    }



}
