import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/TallerJDBC?useSSL=false&serverTimezone=UTC";
    static String userName = "root";
    static String password = "";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            estudianteService estudianteService = new estudianteService();
            try (Scanner in = new Scanner(System.in)) {
                int opcion = -1;

                while (opcion != 6) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Insertar Estudiante");
                    System.out.println("2. Actualizar Estudiante");
                    System.out.println("3. Eliminar Estudiante");
                    System.out.println("4. Consultar todos los Estudiantes");
                    System.out.println("5. Consultar Estudiante por correo");
                    System.out.println("6. Salir");
                    System.out.print("Elija una opción: ");
                    opcion = in.nextInt();
                    in.nextLine();
                    switch (opcion) {
                        case 1 -> estudianteService.insertarEstudiante(conn);
                        case 2 -> estudianteService.actualizarEstudiante(conn);
                        case 3 -> estudianteService.eliminarEstudiante(conn);
                        case 4 -> estudianteService.obtenerEstudiantes(conn);
                        case 5 -> estudianteService.obtenerPorCorreo(conn);
                        case 6 -> System.out.println("Chao!");
                        default -> System.out.println("Opción incorrecta");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
