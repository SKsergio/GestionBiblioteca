import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcion = -1;

        do {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Filtrar por género");
            System.out.println("7. Filtrar por autor");
            System.out.println("8. Ver libros disponibles");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            // VALIDAR QUE INGRESEN NUMERO
            if (!sc.hasNextInt()) {
                System.out.println("Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    if (titulo.isEmpty()) {
                        System.out.println("El título no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    if (autor.isEmpty()) {
                        System.out.println("El autor no puede estar vacío.");
                        break;
                    }

                    // VALIDAR AÑO
                    int anio;

                    while (true) {
                        System.out.print("Año: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Ingrese un año válido.");
                            sc.nextLine();
                            continue;
                        }

                        anio = sc.nextInt();
                        sc.nextLine();

                        if (anio < 1500 || anio > 2025) {
                            System.out.println("Ingrese un año entre 1500 y 2025.");
                        } else {
                            break;
                        }
                    }

                    System.out.print("Genero: ");
                    String genero = sc.nextLine();

                    if (genero.isEmpty()) {
                        System.out.println("El genero no puede estar vacío.");
                        break;
                    }

                    Libro libro = new Libro(titulo, autor, anio, genero);
                    biblioteca.registrarLibro(libro);

                    System.out.println("Libro registrado correctamente.");
                    break;

                case 2:

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    if (id.isEmpty()) {
                        System.out.println("El ID no puede estar vacío.");
                        break;
                    }

                    Usuario usuario = new Usuario(nombre, id);
                    biblioteca.registrarUsuario(usuario);

                    System.out.println("Usuario registrado correctamente.");
                    break;

                case 3:

                    System.out.print("Titulo del libro: ");
                    String tituloPrestamo = sc.nextLine();

                    System.out.print("ID del usuario: ");
                    String idUsuario = sc.nextLine();

                    if (biblioteca.prestarLibro(tituloPrestamo, idUsuario)) {
                        System.out.println("Prestamo realizado.");
                    } else {
                        System.out.println("No se pudo prestar el libro.");
                    }

                    break;

                case 4:

                    System.out.print("Titulo del libro: ");
                    String tituloDev = sc.nextLine();

                    System.out.print("ID del usuario: ");
                    String idDev = sc.nextLine();

                    if (biblioteca.devolverLibro(tituloDev, idDev)) {
                        System.out.println("Libro devuelto.");
                    } else {
                        System.out.println("Error al devolver.");
                    }

                    break;

                case 5:

                    ArrayList<Libro> libros = biblioteca.listarLibros();

                    if (libros.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        for (Libro l : libros) {
                            l.mostrarInfo();
                            System.out.println("-------------------");
                        }
                    }

                    break;

                case 6:

                    System.out.print("Genero: ");
                    String gen = sc.nextLine();

                    for (Libro l : biblioteca.filtrarGenero(gen)) {
                        l.mostrarInfo();
                        System.out.println("-------------------");
                    }

                    break;

                case 7:

                    System.out.print("Autor: ");
                    String aut = sc.nextLine();

                    for (Libro l : biblioteca.filtrarAutor(aut)) {
                        l.mostrarInfo();
                        System.out.println("-------------------");
                    }

                    break;

                case 8:

                    for (Libro l : biblioteca.filtrarDisponibles()) {
                        l.mostrarInfo();
                        System.out.println("-------------------");
                    }

                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}