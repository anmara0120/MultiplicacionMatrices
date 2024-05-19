package metodos;

import java.util.Arrays;

public class III5EnhancedParallelBlock extends TiempoEjecucion{
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "III5EnhancedParallelBlock";
        return alg_III_5_Enhanced_Parallel_Block(matriz);
    }

    public static double[][] alg_III_5_Enhanced_Parallel_Block(double[][] matriz) {
        int size = matriz.length;
        double[][] matrizRes = new double[size][size];
        Arrays.stream(new int[]{0}).parallel().forEach(i1 -> {
            for (i1 = 0; i1 < size / 2; i1 += size) {
                for (int j1 = 0; j1 < size; j1 += size) {
                    for (int k1 = 0; k1 < size; k1 += size) {
                        for (int i = i1; i < i1 + size && i < size; i++) {
                            for (int j = j1; j < j1 + size && j < size; j++) {
                                for (int k = k1; k < k1 + size && k < size; k++) {
                                    matrizRes[i][j] += matriz[i][k] * matriz[k][j];
                                }
                            }
                        }
                    }
                }
            }
        });
        return matrizRes;
    }
}
