public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private String añoPublicacion;
    private String categoria;
    private String estado;

    public Libro(String titulo, String autor, String isbn, String editorial, String añoPublicacion, String categoria, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.categoria = categoria;
        this.estado = estado;
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

    public String mostrarAñoPublicacion() {
        return añoPublicacion;
    }

    public void ponerAñoPublicacion(String añoPublicacion) {
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