import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificarLibro extends JFrame {
    public JTextField tituloField;
    public JTextField autorField;
    public JTextField isbnField;
    public JTextField editorialField;
    public JTextField añoPublicacionField;
    public JTextField categoriaField;
    public JTextField estadoField;
    public JButton buscarButton;
    public JButton modificarButton;

    public ModificarLibro(){
        setTitle("modificar libro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel tituloLabel = new JLabel("titulo:");
        tituloLabel.setBounds(10, 10, 80, 25);
        add(tituloLabel);

        tituloField = new JTextField();
        tituloField.setBounds(100, 10, 160, 25);
        add(tituloField);

        JLabel autorLabel = new JLabel("autor:");
        autorLabel.setBounds(10, 40, 80, 25);
        add(autorLabel);

        autorField = new JTextField();
        autorField.setBounds(100, 40, 160, 25);
        add(autorField);

        JLabel isbnLabel = new JLabel("isbn:");
        isbnLabel.setBounds(10, 70, 80, 25);
        add(isbnLabel);

        isbnField = new JTextField();
        isbnField.setBounds(100, 70, 160, 25);
        add(isbnField);

        JLabel editorialLabel = new JLabel("editorial:");
        editorialLabel.setBounds(10, 100, 80, 25);
        add(editorialLabel);

        editorialField = new JTextField();
        editorialField.setBounds(100, 100, 160, 25);
        add(editorialField);

        JLabel añoLabel = new JLabel("año:");
        añoLabel.setBounds(10, 130, 80, 25);
        add(añoLabel);

        añoPublicacionField = new JTextField();
        añoPublicacionField.setBounds(100, 130, 160, 25);
        add(añoPublicacionField);

        JLabel categoriaLabel = new JLabel("categoria:");
        categoriaLabel.setBounds(10, 160, 80, 25);
        add(categoriaLabel);

        categoriaField = new JTextField();
        categoriaField.setBounds(100, 160, 160, 25);
        add(categoriaField);

        JLabel estadoLabel = new JLabel("estado:");
        estadoLabel.setBounds(10, 190, 80, 25);
        add(estadoLabel);

        estadoField = new JTextField();
        estadoField.setBounds(100, 190, 160, 25);
        add(estadoField);

        modificarButton = new JButton("modificar");
        modificarButton.setBounds(280, 10, 100, 25);
        add(modificarButton);

        buscarButton = new JButton("buscar");
        buscarButton.setBounds(100, 230, 100, 25);
        add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroPorNombre();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarLibro();
            }
        });
    }

    public void buscarLibroPorNombre() {
        String titulo = tituloField.getText();
        Libro libro = buscarLibro(titulo);
        if (libro != null) {
            autorField.setText(libro.mostrarAutor());
            isbnField.setText(libro.mostrarISBN());
            editorialField.setText(libro.mostrarEditorial());
            añoPublicacionField.setText(String.valueOf(libro.mostrarAñoPublicacion()));
            categoriaField.setText(libro.mostrarCategoria());
            estadoField.setText(libro.mostrarEstado());
            
            autorField.setEditable(true);
            isbnField.setEditable(true);
            editorialField.setEditable(true);
            añoPublicacionField.setEditable(true);
            categoriaField.setEditable(true);
            estadoField.setEditable(true);
            modificarButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "no se encontro ningun libro con ese nombre.");
        }
    }

    public void modificarLibro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        String editorial = editorialField.getText();
        int añoPublicacion = Integer.parseInt(añoPublicacionField.getText());
        String categoria = categoriaField.getText();
        String estado = estadoField.getText();
        
        try (Connection conn = ConexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("UPDATE libros SET autor = ?, isbn = ?, editorial = ?, año_publicacion = ?, categoria = ?, estado = ? WHERE titulo = ?")) {
            stmt.setString(1, autor);
            stmt.setString(2, isbn);
            stmt.setString(3, editorial);
            stmt.setInt(4, añoPublicacion);
            stmt.setString(5, categoria);
            stmt.setString(6, estado);
            stmt.setString(7, titulo);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "libro modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "no se pudo modificar el libro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al modificar el libro: " + ex.getMessage());
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
                return new Libro(titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al buscar el libro: " + ex.getMessage());
        }
        return null;
    }
    
}