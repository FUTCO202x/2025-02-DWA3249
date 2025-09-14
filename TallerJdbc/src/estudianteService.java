import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class estudianteService {
    private final Scanner in = new Scanner(System.in);

    // Insertar estudiante
    public void insertarEstudiante(Connection conn) throws SQLException {
        System.out.print("Digite nombre: ");
        String nombre = in.nextLine();
        System.out.print("Digite apellido: ");
        String apellido = in.nextLine();
        System.out.print("Digite correo: ");
        String correo = in.nextLine();
        System.out.print("Digite edad: ");
        int edad = in.nextInt();
        in.nextLine(); // limpiar buffer
        System.out.print("Digite estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil = in.nextLine();

        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estadoCivil) VALUES (?,?,?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante insertado correctamente");
        } else {
            System.out.println("Error en la inserción");
        }
    }

    // Actualizar estudiante
    public void actualizarEstudiante(Connection conn) throws SQLException {
        System.out.print("Digite el ID del estudiante a actualizar: ");
        int id = in.nextInt();
        in.nextLine();

        System.out.print("Digite nuevo nombre: ");
        String nombre = in.nextLine();
        System.out.print("Digite nuevo apellido: ");
        String apellido = in.nextLine();
        System.out.print("Digite nueva edad: ");
        int edad = in.nextInt();
        in.nextLine();
        System.out.print("Digite nuevo estado civil: ");
        String estadoCivil = in.nextLine();

        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estadoCivil=? WHERE id=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setInt(3, edad);
        stm.setString(4, estadoCivil);
        stm.setInt(5, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante actualizado correctamente");
        } else {
            System.out.println("Error en la actualización");
        }
    }

    // Eliminar estudiante
    public void eliminarEstudiante(Connection conn) throws SQLException {
        System.out.print("Digite el ID del estudiante a eliminar: ");
        int id = in.nextInt();
        in.nextLine();

        String sql = "DELETE FROM estudiantes WHERE id=?";
        var stm = conn.prepareStatement(sql);
        stm.setInt(1, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante eliminado correctamente");
        } else {
            System.out.println("Error en la eliminación");
        }
    }

    // Consultar todos los estudiantes
    public void obtenerEstudiantes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estadoCivil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado Civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        }
        System.out.println("Consulta finalizada");
    }

    // Consultar estudiante por correo
    public void obtenerPorCorreo(Connection conn) throws SQLException {
        System.out.print("Digite el correo a buscar: ");
        String correo = in.nextLine();

        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estadoCivil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado Civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        } else {
            System.out.println("No se encontró un estudiante con ese correo.");
        }
    }
}
