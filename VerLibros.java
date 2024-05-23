import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerLibros extends JFrame {
    public VerLibros() {
        setTitle("ver libros");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        List<Libro> libros = obtenerLibros();
        for (Libro libro : libros) {
            textArea.append("titulo: " + libro.mostrarTitulo() + "\n");
            textArea.append("autor: " + libro.mostrarAutor() + "\n");
            textArea.append("isbn: " + libro.mostrarISBN() + "\n");
            textArea.append("editorial: " + libro.mostrarEditorial() + "\n");
            textArea.append("año: " + libro.mostrarAñoPublicacion() + "\n");
            textArea.append("categoria: " + libro.mostrarCategoria() + "\n");
            textArea.append("estado: " + libro.mostrarEstado() + "\n\n");
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM libros")) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                String editorial = rs.getString("editorial");
                int añoPublicacion = rs.getInt("año_publicacion");
                String categoria = rs.getString("categoria");
                String estado = rs.getString("estado");

                Libro libro = new Libro(0, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }
    
}