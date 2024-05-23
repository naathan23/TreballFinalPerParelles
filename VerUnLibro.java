import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerUnLibro extends JFrame {
    public JTextField tituloField;
    public JTextArea textArea;

    public VerUnLibro() {
        setTitle("buscar un libro");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nombreLabel = new JLabel("titulo:");
        nombreLabel.setBounds(10, 10, 80, 25);
        add(nombreLabel);

        tituloField = new JTextField();
        tituloField.setBounds(100, 10, 160, 25);
        add(tituloField);

        JButton buscarButton = new JButton("buscar");
        buscarButton.setBounds(270, 10, 100, 25);
        add(buscarButton);

        textArea = new JTextArea();
        textArea.setBounds(10, 50, 300, 150);
        textArea.setEditable(false);
        add(textArea);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroPorTitulo();
            }
        });
    }

    public void buscarLibroPorTitulo() {
        String titulo = tituloField.getText();
        Libro libro = buscarLibro(titulo);
        if (libro != null) {
            textArea.setText("titulo: " + libro.mostrarTitulo() + "\n" + "autor: " + libro.mostrarAutor() + "\n" + "isbn: " + libro.mostrarISBN() + "\n" + "editorial: " + libro.mostrarEditorial() + "\n" + "año: " + libro.mostrarAñoPublicacion() + "\n" + "categoria: " + libro.mostrarCategoria()+ "\n" + "estado: " + libro.mostrarEstado());
        } else {
            textArea.setText("no se encontro ningun libro con ese titulo.");
        }
    }

    public Libro buscarLibro(String titulo) {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM libros WHERE titulo = ?")) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                String editorial = rs.getString("editorial");
                int añoPublicacion = rs.getInt("año_publicacion");
                String categoria = rs.getString("categoria");
                String estado = rs.getString("estado");
                return new Libro(0, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}