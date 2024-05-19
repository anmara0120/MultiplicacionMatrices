package metodos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static persistencia.FicheroUtils.guardarTiempo;

public abstract class TiempoEjecucion {
    String nombreMetodo;

    public void ejecutar(double [][] matriz){
        long tiempoInicio = System.nanoTime();
        double[][] matrizResultante = algoritmo(matriz);
        long tiempoFinal = System.nanoTime();
        long tiempoEjecucion = tiempoFinal - tiempoInicio;
        escribirArchivo(matrizResultante);
        guardarTiempo(nombreMetodo, tiempoEjecucion, matriz.length + "x" + matriz.length);
    }

    public void escribirArchivo(double [][] matriz) {
        String filename = nombreMetodo + matriz.length + "x" + matriz.length +".txt"; // Nombre del archivo a crear
        File carpetaResultado = new File("resultados");
        File carpetaMetodo = new File("resultados" + File.separator + nombreMetodo);
        carpetaResultado.mkdir();
        carpetaMetodo.mkdir();
        try {
            FileWriter writer = new FileWriter("resultados" + File.separator
                    + nombreMetodo + File.separator + filename);

            // Escribir la matriz en el archivo
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    writer.write(matriz[i][j] + "\t"); // Formatear el número como una cadena de 6 dígitos
                }
                writer.write("\n"); // Nueva línea después de cada fila
            }

            writer.close(); // Cerrar el FileWriter después de usarlo
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public abstract double[][] algoritmo(double [][] matriz);
}
