package models;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private String estadoCivil;

    public Estudiante(int id, String nombre, String apellido, String correo, int edad, String estadoCivil) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }

    public Estudiante(String nombre, String apellido, String correo, int edad, String estadoCivil) {
        this(0, nombre, apellido, correo, edad, estadoCivil);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public int getEdad() { return edad; }
    public String getEstadoCivil() { return estadoCivil; }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", edad=" + edad +
                ", estadoCivil='" + estadoCivil + '\'' +
                '}';
    }
}

