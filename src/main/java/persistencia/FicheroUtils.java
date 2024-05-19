package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FicheroUtils {

    public static void main(String[] args) {

        for (int i = 1; i <=10 ; i++) {
            createMatriz((int) Math.pow(2,i));
        }
    }

    private static void createMatriz(int n) {

        String filename = n + "x" + n +".txt"; // Nombre del archivo a crear
        try {
            FileWriter writer = new FileWriter(filename);
            Random random = new Random();

            // Escribir la matriz en el archivo
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int randomNumber = random.nextInt(900000) + 100000; // Generar número aleatorio de 6 dígitos
                    writer.write(String.format("%06d", randomNumber) + "\t"); // Formatear el número como una cadena de 6 dígitos
                }
                writer.write("\n"); // Nueva línea después de cada fila
            }

            writer.close(); // Cerrar el FileWriter después de usarlo
            System.out.println("Se ha creado el archivo '" + filename + "' con una matriz " + n + "x" + n + " de números aleatorios.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    public static double[][] leerMatriz(String archivo) {

        try {
            return Files.lines(Paths.get(archivo))
                    .map(linea -> Arrays.stream(linea.split("\\s"))
                            .mapToDouble(Double::parseDouble)
                            .toArray())
                    .toArray(double[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new double[0][0];
        }
    }

    public static void guardarTiempo(String nombreAlgoritmo, long executionTime, String fichero) {
        try {
            // Leer el archivo, modificar la línea y escribir de vuelta al archivo
            System.out.println(nombreAlgoritmo + " - " + fichero);
            List<String> lines = Files.lines(Paths.get("Tiempo_Ejecucion.txt"))
                    .map(linea -> {
                        if (linea.contains(nombreAlgoritmo + " [" + fichero + "]")) {
                            return nombreAlgoritmo + " [" + fichero + "] -> " + "Tiempo: " + executionTime + " nanosegundos";
                        } else {
                            return linea;
                        }
                    }).toList();

            // Escribir las líneas modificadas de vuelta al archivo
            Files.write(Paths.get("Tiempo_Ejecucion.txt"), (Iterable<String>) lines::iterator);

            System.out.println("La línea ha sido modificada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

