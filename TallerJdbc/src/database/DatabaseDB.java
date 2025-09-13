package database;
import java.sql.*;

public class DatabaseDB {
    private String url = "jdbc:postgresql://localhost:5432/Escuela";
    private String userName="postgres";
    private String password="123456";


    public Connection conexionDb(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a PostgreSQL");
            e.printStackTrace();
        }
        return conexion;
    }

    public void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                e.printStackTrace();
            }
        }
    }
    
}
