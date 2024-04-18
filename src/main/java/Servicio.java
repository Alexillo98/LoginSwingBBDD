import java.sql.SQLException;

public class Servicio {
    private static java.sql.Connection con;

    public Servicio()throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/usuarios";
        con = java.sql.DriverManager.getConnection(host);
    }
    public java.sql.Connection getConnection(){
        return con;
    }
}
