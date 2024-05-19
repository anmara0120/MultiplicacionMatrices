package metodos;

import java.util.stream.IntStream;

public class IV4ParallelBlock extends TiempoEjecucion {
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "IV4ParallelBlock";
        return lV_4ParallelBlock(matriz);
    }
    public double[][] lV_4ParallelBlock(double [][] matriz){
        int size = matriz.length;
        int bsize = (int) Math.sqrt(size);
        double [][] matrizResultado = new double [size][size];
        IntStream.range(0, size / bsize).parallel().forEach(i1 -> {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    for (int i = i1 * bsize; i < (i1 + 1) * bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                matrizResultado[i][k] += matriz[i][j] * matriz[j][k];
                            }
                        }
                    }
                }
            }
        });
        return matrizResultado;
    }
}
