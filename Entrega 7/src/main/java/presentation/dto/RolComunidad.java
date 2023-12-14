package presentation.dto;

public class RolComunidad {

    public String nombre;

    public long id;
    public String rol;
    public String norol;
    public String admin;

    public RolComunidad(String nombre,long id, Boolean esAfectado, Boolean esAdmin) {
        this.nombre = nombre;
        this.id = id;
        if (esAfectado) {
            this.rol = "Afectado";
            this.norol = "Observador";
        } else {
            this.rol = "Observador";
            this.norol = "Afectado";
        }

        if(esAdmin){
            this.admin = "Si";
        }
        else {
            this.admin = "No";
        }

    }

    public String getAdmin(){return admin;}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
