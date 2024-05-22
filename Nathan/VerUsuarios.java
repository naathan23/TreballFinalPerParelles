import java.sql.*;
import javax.swing.*;
import java.util.*;

public class VerUsuarios extends JFrame{
    public VerUsuarios() {
        setTitle("ver usuarios");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        List<Usuario> usuarios = obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            textArea.append("nombre: " + usuario.mostrarNombre() + "\n");
            textArea.append("apellidos: " + usuario.mostrarApellidos() + "\n");
            textArea.append("email: " + usuario.mostrarTelefono() + "\n");
            textArea.append("telefono: " + usuario.mostrarTelefono() + "\n");
            textArea.append("rol: " + usuario.mostrarRol() + "\n");
            textArea.append("fecha de registro: " + usuario.mostrarFechaRegistro() + "\n\n");
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String rol = rs.getString("rol");
                String fecha = rs.getString("fecha_registro");

                Usuario usuario = new Usuario(nombre, apellidos, email, telefono, rol, fecha);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}