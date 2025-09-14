import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteServices {
    public void consultarEstudiantePorEmail(Connection conn, String email) throws SQLException {
        String sql = "SELECT * FROM Estudiante WHERE Email = ?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        boolean encontrado = false;
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("Nombre");
            String apellido = rs.getString("Apellido");
            String edad = rs.getString("Edad");
            String estadoCivil = rs.getString("EstadoCivil");
            String emailDb = rs.getString("Email");
            System.out.println(String.format("ID: %d, Nombre: %s %s, Edad: %s, Estado Civil: %s, Email: %s", id, nombre, apellido, edad, estadoCivil, emailDb));
            encontrado = true;
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún estudiante con ese email.");
        }
        System.out.println("Consulta Finalizada");
    }
    public void obtenerNumeroEstudiantes(Connection conn) throws SQLException{
        String sql="SELECT * FROM Estudiante";
        var stm =conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont=0;
        while (rs.next()){
            System.out.print(".");
            cont++;
        }
        System.out.println("Cantidad de Estudiantes encontrados " + cont);
    }

    public void insertarEstudiante(Connection conn) throws SQLException{
        String sql="INSERT INTO Estudiante (Nombre, Apellido, Edad, EstadoCivil) VALUES ('Jim', 'Carrey', 20 ,'Casado')";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Estudiante insertado de forma correcta");
        }
        else {
            System.out.println("Error en insercion");
        }
    }

    public void actualizarEstudiante(Connection conn) throws SQLException{
        String sql="UPDATE Estudiante SET EstadoCivil='VIUDO' WHERE ID=10";
        var stm=conn.prepareStatement(sql);
        int rs = stm.executeUpdate();
        if(rs>0) {
            System.out.println("Estudiante actualizado de forma correcta");
        }
        else {
            System.out.println("Error en la actualizacion");
        }
    }

    public void eliminarEstudiante(Connection conn) throws SQLException{
        String sql="DELETE FROM Estudiante WHERE ID=10";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Estudiante eliminado de forma correcta");
        }
        else {
            System.out.println("Error en la eliminacion");
        }
    }

    public void insertarEstudianteConValores(Connection conn) throws SQLException{
        Scanner in= new Scanner(System.in);
        System.out.println("Digite nombre del estudiante: ");
        String nombre= in.nextLine();
        System.out.println("Digite apellido del estudiante: ");
        String apellido=in.nextLine();
        System.out.println("Digite la edad del estudiante: ");
        String edad=in.nextLine();
        System.out.println("Digite el estado civil de la persona: ");
        String estadoCivil=in.nextLine();
        String sql="INSERT INTO Estudiante (Nombre, Apellido, Edad, EstadoCivil) VALUES (?,?,?,?)";
        var stm=conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2,apellido);
        stm.setString(3, edad);
        stm.setString(4,estadoCivil);
        int rs = stm.executeUpdate();
        if(rs>0){
            System.out.println("Estudiante insertado de forma correcta");
        }
        else{
            System.out.println("Fallo en la insercion");
        }
        in.close();
    }

    public void actualizarEstudiantesConValores(Connection conn) throws SQLException{
        Scanner in =new Scanner(System.in);
        System.out.println("Digite el nuevo estado civil: ");
        String estadoCivil=in.nextLine();
        System.out.println("Digite el ID del estudiante a actualizar: ");
        int id= in.nextInt();
        String sql="UPDATE Estudiante SET EstadoCivil=? WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        //stm.setString(1, estadoCivil);
        //stm.setInt(2,id);
        stm.setObject(1, estadoCivil);
        stm.setObject(2,id);
        int rs = stm.executeUpdate();
        if(rs>0){
            System.out.println("Estudiante actualizado de forma correcta");
        }
        else{
            System.out.println("Fallo en la actualizacion");
        }
        in.close();
    }

    public void eliminarEstudianteConValores(Connection conn) throws SQLException{
        Scanner in=new Scanner(System.in);
        System.out.print("Digite el id del estudiante a eliminar: ");
        int id=in.nextInt();
        String sql="DELETE FROM Estudiante WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setObject(1,id);
        int rs=stm.executeUpdate();
        if(rs>0){
            System.out.println("Estudiante eliminado");
        }
        else {
            System.out.println("Eiminacion fallida");
        }
        in.close();
    }

    public void obtenerEstudiantesConValores(Connection conn) throws SQLException{
        String sql="SELECT * FROM Estudiante";
        var stm=conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String nombre = rs.getString("Nombre");
            String apellido = rs.getString("Apellido");
            String edad = rs.getString("Edad");
            String estadoCivil = rs.getString("EstadoCivil");
            System.out.println(String.format("Mi id es %d, mi nombre completo es %s %s, mi edad es %s y mi estado civil es %s",id,nombre,apellido,edad,estadoCivil));
        }
        System.out.println("Consulta Finalizada");
    }
}