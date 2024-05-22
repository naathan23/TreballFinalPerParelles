import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class EliminarUsuario extends JFrame{
    public JTextField nombreField;
    public JTextField apellidosField;
    
    public EliminarUsuario(){
        setTitle("eliminar usuario");
        setSize(300, 200);
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

        JButton eliminarButton = new JButton("eliminar");
        eliminarButton.setBounds(140, 100, 130, 25);
        add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                eliminarUsuario();
            }
        });
    }

    public void eliminarUsuario(){
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();

        try (Connection conn = ConexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE nombre = ? AND apellidos = ?")) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "usuario eliminado con exito.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "no se ha podido eliminar el usuario");
            }          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error al eliminar el usuario: " + ex.getMessage());
        }
    }
}