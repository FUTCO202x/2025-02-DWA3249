import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    /*
     * INTEGRANTES:
     * - Carlos Alberto De La Rosa Banquez
     * - Ruben Dario Castaño Gallardo
     */
    static String url="jdbc:mysql://localhost:3306/escuela";
    static String userName="root";
    static String  password="Carlos1009";


    public static void main(String[] args) throws Exception {
        try (Connection connection = DriverManager.getConnection(url, userName, password)){
            Scanner scanner = new Scanner(System.in);
            int opcion;
            EstudianteServices estudianteServices = new EstudianteServices();

            do {
                System.out.println("""
                    Seleccione una opcion: 
                    1. Insertar Estudiante
                    2. Actualizar Estudiante
                    3. Eliminar Estudiante
                    4. Consultar todos los estudiantes
                    5. Consultar Estudiante por email
                    6. Salir del programa
                    """);
                System.out.println("Seleccione una opción:");
                opcion = scanner.nextInt(); 

                switch (opcion) {
                    case 1:
                        estudianteServices.insertarEstudiante(connection);
                        break;
                    case 2:
                        estudianteServices.actualizarEstudiante(connection);
                        break;
                    case 3:
                        estudianteServices.eliminarEstudiante(connection);
                        break;
                    case 4:
                        estudianteServices.consultarEstudiantes(connection);
                        break;
                    case 5:
                        estudianteServices.consultarEstudiantePorEmail(connection);
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (opcion != 6);

            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
