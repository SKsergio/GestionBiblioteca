import Interface.Exportable;

import java.util.ArrayList;

public class Usuario implements Exportable {

    private String nombre;
    private String idUsuario;
    private ArrayList<Libro> librosPrestados;

    // Constructor
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    // Métodos

    public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    @Override
    public String aFormatoTexto() {
        return idUsuario + ";" + nombre;
    }

    public void mostrarUsuario() {
        System.out.println("--- Información del usuario ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + idUsuario);

        System.out.println("Libros prestados:");
        for (Libro libro : librosPrestados) {
            System.out.println(libro.getTitulo());
        }
    }
}