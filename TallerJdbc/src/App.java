import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/TallerCurso";
    static String userName = "root";
    static String password = "root";

    public static void main(String[] args) {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC cargado correctamente ✅");

            // Conexión
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                System.out.println("Conectado a la base de datos ✅");

                student_service studentService = new student_service();
                Scanner in = new Scanner(System.in);
                int opcion = -1;

                while (opcion != 0) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Contar personas");
                    System.out.println("2. Insertar persona");
                    System.out.println("3. Actualizar persona");
                    System.out.println("4. Eliminar persona");
                    System.out.println("5. Ver todas las personas");
                    System.out.println("0. Salir");
                    System.out.print("Elija una opción: ");
                    opcion = in.nextInt();
                    in.nextLine(); // limpiar buffer

                    if (opcion == 1) {
                        studentService.obtenerNumeroPersonas(conn);
                    } else if (opcion == 2) {
                        studentService.insertarPersona(conn);
                    } else if (opcion == 3) {
                        studentService.actualizarPersona(conn);
                    } else if (opcion == 4) {
                        studentService.eliminarPersona(conn);
                    } else if (opcion == 5) {
                        studentService.obtenerPersonas(conn);
                    } else if (opcion == 0) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Opción incorrecta");
                    }
                }

                in.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
