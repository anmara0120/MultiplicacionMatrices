package metodos;

public class WinogradScaled extends TiempoEjecucion {

    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "WinogradScaled";
        return winogradScaled(matriz);
    }

    public double[][] winogradScaled(double[][] matriz) {
        int N = matriz.length;

        double[][] Result = new double[N][N];

        double a = normInf(matriz, N, N);
        double b = normInf(matriz, N, N);
        double lambda = Math.floor(0.5 + Math.log(b / a) / Math.log(4));

        double[][] copyA = multiplyWithScalar(matriz, Math.pow(2, lambda));
        double[][] copyB = multiplyWithScalar(matriz, Math.pow(2, -lambda));

        multiplyMatrices(copyA, copyB, Result);

        return Result;
    }

    private double[][] multiplyWithScalar(double[][] A, double scalar) {
        int N = A.length;
        double[][] B = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                B[i][j] = A[i][j] * scalar;
            }
        }
        return B;
    }

    private double normInf(double[][] A, int N, int M) {
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < N; i++) {
            double sum = 0.0;
            for (int j = 0; j < M; j++) {
                sum += Math.abs(A[i][j]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    private void multiplyMatrices(double[][] A, double[][] B, double[][] Result) {
        int N = A.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                Result[i][j] = sum;
            }
        }
    }
}
