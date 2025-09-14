import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteServices {
    private Scanner scanner = new Scanner(System.in);

    public void insertarEstudiante(Connection connection) throws SQLException {
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrease el apellido del estudiante:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el correo del estudiante:");
        String correo = scanner.nextLine();
        System.out.println("Ingrease la edad del estudiante:");
        int edad = scanner.nextInt();
        System.out.println("Ingrese estado civil del estudiante(SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO):");
        String estadoCivil = scanner.next().toUpperCase();
        scanner.nextLine();

        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, apellido);
        preparedStatement.setString(3, correo);
        preparedStatement.setInt(4, edad);
        preparedStatement.setString(5, estadoCivil);
        int rs = preparedStatement.executeUpdate();
        if(rs>0) {
            System.out.println("Registro insertado de forma correcta");
        }
        else {
            System.out.println("Error en insercion");
        }
    }

    public void actualizarEstudiante(Connection connection) throws SQLException {
        System.out.print("Ingrese el id de la persona a actualizar: ");
        int id=scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre=scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido: ");
        String apellido=scanner.nextLine();
        System.out.print("Ingrese el nuevo correo: ");
        String correo=scanner.nextLine();
        System.out.print("Ingrese la nueva edad: ");
        int edad=scanner.nextInt();
        System.out.print("Ingrese el nuevo estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil=scanner.next().toUpperCase();

        String sql="UPDATE estudiantes SET nombre=?, apellido=?, correo=?, edad=?, estado_civil=? WHERE ID=?";

        var stm=connection.prepareStatement(sql);
        stm.setObject(1,nombre);
        stm.setObject(2,apellido);
        stm.setObject(3,correo);
        stm.setObject(4,edad);
        stm.setObject(5,estadoCivil);
        stm.setObject(6,id);

        int rs=stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro actualizado");
        }
        else {
            System.out.println("Actualizacion fallida");
        }

    }

    public void eliminarEstudiante(Connection connection) throws SQLException {
        System.out.print("Ingrese el id de la persona a eliminar: ");
        int id=scanner.nextInt();

        String sql="DELETE FROM estudiantes WHERE ID=?";

        var stm=connection.prepareStatement(sql);
        stm.setObject(1,id);
        int rs=stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro eliminado");
        }
        else {
            System.out.println("Eiminacion fallida");
        }

    }

    public void consultarEstudiantes(Connection connection) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        var stm = connection.prepareStatement(sql);
        var rs = stm.executeQuery();
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Nombre: " + rs.getString("nombre") +
                    ", Apellido: " + rs.getString("apellido") +
                    ", Correo: " + rs.getString("correo") +
                    ", Edad: " + rs.getInt("edad") +
                    ", Estado Civil: " + rs.getString("estado_civil"));
        }
    }

    public void consultarEstudiantePorEmail(Connection connection) throws SQLException {
        System.out.print("Ingrese el correo del estudiante a consultar: ");
        String email = scanner.next();

        String sql = "SELECT * FROM estudiantes WHERE correo = ?";
        var stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        var rs = stm.executeQuery();

        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Nombre: " + rs.getString("nombre") +
                    ", Apellido: " + rs.getString("apellido") +
                    ", Correo: " + rs.getString("correo") +
                    ", Edad: " + rs.getInt("edad") +
                    ", Estado Civil: " + rs.getString("estado_civil"));
        } else {
            System.out.println("No se encontró un estudiante con el correo: " + email);
        }
    }

}
