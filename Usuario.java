public class Usuario {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String rol;
    private String fechaRegistro;

    public Usuario(String nombre, String apellidos, String email, String telefono, String rol, String fechaRegistro) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public String mostrarNombre() {
        return nombre;
    }

    public void ponerNombre(String nombre) {
        this.nombre = nombre;
    }

    public String mostrarApellidos() {
        return apellidos;
    }

    public void ponerApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String mostrarEmail() {
        return email;
    }

    public void ponerEmail(String email) {
        this.email = email;
    }

    public String mostrarTelefono() {
        return telefono;
    }

    public void ponerTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String mostrarRol() {
        return rol;
    }

    public void ponerRol(String rol) {
        this.rol = rol;
    }

    public String mostrarFechaRegistro() {
        return fechaRegistro;
    }

    public void ponerFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}