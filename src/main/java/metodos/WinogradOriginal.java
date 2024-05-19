package metodos;

import java.io.IOException;

public class WinogradOriginal extends TiempoEjecucion {
   //
   @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "WinogradOriginal";
        try {
            return WinogradOriginal(matriz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public double[][] WinogradOriginal(double[][] matriz)throws IOException  {

       int i, j, k, N, P,M;
        N=P=M= matriz.length;
        double[][] Result = new double[N][N];
        double aux;
        int upsilon = P % 2;
        int gamma = P - upsilon;
        double[] y = new double[N];
        double[] z = new double[M];

        // Calcular vector y
        for (i = 0; i < N; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += matriz[i][j] * matriz[i][j + 1];
            }
            y[i] = aux;
        }

        // Calcular vector z
        for (i = 0; i < M; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += matriz[j][i] * matriz[j + 1][i];
            }
            z[i] = aux;
        }

        // Si P es impar
        if (upsilon == 1) {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (k = 0; k < M; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (matriz[i][j] + matriz[j + 1][k]) * (matriz[i][j + 1] + matriz[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k] + matriz[i][PP] * matriz[PP][k];
                }
            }
        } else {
            // Si P es par
            for (i = 0; i < N; i++) {
                for (k = 0; k < M; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (matriz[i][j] + matriz[j + 1][k]) * (matriz[i][j + 1] + matriz[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k];
                }
            }
        }
        return Result;
    }
    }


