
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteService {

    public void insertarEstudiante(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Digite nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Digite apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Digite correo: ");
        String correo = sc.nextLine();
        System.out.print("Digite edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil = sc.nextLine();

        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);

        int rs = stm.executeUpdate();
        System.out.println(rs > 0 ? "Estudiante insertado correctamente." : "Error al insertar estudiante.");
    }

    public void actualizarEstudiante(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Digite el correo del estudiante a actualizar: ");
        String correo = sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Nueva edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo estado civil: ");
        String estadoCivil = sc.nextLine();

        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setInt(3, edad);
        stm.setString(4, estadoCivil);
        stm.setString(5, correo);

        int rs = stm.executeUpdate();
        System.out.println(rs > 0 ? "Estudiante actualizado correctamente." : "No se encontró estudiante con ese correo.");
    }

    public void eliminarEstudiante(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Digite el correo del estudiante a eliminar: ");
        String correo = sc.nextLine();

        String sql = "DELETE FROM estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);

        int rs = stm.executeUpdate();
        System.out.println(rs > 0 ? "Estudiante eliminado." : "No se encontró estudiante con ese correo.");
    }

    public void consultarEstudiantes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado Civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        }
    }

    public void consultarPorCorreo(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Digite el correo del estudiante: ");
        String correo = sc.nextLine();

        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado Civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        } else {
            System.out.println("No se encontró estudiante con ese correo.");
        }
    }
}
