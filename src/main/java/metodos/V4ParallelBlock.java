package metodos;

import java.util.stream.IntStream;

public class V4ParallelBlock extends TiempoEjecucion{

    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "V4ParallelBlock";
        return v_4ParallelBlock(matriz);
    }

    public double[][] v_4ParallelBlock(double [][] matriz){
        int size=matriz.length;
        int bsize = (int) Math.sqrt(size);
        double [][] matrizResultado = new double [size][size];
        IntStream.range(0, 1).parallel().forEach(_i -> {
            for (int i1 = 0; i1 < size; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    matrizResultado[i][k] += matriz[i][j] * matriz[j][k];
                                }
                            }
                        }
                    }
                }
            }
        });
        return matrizResultado;
    }
}
