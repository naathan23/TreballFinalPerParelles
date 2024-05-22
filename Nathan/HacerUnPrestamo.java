import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class HacerUnPrestamo extends JFrame {
    private JTextField nombreLibroField;
    private JTextField nombreUsuarioField;
    private JTextField fechaPrestamoField;
    private JTextField fechaRetornoPrevistaField;

    public HacerUnPrestamo() {
        setTitle("hacer un prestamo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nombreLibroLabel = new JLabel("nombre del libro:");
        nombreLibroLabel.setBounds(20, 20, 150, 25);
        add(nombreLibroLabel);

        nombreLibroField = new JTextField();
        nombreLibroField.setBounds(220, 20, 150, 25);
        add(nombreLibroField);

        JLabel nombreUsuarioLabel = new JLabel("nombre del usuario:");
        nombreUsuarioLabel.setBounds(20, 60, 150, 25);
        add(nombreUsuarioLabel);

        nombreUsuarioField = new JTextField();
        nombreUsuarioField.setBounds(220, 60, 150, 25);
        add(nombreUsuarioField);

        JLabel fechaPrestamoLabel = new JLabel("fecha de prestamo:");
        fechaPrestamoLabel.setBounds(20, 100, 150, 25);
        add(fechaPrestamoLabel);

        fechaPrestamoField = new JTextField();
        fechaPrestamoField.setBounds(220, 100, 150, 25);
        add(fechaPrestamoField);

        JLabel fechaRetornoPrevistaLabel = new JLabel("fecha de retorno prevista:");
        fechaRetornoPrevistaLabel.setBounds(20, 140, 200, 25);
        add(fechaRetornoPrevistaLabel);

        fechaRetornoPrevistaField = new JTextField();
        fechaRetornoPrevistaField.setBounds(220, 140, 150, 25);
        add(fechaRetornoPrevistaField);

        JButton hacerPrestamoButton = new JButton("hacer prestamo");
        hacerPrestamoButton.setBounds(100, 200, 150, 25);
        add(hacerPrestamoButton);

        hacerPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerPrestamo();
            }
        });
    }

    public void hacerPrestamo() {
        String nombreLibro = nombreLibroField.getText();
        String nombreUsuario = nombreUsuarioField.getText();
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaRetornoPrevista = fechaRetornoPrevistaField.getText();
        String estadoPrestamo = "activo";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            int idLibro = obtenerIdLibro(conn, nombreLibro);
            int idUsuario = obtenerIdUsuario(conn, nombreUsuario);

            if (idLibro != -1 && idUsuario != -1) {
                Prestamo nuevoPrestamo = new Prestamo(0, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, null, estadoPrestamo);

                try (PreparedStatement stmtPrestamo = conn.prepareStatement(
                        "INSERT INTO prestamos (id_libro, id_usuario, fecha_prestamo, fecha_retorno_prevista, estado) VALUES (?, ?, ?, ?, ?)")) {
                    stmtPrestamo.setInt(1, nuevoPrestamo.mostrarIdLibro());
                    stmtPrestamo.setInt(2, nuevoPrestamo.mostrarIdUsuario());
                    stmtPrestamo.setString(3, nuevoPrestamo.mostrarFechaPrestamo());
                    stmtPrestamo.setString(4, nuevoPrestamo.mostrarFechaRetornoPrevista());
                    stmtPrestamo.setString(5, estadoPrestamo);

                    int filasAfectadas = stmtPrestamo.executeUpdate();

                    if (filasAfectadas > 0) {
                        if (actualizarEstadoLibro(conn, idLibro, "prestado")) {
                            JOptionPane.showMessageDialog(this, "prestamo realizado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(this, "no se pudo actualizar el estado del libro");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "no se pudo realizar el prestamo");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "no se encontro el libro o el usuario especificado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "error al realizar el prestamo: " + e.getMessage());
        }
    }

    public int obtenerIdLibro(Connection conn, String nombreLibro) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT id_libro FROM libros WHERE titulo = ?")) {
            stmt.setString(1, nombreLibro);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("id_libro") : -1;
        }
    }

    public int obtenerIdUsuario(Connection conn, String nombreUsuario) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT id_usuario FROM usuarios WHERE nombre = ?")) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("id_usuario") : -1;
        }
    }

    public boolean actualizarEstadoLibro(Connection conn, int idLibro, String nuevoEstado) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE libros SET estado = ? WHERE id_libro = ?")) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idLibro);
            return stmt.executeUpdate() > 0;
        }
    }
}