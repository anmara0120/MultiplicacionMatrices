package metodos;

public class WinogradScaled extends WinogradOriginal{
    public void WinogradScaled(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        int i, j;
        /* Create scaled copies of A and B */
        double[][] CopyA = new double[N][P];
        double[][] CopyB = new double[P][M];
        /* Scaling factors */
        double a = NormInf(A, N, P);
        double b = NormInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b/a)/Math.log(4));
        /* Scaling */
        MultiplyWithScalar(A, CopyA, N, P, Math.pow(2, lambda));
        MultiplyWithScalar(B, CopyB, P, M, Math.pow(2, -lambda));
        /* Using Winograd with scaled matrices */
        WinogradOriginal(CopyA, CopyB, Result, N, P, M);
    }

    public static void MultiplyWithScalar(double[][] A, double[][] B, int N, int M, double scalar) {
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                B[i][j] = A[i][j] * scalar;
            }
        }
    }

    public static double NormInf(double[][] A, int N, int M) {
        int i, j;
        double max = Double.NEGATIVE_INFINITY;
        for (i = 0; i < N; i++) {
            double sum = 0.0;
            for (j = 0; j < M; j++) {
                sum += Math.abs(A[i][j]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
