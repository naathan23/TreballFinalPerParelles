import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class VerUnUsuario extends JFrame{
    public JTextField nombreField;
    public JTextField apellidosField;
    public JTextArea textArea;

    public VerUnUsuario(){
        setTitle("buscar usuario");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nombreLabel = new JLabel("nombre: ");
        nombreLabel.setBounds(20, 20, 100, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(140, 20, 130, 25);
        add(nombreField);

        JLabel apellidosLabel = new JLabel("apellidos: ");
        apellidosLabel.setBounds(20, 60, 100, 25);
        add(apellidosLabel);

        apellidosField = new JTextField();
        apellidosField.setBounds(140, 70, 130, 25);
        add(apellidosField);

        JButton buscarButton = new JButton("buscar");
        buscarButton.setBounds(280, 20, 150, 25);
        add(buscarButton);

        textArea = new JTextArea();
        textArea.setBounds(10, 150, 300, 150);
        textArea.setEditable(false);
        add(textArea);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                buscarUsuarioNombreApellidos();
            }
        });
    }

    public void buscarUsuarioNombreApellidos(){
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        Usuario usuario = buscarUsuario(nombre, apellidos);
        if (usuario != null){
            textArea.setText("nombre: " + usuario.mostrarNombre() + "\n" + "apellidos: " + usuario.mostrarApellidos() + "\n" + "email: " + usuario.mostrarEmail() + "telefono: " + usuario.mostrarTelefono() + "\n" + "rol: " + usuario.mostrarRol() + "\n" + "fecha de registro: " + usuario.mostrarFechaRegistro());
        }else{
            textArea.setText("no se ha encontrado el usuario con los credenciales especificados");
        }
    }


    public Usuario buscarUsuario(String nombre, String apellidos) {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND apellidos = ?")) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String rol = rs.getString("rol");
                String fecha = rs.getString("fecha_registro");
                return new Usuario(nombre, apellidos, email, telefono, rol, fecha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al buscar el libro: " + ex.getMessage());
        }
        return null;
    }

    



}