import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class student_service {
    private Scanner in = new Scanner(System.in);

    public void obtenerNumeroPersonas(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Persona";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont = 0;
        while (rs.next()) {
            cont++;
        }
        System.out.println("Cantidad de registros encontrados: " + cont);
    }

    public void insertarPersona(Connection conn) throws SQLException {
        System.out.println("Digite nombre de la persona: ");
        String nombre = in.nextLine();
        System.out.println("Digite apellido de la persona: ");
        String apellido = in.nextLine();
        System.out.println("Digite el estado civil de la persona: ");
        String estadoCivil = in.nextLine();

        String sql = "INSERT INTO Persona (Nombre, Apellido, EstadoCivil) VALUES (?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, estadoCivil);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro insertado de forma correcta");
        } else {
            System.out.println("Error en inserción");
        }
    }

    public void actualizarPersona(Connection conn) throws SQLException {
        System.out.println("Digite el nuevo estado civil: ");
        String estadoCivil = in.nextLine();
        System.out.println("Digite el ID de la persona a actualizar: ");
        int id = in.nextInt();
        in.nextLine(); // limpiar buffer

        String sql = "UPDATE Persona SET EstadoCivil=? WHERE ID=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, estadoCivil);
        stm.setInt(2, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro actualizado de forma correcta");
        } else {
            System.out.println("Error en la actualización");
        }
    }

    public void eliminarPersona(Connection conn) throws SQLException {
        System.out.print("Digite el ID de la persona a eliminar: ");
        int id = in.nextInt();
        in.nextLine(); // limpiar buffer

        String sql = "DELETE FROM Persona WHERE ID=?";
        var stm = conn.prepareStatement(sql);
        stm.setInt(1, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro eliminado de forma correcta");
        } else {
            System.out.println("Error en la eliminación");
        }
    }

    public void obtenerPersonas(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Persona";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("Nombre");
            String apellido = rs.getString("Apellido");
            String estadoCivil = rs.getString("EstadoCivil");
            System.out.println(String.format("Mi id es %d, mi nombre completo es %s %s y mi estado civil es %s",
                    id, nombre, apellido, estadoCivil));
        }
        System.out.println("Consulta finalizada");
    }
}
