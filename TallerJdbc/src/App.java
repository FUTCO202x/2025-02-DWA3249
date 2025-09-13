import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url= "jdbc:mysql://localhost:3306/escuela";
    static String userName="root";
    static String password="Pass_123";

    public static void main(String[] args){
        try(Connection conn= DriverManager.getConnection(url, userName, password);
            Scanner sc = new Scanner(System.in)) {
            EstudiantesService estudiantesService=new EstudiantesService();
            int opcion = -1;
            while(opcion != 0){
                System.out.println("\n--- Menú Estudiantes ---");
                System.out.println("1. Listar estudiantes");
                System.out.println("2. Insertar estudiante");
                System.out.println("3. Actualizar estudiante");
                System.out.println("4. Eliminar estudiante");
                System.out.println("5. Consultar por correo e ID");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); 

                switch(opcion){
                    case 1:
                        estudiantesService.obtenerEstudiantes(conn);
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Estado civil: ");
                        String estadoCivil = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        estudiantesService.insertarEstudiantes(conn, nombre, apellido, estadoCivil, edad, correo);
                        break;
                    case 3:
                        System.out.print("ID del estudiante a actualizar: ");
                        int idActualizar = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nNombre = sc.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String nApellido = sc.nextLine();
                        System.out.print("Nuevo correo: ");
                        String nCorreo = sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int nEdad = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo estado civil: ");
                        String nEstadoCivil = sc.nextLine();
                        estudiantesService.actualizarEstudiantes(conn, idActualizar, nNombre, nApellido, nCorreo, nEdad, nEstadoCivil);
                        break;
                    case 4:
                        System.out.print("ID del estudiante a eliminar: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine();
                        estudiantesService.eliminarEstudiantes(conn, idEliminar);
                        break;
                    case 5:
                        System.out.print("Correo: ");
                        String cCorreo = sc.nextLine();
                        System.out.print("ID: ");
                        int cId = sc.nextInt();
                        sc.nextLine();
                        estudiantesService.consultarCorreo(conn, cCorreo, cId);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}