import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EliminarLibro extends JFrame {
    public JTextField tituloField;
    public JButton eliminarButton;

    public EliminarLibro(){
        setTitle("eliminar libro");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel tituloLabel = new JLabel("titulo:");
        tituloLabel.setBounds(20, 20, 80, 25);
        add(tituloLabel);

        tituloField = new JTextField();
        tituloField.setBounds(100, 20, 160, 25);
        add(tituloField);

        eliminarButton = new JButton("eliminar");
        eliminarButton.setBounds(100, 50, 100, 25);
        add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
    }

    public void eliminarLibro() {
        String titulo = tituloField.getText();
        Libro libro = buscarLibro(titulo);

        if (libro != null) {
            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM libros WHERE titulo = ?")) {
                stmt.setString(1, titulo);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "libro eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "no se pudo eliminar el libro.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "error al eliminar el libro: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "no se encontro ningun libro con ese titulo.");
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