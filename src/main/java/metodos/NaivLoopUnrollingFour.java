package metodos;

import java.io.IOException;

public class NaivLoopUnrollingFour extends TiempoEjecucion {
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "NaivLoopUnrollingFour";
        try {
            return NaivLoopUnrollingFour(matriz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double[][] NaivLoopUnrollingFour(double [][]matriz) throws IOException {
        int N , P, M;
        N = P = M = matriz.length;
        double[][] resultado = new double[N][N];
        int i, j, k;
        double aux;
        if (P % 4 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 4) {
                        aux += matriz[i][k]*matriz[k][j] + matriz[i][k+1]*matriz[k+1][j] + matriz[i][k+2]*matriz[k+2][j]
                                + matriz[i][k+3]*matriz[k+3][j];
                    }
                    resultado[i][j] = aux;
                }
            }
        } else if (P % 4 == 1) {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matriz[i][k]*matriz[k][j] + matriz[i][k+1]*matriz[k+1][j] + matriz[i][k+2]*matriz[k+2][j]
                                + matriz[i][k+3]*matriz[k+3][j];
                    }
                    resultado[i][j] = aux + matriz[i][PP]*matriz[PP][j];
                }
            }
        } else if (P % 4 == 2) {
            int PP = P - 2;
            int PPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matriz[i][k]*matriz[k][j] + matriz[i][k+1]*matriz[k+1][j] + matriz[i][k+2]*matriz[k+2][j]
                                + matriz[i][k+3]*matriz[k+3][j];
                    }
                    resultado[i][j] = aux + matriz[i][PP]*matriz[PP][j] + matriz[i][PPP]*matriz[PPP][j];
                }
            }
        } else {
            int PP = P - 3;
            int PPP = P - 2;
            int PPPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matriz[i][k]*matriz[k][j] + matriz[i][k+1]*matriz[k+1][j] + matriz[i][k+2]*matriz[k+2][j]
                                + matriz[i][k+3]*matriz[k+3][j];
                    }
                    resultado[i][j] = aux + matriz[i][PP]*matriz[PP][j] + matriz[i][PPP]*matriz[PPP][j]
                            + matriz[i][PPPP]*matriz[PPPP][j];
                }
            }
        }
        return resultado;
    }
    double Abs(double a){
        if ( a < 0 ) {
            return -a;
        }
        return a;
    }


    public static double Max( double x, double y )
    {
        if (x < y){
            return y;
        } else {
            return x;
        }
    }

    public static double NormInf(double[][] A, double N, double P)
    {
        double Norm = 0.0;
        double aux;
        int i, j;
        for (i = 0; i < N; i++) {
            aux = 0.0;
            for (j = 0; j < P; j++) {
                aux += Math.abs(A[i][j]);
            }
            Norm = Max(Norm, aux);
        }
        return Norm;
    }
}
