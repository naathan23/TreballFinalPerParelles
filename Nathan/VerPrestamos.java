import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerPrestamos extends JFrame {
    public VerPrestamos() {
        setTitle("ver prestamos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        List<Prestamo> prestamos = obtenerPrestamos();
        for (Prestamo prestamo : prestamos) {
            textArea.append("id prestamo: " + prestamo.mostrarIdPrestamo() + "\n");
            textArea.append("id libro: " + prestamo.mostrarIdLibro() + "\n");
            textArea.append("id usuario: " + prestamo.mostrarIdUsuario() + "\n");
            textArea.append("fecha prestamo: " + prestamo.mostrarFechaPrestamo() + "\n");
            textArea.append("fecha retorno prevista: " + prestamo.mostrarFechaRetornoPrevista() + "\n");
            textArea.append("fecha retorno real: " + prestamo.mostrarFechaRetornoReal() + "\n");
            textArea.append("estado: " + prestamo.mostrarEstado() + "\n\n");
        }
    }

    public List<Prestamo> obtenerPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM prestamos")) {

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                int idLibro = rs.getInt("id_libro");
                int idUsuario = rs.getInt("id_usuario");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaRetornoPrevista = rs.getString("fecha_retorno_prevista");
                String fechaRetornoReal = rs.getString("fecha_retorno_real");
                String estado = rs.getString("estado");

                Prestamo prestamo = new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo,
                        fechaRetornoPrevista, fechaRetornoReal, estado);
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }
}