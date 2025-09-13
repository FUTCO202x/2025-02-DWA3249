import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudiantesService {
    public void obtenerEstudiantes(Connection conn) throws SQLException{
        String sql="SELECT * FROM Estudiantes";
        var stm =conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont=0;
        while (rs.next()){
            System.out.println("ID: " + rs.getInt("ID") +
                               ", Nombre: " + rs.getString("Nombre") +
                               ", Apellido: " + rs.getString("Apellido") +
                               ", Estado Civil: " + rs.getString("estado_civil") +
                               ", Edad: " + rs.getInt("Edad") +
                               ", Correo: " + rs.getString("Correo"));
            cont++;
        }
        System.out.println("Cantidad de registros encontrados: " + cont);
    }

    public void insertarEstudiantes(Connection conn, String nombre, String apellido, String estadoCivil, int edad, String correo) throws SQLException{
        String sql="INSERT INTO Estudiantes (Nombre, Apellido, estado_civil, Edad, Correo) VALUES (?, ?, ?, ?, ?)";
        var stm=conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, estadoCivil);
        stm.setInt(4, edad);
        stm.setString(5, correo);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro insertado de forma correcta");
        }
        else {
            System.out.println("Error en inserción");
        }
    }

    public void actualizarEstudiantes(Connection conn, int id, String nombre, String apellido, String correo, int edad, String estadoCivil) throws SQLException{
        String sql="UPDATE Estudiantes SET nombre = ?, apellido= ?, Correo = ?, edad = ?, estado_civil=? WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);
        stm.setInt(6, id);
        int rs = stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro actualizado de forma correcta");
        }
        else {
            System.out.println("Error en la actualización");
        }
    }

    public void eliminarEstudiantes(Connection conn, int id) throws SQLException{
        String sql="DELETE FROM Estudiantes WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setInt(1, id);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro eliminado de forma correcta");
        }
        else {
            System.out.println("Error en la eliminación");
        }
    }

    public void consultarCorreo(Connection conn, String correo, int id) throws SQLException{
        String sql="SELECT * FROM Estudiantes WHERE correo = ? AND ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setString(1, correo);
        stm.setInt(2, id);
        ResultSet rs=stm.executeQuery();
        if(rs.next()) {
            System.out.println("Registro encontrado: ID: " + rs.getInt("ID") +
                               ", Nombre: " + rs.getString("Nombre") +
                               ", Apellido: " + rs.getString("Apellido") +
                               ", Estado Civil: " + rs.getString("estado_civil") +
                               ", Edad: " + rs.getInt("Edad") +
                               ", Correo: " + rs.getString("Correo"));
        }
        else {
            System.out.println("No se encontró el registro");
        }
    }

    public void eliminarEstudiantes(Connection conn) throws SQLException{
        String sql="DELETE FROM Estudiantes WHERE ID=10";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro eliminado de forma correcta");
        }
        else {
            System.out.println("Error en la eliminacion");
        }
    }

    public void consultarCorreo(Connection conn) throws SQLException{
        String sql="SELECT * FROM Estudiantes WHERE correo = 'andres123@gmail.com' AND ID=10";
        var stm=conn.prepareStatement(sql);
        ResultSet rs=stm.executeQuery();
        if(rs.next()) {
            System.out.println("Registro obtenido de forma correcta");
        }
        else {
            System.out.println("Error en la obtencion");
        }
    }
}