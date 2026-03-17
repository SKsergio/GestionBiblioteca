import Pesitencia.Persistencia;
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;

    private Persistencia persistenciaUsuarios;
    private Persistencia persistenciaLibros;

    // Constructor
    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();

        //inicializacion de archivos
        this.persistenciaUsuarios = new Persistencia("usuarios.txt");
        this.persistenciaLibros = new Persistencia("libros.txt");

        cargarDatosPrevios();
    }

    private void cargarDatosPrevios() {
        ArrayList<String> lineasUsuarios = persistenciaUsuarios.leerRegistros();
        for (String linea : lineasUsuarios) {
            String[] partes = linea.split(";");
            if (partes.length == 2) {
                Usuario u = new Usuario(partes[1], partes[0]);
                this.usuarios.add(u);
            }
        }

        ArrayList<String> lineasLibros = persistenciaLibros.leerRegistros();
        for (String linea : lineasLibros) {
            String[] partes = linea.split(";");
            if (partes.length == 5) {
                String titulo = partes[0];
                String autor = partes[1];

                int anioPublicacion = Integer.parseInt(partes[2]);
                String genero = partes[3];
                boolean disponible = Boolean.parseBoolean(partes[4]);
                Libro libroRecuperado = new Libro(titulo, autor, anioPublicacion, genero);
                libroRecuperado.setDisponible(disponible);

                this.libros.add(libroRecuperado);
            }
        }

        System.out.println("Biblioteca inicializada. Usuarios cargados: " + usuarios.size() + " | Libros cargados: " + libros.size());
    }


    // REGISTRAR LIBROS
    public void registrarLibro(Libro libro) {
        libros.add(libro);
        persistenciaLibros.guardarRegistro(libro);
    }


    // REGISTRAR USUARIOS
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        persistenciaUsuarios.guardarRegistro(usuario);
    }


    // BUSCAR LIBRO

    public Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }


    // BUSCAR USUARIO

    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equalsIgnoreCase(id)) {
                return usuario;
            }
        }
        return null;
    }


    // PRESTAR LIBRO

    public boolean prestarLibro(String titulo, String idUsuario) {

        Libro libro = buscarLibro(titulo);
        Usuario usuario = buscarUsuario(idUsuario);

        if (libro == null || usuario == null) {
            return false;
        }

        if (!libro.isDisponible()) {
            return false;
        }

        libro.setDisponible(false);
        usuario.getLibrosPrestados().add(libro);

        return true;
    }


    // DEVOLVER LIBROO

    public boolean devolverLibro(String titulo, String idUsuario) {

        Libro libro = buscarLibro(titulo);
        Usuario usuario = buscarUsuario(idUsuario);

        if (libro == null || usuario == null) {
            return false;
        }

        if (usuario.getLibrosPrestados().remove(libro)) {
            libro.setDisponible(true);
            return true;
        }

        return false;
    }


    // LISTAR LIBROS

    public ArrayList<Libro> listarLibros() {
        return libros;
    }


    // FILTRAR POR GENERO
    public ArrayList<Libro> filtrarGenero(String genero) {

        ArrayList<Libro> resultado = new ArrayList<>();

        for (Libro libro : libros) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }


    // FILTRAR POR AUTOR

    public ArrayList<Libro> filtrarAutor(String autor) {

        ArrayList<Libro> resultado = new ArrayList<>();

        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }


    // FILTRAR POR DISPONIBILIDAD
    public ArrayList<Libro> filtrarDisponibles() {

        ArrayList<Libro> resultado = new ArrayList<>();

        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

}
