import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    
    public static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
    
    public static final String USER = "mohid";
    public static final String PASS = "0000";
    
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}