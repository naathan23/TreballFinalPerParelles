import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarLibro extends JFrame {
    public JTextField tituloField;
    public JTextField autorField;
    public JTextField isbnField;
    public JTextField editorialField;
    public JTextField añoPublicacionField;
    public JTextField categoriaField;

    public AgregarLibro() {
        setTitle("agregar libro");
        setSize(300, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel tituloLabel = new JLabel("titulo:");
        tituloLabel.setBounds(20, 20, 80, 25);
        add(tituloLabel);

        tituloField = new JTextField();
        tituloField.setBounds(100, 20, 160, 25);
        add(tituloField);

        JLabel autorLabel = new JLabel("autor:");
        autorLabel.setBounds(20, 50, 80, 25);
        add(autorLabel);

        autorField = new JTextField();
        autorField.setBounds(100, 50, 160, 25);
        add(autorField);

        JLabel isbnLabel = new JLabel("isbn:");
        isbnLabel.setBounds(20, 80, 80, 25);
        add(isbnLabel);

        isbnField = new JTextField();
        isbnField.setBounds(100, 80, 160, 25);
        add(isbnField);

        JLabel editorialLabel = new JLabel("editorial:");
        editorialLabel.setBounds(20, 110, 80, 25);
        add(editorialLabel);

        editorialField = new JTextField();
        editorialField.setBounds(100, 110, 160, 25);
        add(editorialField);

        JLabel añoPublicacionLabel = new JLabel("año:");
        añoPublicacionLabel.setBounds(20, 140, 150, 25);
        add(añoPublicacionLabel);

        añoPublicacionField = new JTextField();
        añoPublicacionField.setBounds(100, 140, 160, 25);
        add(añoPublicacionField);

        JLabel categoriaLabel = new JLabel("categoria:");
        categoriaLabel.setBounds(20, 170, 80, 25);
        add(categoriaLabel);

        categoriaField = new JTextField();
        categoriaField.setBounds(100, 170, 160, 25);
        add(categoriaField);

        JButton agregarButton = new JButton("agregar");
        agregarButton.setBounds(100, 210, 100, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });
    }

    public void agregarLibro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        String editorial = editorialField.getText();
        String añoPublicacionstr = añoPublicacionField.getText();
        String categoria = categoriaField.getText();
        String estado = "disponible";

        int añoPublicacion;

        try {
            añoPublicacion = Integer.parseInt(añoPublicacionstr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "el año de publicacion debe ser un numero entero.", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Libro nuevoLibro = new Libro(0, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);

        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO libros (titulo, autor, isbn, editorial, año_publicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, nuevoLibro.mostrarTitulo());
            stmt.setString(2, nuevoLibro.mostrarAutor());
            stmt.setString(3, nuevoLibro.mostrarISBN());
            stmt.setString(4, nuevoLibro.mostrarEditorial());
            stmt.setInt(5, nuevoLibro.mostrarAñoPublicacion());
            stmt.setString(6, nuevoLibro.mostrarCategoria());
            stmt.setString(7, nuevoLibro.mostrarEstado());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "libro agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "no se pudo agregar el libro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al agregar el libro: " + ex.getMessage());
        }
    }
    
}