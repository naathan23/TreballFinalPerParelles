import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class AgregarUsuario extends JFrame{
    public JTextField nombreField;
    public JTextField apellidosField;
    public JTextField rolField;
    public JTextField emailField;
    public JTextField fechaField;
    public JTextField telefonoField;

    public AgregarUsuario(){
        setTitle("agregar usuario");
        setSize(300, 350);
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
        apellidosField.setBounds(140, 60, 130, 25);
        add(apellidosField);

        JLabel telefonoLabel = new JLabel("telefono: ");
        telefonoLabel.setBounds(20, 100, 100, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(140, 100, 130, 25);
        add(telefonoField);

        JLabel fechaLabel = new JLabel("fecha: ");
        fechaLabel.setBounds(20, 140, 120, 25);
        add(fechaLabel);

        fechaField = new JTextField();
        fechaField.setBounds(140, 140, 130, 25);
        add(fechaField);

        JLabel emailLabel = new JLabel("email: ");
        emailLabel.setBounds(20, 180, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(140, 180, 130, 25);
        add(emailField);

        JLabel rolLabel = new JLabel("rol: ");
        rolLabel.setBounds(20, 220, 100, 25);
        add(rolLabel);

        rolField = new JTextField();
        rolField.setBounds(140, 220, 130, 25);
        add(rolField);

        JButton agregarButton = new JButton("agregar");
        agregarButton.setBounds(100, 270, 100, 25);
        add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                agregarUsuario();
            }  
        });
    }
    
    public void agregarUsuario(){
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String telefono = telefonoField.getText();
        String fecha = fechaField.getText();
        String email = emailField.getText();
        String rol = rolField.getText();

        Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, telefono, rol, fecha);

        try (Connection conn = ConexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios(nombre, apellidos, email, telefono, rol, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)")){
            stmt.setString(1, nuevoUsuario.mostrarNombre());
            stmt.setString(2, nuevoUsuario.mostrarApellidos());
            stmt.setString(3, nuevoUsuario.mostrarEmail());
            stmt.setString(4, nuevoUsuario.mostrarTelefono());
            stmt.setString(5, nuevoUsuario.mostrarRol());
            stmt.setString(6, nuevoUsuario.mostrarFechaRegistro());
            int filasAfectadas = stmt.executeUpdate();

            if(filasAfectadas >0){
                JOptionPane.showMessageDialog(this, "asuario agregado correctamente");
            } else{
                JOptionPane.showMessageDialog(this, "no se ha podido agregar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al agregar el usuario" + e.getMessage());
        }


    }
}