import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarUsuario extends JFrame {
    public JTextField nombreField;
    public JTextField apellidosField;
    public JTextField rolField;
    public JTextField emailField;
    public JTextField fechaField;
    public JTextField telefonoField;
    public JButton buscarButton;
    public JButton modificarButton;
    
    public ModificarUsuario(){
        setTitle("modificar usuario");
        setSize(400, 350);
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
        
        modificarButton = new JButton("modificar");
        modificarButton.setBounds(280, 20, 100, 25);
        add(modificarButton);
    
        JButton buscarButton = new JButton("buscar");
        buscarButton.setBounds(140, 270, 100, 25);
        add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                buscarUsuarioNombreApellidos();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
            }
        });
    }

    public void modificarUsuario(){
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String telefono = telefonoField.getText();
        String fecha = fechaField.getText();
        String email = emailField.getText();
        String rol = rolField.getText();

        try(Connection conn = ConexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET nombre = ?, apellidos = ?, telefono = ?, fecha_registro = ?, email = ?, rol = ? WHERE nombre = ? AND apellidos = ? ")){
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, telefono);
            stmt.setString(4, fecha);
            stmt.setString(5, email);
            stmt.setString(6, rol);
            stmt.setString(7, nombre);
            stmt.setString(8, apellidos);

            int filasAfectadas = stmt.executeUpdate();

            if(filasAfectadas > 0){
                JOptionPane.showMessageDialog(this, "usario modificado correctamente");
            } else{
                JOptionPane.showMessageDialog(this, "no se ha podido modificar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al modificar el usuario: " + e.getMessage());
        }
    }

    public void buscarUsuarioNombreApellidos(){
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        Usuario usuario= buscarUsuario(nombre, apellidos);

        if (usuario != null){
            nombreField.setText(usuario.mostrarNombre());
            apellidosField.setText(usuario.mostrarApellidos());
            emailField.setText(usuario.mostrarEmail());
            telefonoField.setText(usuario.mostrarTelefono());
            rolField.setText(usuario.mostrarRol());
            fechaField.setText(usuario.mostrarFechaRegistro());

            nombreField.setEditable(true);
            apellidosField.setEditable(true);
            emailField.setEditable(true);
            telefonoField.setEditable(true);
            rolField.setEditable(true);
            fechaField.setEditable(true);
            modificarButton.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(this, "no se ha encontrado ningun usuario con los credenciales especificados");
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
            JOptionPane.showMessageDialog(this, "error al buscar el usuario: " + ex.getMessage());
        }
        return null;
    }
    
}