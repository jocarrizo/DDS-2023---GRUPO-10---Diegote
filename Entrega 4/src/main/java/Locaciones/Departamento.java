package Locaciones;

public class Departamento {

    public Provincia provincia;
    public int id;
    public String nombre;

    @Override
    public String toString() {
        return "Departamento{" +
                "provincia=" + provincia +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
