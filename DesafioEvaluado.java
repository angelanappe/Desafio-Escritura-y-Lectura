import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DesafioEvaluado {
    public static void main(String[] args) {

        String nombreDirectorio = "directorio";
        String nombreFile = "archivo.txt"; //El nombre del fichero debe terminar con “.txt”, para indicar que es un archivo de texto.

        crearArchivo(nombreDirectorio, nombreFile);
        buscarTexto(nombreDirectorio + "/" + nombreFile, "Perro");
    }

    //Crear un método llamado crearArchivo(directorio,archivo), este método recibe el nombre del directorio y el fichero como parámetros de entrada.
    public static void crearArchivo(String directorio, String archivo) {
        File dir = new File(directorio);
        File file = new File(directorio + "/" + archivo);

        //Validar que el nombre del directorio no exista
        if(!dir.exists()) {
            //Si el directorio no existe, se debe crear.
            if (dir.mkdirs()) {
                System.out.printf("'%s' fue creado con éxito.\n", directorio);
            } else {
                //Si existe algún otro problema al crear el directorio, se mostrará el siguiente mensaje por consola:
                System.out.println("Error al crear directorio.");
                return;
            }
            //Si existe se mostrará el siguiente mensaje por consola
        } else {
            System.out.println("El directorio ya existe.\n");
        }

        try {
            if (file.exists()){
                System.out.println("El archivo ya existe.\n");
            }
            else {
                if(file.createNewFile()) {
                    System.out.printf("'%s' creado con éxito.\n", file.getName());
                }else {
                    System.out.println("No se pudo crear el archivo.\n");
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo.\n");
            e.printStackTrace();
            return;
        }

        // Escribir en el archivo con un salto de línea lo que se encuentra en el siguiente ArrayList.
        try {
            FileWriter wr = new FileWriter(file);

            ArrayList<String> lista = new ArrayList<String>();
            lista.add("Perro");
            lista.add("Gato");
            lista.add("Juan");
            lista.add("Daniel");
            lista.add("Juan");
            lista.add("Gato");
            lista.add("Perro");
            lista.add("Camila");
            lista.add("Daniel");
            lista.add("Camila");

            // Considerar utilizar Iterator para recorrer la lista.
            Iterator<String> iterator = lista.iterator();
            while (iterator.hasNext()) {
                wr.write(iterator.next() + "\n");
            }

            wr.close();
            System.out.println("Datos escritos en: " + file.getAbsolutePath() + "\n");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
            e.printStackTrace();
        }
    }

    //Crear un método llamado buscarTexto(nombreFichero,texto), este método recibe el nombre del fichero y el texto a buscar como parámetros de entrada.
    public static void buscarTexto(String nombreArchivo, String texto) {
        File file = new File (nombreArchivo);

        //Validar que el archivo exista
        if (!file.exists()) {
            System.out.println("El archivo ingresado no existe."); //Si el fichero no existe, se mostrará el siguiente mensaje por consola.
            return;
        }

        //Si el fichero existe, buscar el texto ingresado dentro del archivo.
        int repeticiones = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                if(line.contains(texto)){
                    repeticiones++;
                }
            }
            br.close();
            System.out.println("Cantidad de repeticiones del texto -> " + repeticiones);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
