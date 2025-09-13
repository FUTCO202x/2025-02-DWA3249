import java.util.List;
import java.util.Scanner;

import models.Estudiante;
import services.EstudianteServices;

public class App {
    public static void main(String[] args) throws Exception {
        EstudianteServices estudiante = new EstudianteServices();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENU ESTUDIANTES ===");
            System.out.println("1. Insertar Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("4. Consultar todos los Estudiantes");
            System.out.println("5. Consultar Estudiante por Email");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                    String estadoCivil = sc.nextLine();

                    Estudiante est = new Estudiante(nombre, apellido, correo, edad, estadoCivil);
                    estudiante.insertar(est);
                }
                case 2 -> {
                    System.out.print("Correo del estudiante a actualizar: ");
                    String correo = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Nueva edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo estado civil: ");
                    String estadoCivil = sc.nextLine();

                    estudiante.actualizar(correo, nombre, apellido, edad, estadoCivil);
                }
                case 3 -> {
                    System.out.print("Correo del estudiante a eliminar: ");
                    String correo = sc.nextLine();
                    estudiante.eliminar(correo);
                }
                case 4 -> {
                    List<Estudiante> lista = estudiante.consultarTodos();
                    lista.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Correo del estudiante: ");
                    String correo = sc.nextLine();
                    Estudiante est = estudiante.consultarPorCorreo(correo);
                    if (est != null)
                        System.out.println(est);
                    else
                        System.out.println("Estudiante no encontrado");
                }
                case 6 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida, escoge nuevamente.");
            }
        } while (opcion != 6);

        sc.close();

    }
}
