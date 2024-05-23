public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private int añoPublicacion;
    private String categoria;
    private String estado;

    public Libro(int id, String titulo, String autor, String isbn, String editorial, int añoPublicacion, String categoria, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int mostrarId() {
        return id;
    }

    public void ponerId(int id) {
        this.id = id;
    }

    public String mostrarTitulo() {
        return titulo;
    }

    public void ponerTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String mostrarAutor() {
        return autor;
    }

    public void ponerAutor(String autor) {
        this.autor = autor;
    }

    public String mostrarISBN() {
        return isbn;
    }

    public void ponerISBN(String isbn) {
        this.isbn = isbn;
    }

    public String mostrarEditorial() {
        return editorial;
    }

    public void ponerEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int mostrarAñoPublicacion() {
        return añoPublicacion;
    }

    public void ponerAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String mostrarCategoria() {
        return categoria;
    }

    public void ponerCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String mostrarEstado() {
        return estado;
    }

    public void ponerEstado(String estado) {
        this.estado = estado;
    }
}