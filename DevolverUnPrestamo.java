import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class DevolverUnPrestamo extends JFrame {
    private JTextField idPrestamoField;
    private JTextField fechaRetornoRealField;

    public DevolverUnPrestamo() {
        setTitle("devolver un prestamo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel idPrestamoLabel = new JLabel("id prestamo:");
        idPrestamoLabel.setBounds(20, 20, 160, 25);
        add(idPrestamoLabel);

        idPrestamoField = new JTextField();
        idPrestamoField.setBounds(180, 20, 130, 25);
        add(idPrestamoField);

        JLabel fechaRetornoRealLabel = new JLabel("fecha retorno real:");
        fechaRetornoRealLabel.setBounds(20, 60, 160, 25);
        add(fechaRetornoRealLabel);

        fechaRetornoRealField = new JTextField();
        fechaRetornoRealField.setBounds(180, 60, 130, 25);
        add(fechaRetornoRealField);

        JButton devolverPrestamoButton = new JButton("devolver prestamo");
        devolverPrestamoButton.setBounds(80, 100, 200, 25);
        add(devolverPrestamoButton);

        devolverPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverPrestamo();
            }
        });
    }

    private void devolverPrestamo() {
        int idPrestamo = Integer.parseInt(idPrestamoField.getText());
        String fechaRetornoReal = fechaRetornoRealField.getText();

        try (Connection conn = ConexionBD.obtenerConexion()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE prestamos SET fecha_retorno_real = ?, estado = 'completado' WHERE id_prestamo = ?")) {
                stmt.setString(1, fechaRetornoReal);
                stmt.setInt(2, idPrestamo);
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    try (PreparedStatement stmtActualizarLibro = conn.prepareStatement(
                            "UPDATE libros SET estado = 'disponible' WHERE id_libro = (SELECT id_libro FROM prestamos WHERE id_prestamo = ?)")) {
                        stmtActualizarLibro.setInt(1, idPrestamo);
                        int filasActualizadas = stmtActualizarLibro.executeUpdate();
                        
                        if (filasActualizadas > 0) {
                            JOptionPane.showMessageDialog(this, "prestamo devuelto correctamente");
                        } else {
                            JOptionPane.showMessageDialog(this, "no se pudo actualizar el estado del libro");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "no se pudo devolver el prestamo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al devolver el prestamo: " + e.getMessage());
        }
    }
}