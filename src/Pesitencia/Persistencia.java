package Pesitencia;
import Interface.Exportable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;

public class Persistencia {
    private String fileName;
    private String rutaArchivo;

    public Persistencia(String filename){
        String carpeta = "Datos";
        if (filename != null && filename.isEmpty()){
            System.out.println("El nombre del archivo no puede estar vacio");
        } else {
            this.fileName = filename;
        }

        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
            System.out.println("Carpeta '" + carpeta + "' creada exitosamente.");
        }
        this.rutaArchivo = carpeta + "/" + filename;
    }


    public void guardarRegistro(Exportable objeto){
        String linea = objeto.aFormatoTexto();

        try(FileWriter escritor = new FileWriter(this.rutaArchivo, true)){
            escritor.write(linea + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public ArrayList<String>leerRegistros(){
        ArrayList<String> lineas  = new ArrayList<>();

        try(BufferedReader lector = new BufferedReader(new FileReader(this.rutaArchivo))){
            String linea;

            while ((linea = lector.readLine()) != null){
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Aviso: No se pudo leer el archivo o aun no existe");
        }
        return lineas;
    }
}
