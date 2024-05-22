import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class VerMisPrestamos extends JFrame {
    JTextField nombreField;
    JTextField apellidosField;
    JButton buscarButton;
    JTextArea resultadosArea;

    public VerMisPrestamos() {
        setTitle("ver mis prestamos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nombreLabel = new JLabel("nombre:");
        nombreLabel.setBounds(20, 20, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(100, 20, 150, 25);
        add(nombreField);

        JLabel apellidosLabel = new JLabel("apellidos:");
        apellidosLabel.setBounds(20, 60, 80, 25);
        add(apellidosLabel);

        apellidosField = new JTextField();
        apellidosField.setBounds(100, 60, 150, 25);
        add(apellidosField);

        buscarButton = new JButton("buscar");
        buscarButton.setBounds(280, 40, 100, 25);
        add(buscarButton);

        resultadosArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultadosArea);
        scrollPane.setBounds(20, 100, 460, 250);
        add(scrollPane);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPrestamos();
            }
        });
    }

    void buscarPrestamos() {
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmtUsuario = conn.prepareStatement(
                     "SELECT id_usuario FROM usuarios WHERE nombre = ? AND apellidos = ?")) {
            stmtUsuario.setString(1, nombre);
            stmtUsuario.setString(2, apellidos);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            if (rsUsuario.next()) {
                int idUsuario = rsUsuario.getInt("id_usuario");
                
                try (PreparedStatement stmtPrestamos = conn.prepareStatement(
                        "SELECT * FROM prestamos WHERE id_usuario = ?")) {
                    stmtPrestamos.setInt(1, idUsuario);
                    ResultSet rsPrestamos = stmtPrestamos.executeQuery();

                    resultadosArea.setText("");
                    
                    while (rsPrestamos.next()) {
                        int idPrestamo = rsPrestamos.getInt("id_prestamo");
                        int idLibro = rsPrestamos.getInt("id_libro");
                        String fechaPrestamo = rsPrestamos.getString("fecha_prestamo");
                        String fechaRetornoPrevista = rsPrestamos.getString("fecha_retorno_prevista");
                        String fechaRetornoReal = rsPrestamos.getString("fecha_retorno_real");
                        String estado = rsPrestamos.getString("estado");

                        resultadosArea.append("id prestamo: " + idPrestamo + "\n");
                        resultadosArea.append("id libro: " + idLibro + "\n");
                        resultadosArea.append("fecha prestamo: " + fechaPrestamo + "\n");
                        resultadosArea.append("fecha retorno prevista: " + fechaRetornoPrevista + "\n");
                        resultadosArea.append("fecha retorno real: " + fechaRetornoReal + "\n");
                        resultadosArea.append("estado: " + estado + "\n\n");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "usuario no encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al buscar prestamos: " + ex.getMessage());
        }
    }
}