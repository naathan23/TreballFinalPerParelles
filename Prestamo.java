public class Prestamo {
    private int idLibro;
    private int idUsuario;
    private String fechaPrestamo;
    private String fechaRetornoPrevista;
    private String fechaRetornoReal;
    private String estado;

    public Prestamo(int idPrestamo, int idLibro, int idUsuario, String fechaPrestamo, String fechaRetornoPrevista, String fechaRetornoReal, String estado) {
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    public int mostrarIdPrestamo() {
        return idLibro;
    }

    public void ponerIdPrestamo(int idLibro) {
        this.idLibro = idLibro;
    }

    public int mostrarIdLibro() {
        return idLibro;
    }

    public void ponerIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int mostrarIdUsuario() {
        return idUsuario;
    }

    public void ponerIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String mostrarFechaPrestamo() {
        return fechaPrestamo;
    }

    public void ponerFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String mostrarFechaRetornoPrevista() {
        return fechaRetornoPrevista;
    }

    public void ponerFechaRetornoPrevista(String fechaRetornoPrevista) {
        this.fechaRetornoPrevista = fechaRetornoPrevista;
    }

    public String mostrarFechaRetornoReal() {
        return fechaRetornoReal;
    }

    public void ponerFechaRetornoReal(String fechaRetornoReal) {
        this.fechaRetornoReal = fechaRetornoReal;
    }

    public String mostrarEstado() {
        return estado;
    }

    public void ponerEstado(String estado) {
        this.estado = estado;
    }
}