import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesion extends JFrame {
    public JTextField nombreField;
    public JTextField apellidosField;

    public InicioSesion(){
        setTitle("inicio de sesion");
        setSize(380, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JButton iniciarSesionButton = new JButton("iniciar sesion");
        iniciarSesionButton.setBounds(20, 100, 130, 25);
        add(iniciarSesionButton);

        JButton limpiarButton = new JButton("limpiar");
        limpiarButton.setBounds(165, 100, 100, 25);
        add(limpiarButton);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String nombre = nombreField.getText();
                String apellidos = apellidosField.getText();
                autenticarUsuario(nombre, apellidos);
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                nombreField.setText(null);
                apellidosField.setText(null);
            }
        });
    }

    private void autenticarUsuario(String nombre, String apellidos){
        String query = "SELECT rol FROM usuarios WHERE nombre = ? AND apellidos = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    if ("bibliotecario".equalsIgnoreCase(rol)) {
                        new InterfazBibliotecario().setVisible(true);
                        dispose();
                    } else if ("lector".equalsIgnoreCase(rol)) {
                        new InterfazLector().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "usuario con rol incorrecto");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "nombre o apellidos incorrectos");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InicioSesion inicioSesion = new InicioSesion();
                inicioSesion.setVisible(true);
            }
        });
    }
}