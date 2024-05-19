package metodos;

public class NaivLoopUnrollingTwo extends TiempoEjecucion {
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "NaivLoopUnrollingTwo";
        return NaivLoopUnrollingTwo(matriz);
    }
    public double[][] NaivLoopUnrollingTwo(double[][] matriz) {
        int N , P, M;
        N = P = M = matriz.length;
        double[][] resultado = new double[N][N];
        int i, j, k;
        double aux;
        if (P % 2 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 2) {
                        aux += matriz[i][k] * matriz[k][j] + matriz[i][k + 1] * matriz[k + 1][j];
                    }
                    resultado[i][j] = aux;
                }
            }
        } else {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 2) {
                        aux += matriz[i][k] * matriz[k][j] + matriz[i][k + 1] * matriz[k + 1][j];
                    }
                    resultado[i][j] = aux + matriz[i][PP] * matriz[PP][j];
                }
            }
        }
        return resultado;
    }
}
