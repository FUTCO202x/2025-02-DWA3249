import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/TestDB";
    static String userName = "root";
    static String password = "Pass_123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, userName, password);
                 Scanner sc = new Scanner(System.in)) {

                EstudianteService estudianteService = new EstudianteService();

                int opcion;
                do {
                    System.out.println("\n--- MENÚ ESTUDIANTES ---");
                    System.out.println("1. Insertar Estudiante");
                    System.out.println("2. Actualizar Estudiante");
                    System.out.println("3. Eliminar Estudiante");
                    System.out.println("4. Consultar todos los estudiantes");
                    System.out.println("5. Consultar estudiante por correo");
                    System.out.println("6. Salir");
                    System.out.print("Elija una opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (opcion) {
                        case 1 -> estudianteService.insertarEstudiante(conn, sc);
                        case 2 -> estudianteService.actualizarEstudiante(conn, sc);
                        case 3 -> estudianteService.eliminarEstudiante(conn, sc);
                        case 4 -> estudianteService.consultarEstudiantes(conn);
                        case 5 -> estudianteService.consultarPorCorreo(conn, sc);
                        case 6 -> System.out.println("Saliendo...");
                        default -> System.out.println("Opción no válida.");
                    }
                } while (opcion != 6);

            }
        } catch (Exception e) {
            System.out.println(" Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
