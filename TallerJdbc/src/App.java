import java.sql.Connection;
import java.sql.DriverManager;

public class App {
     static String url= "jdbc:mysql://localhost:3306/escuela";
     static String userName="root";
     static String password="";

    public static void main(String[] args){
        try(Connection conn= DriverManager.getConnection(url, userName, password)){
            EstudianteServices estudianteServices=new EstudianteServices();
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int opcion = 0;
            while (opcion != 6) {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los estudiantes");
                System.out.println("5. Consultar Estudiante por email");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        estudianteServices.insertarEstudianteConValores(conn);
                        break;
                    case 2:
                        estudianteServices.actualizarEstudiantesConValores(conn);
                        break;
                    case 3:
                        estudianteServices.eliminarEstudianteConValores(conn);
                        break;
                    case 4:
                        estudianteServices.obtenerEstudiantesConValores(conn);
                        break;
                    case 5:
                        System.out.print("Digite el email del estudiante: ");
                        String email = scanner.nextLine();
                        estudianteServices.consultarEstudiantePorEmail(conn, email);
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            }
            scanner.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}