import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String idUsuario;
    private ArrayList<String> librosPrestados;

    //Constructor
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario= idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<String> getLibrosPrestados() {
        return librosPrestados;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setLibrosPrestados(ArrayList<String> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    //Metodo para prestar libro
    public void agregarLibroPrestado(String tituloLibro) {
        librosPrestados.add(tituloLibro);
    }

    // Metodo para devolver libro
    public void devolverLibro(String tituloLibro) {
        librosPrestados.remove(tituloLibro);
    }

    // Mostrar información del usuario
    public void mostrarUsuario() {
        System.out.println("---Información del usuario--- ");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + idUsuario);
        System.out.println("Libros prestados: " + librosPrestados);
    }




}
