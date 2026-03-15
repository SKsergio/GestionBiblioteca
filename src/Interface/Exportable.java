package Interface;

public interface Exportable {
    String aFormatoTexto();
    //usar esta interfaz en todas las clases, y agregar
    // un metodo que devuelva los campos de la clsae en formato string

    //Ejemplo
//    public String aFormatoTexto() {
//        // Formato CSV: "1,Harry Potter,J.K. Rowling"
//        return this.id + "," + this.titulo + "," + this.autor;
//    }
}
