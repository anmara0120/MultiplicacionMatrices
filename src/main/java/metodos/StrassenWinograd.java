package metodos;

import java.io.IOException;

public class StrassenWinograd extends TiempoEjecucion {
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "StrassenWinograd";
            return multiply(matriz, matriz);
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        double[][] A11 = new double[n/2][n/2];
        double[][] A12 = new double[n/2][n/2];
        double[][] A21 = new double[n/2][n/2];
        double[][] A22 = new double[n/2][n/2];

        double[][] B11 = new double[n/2][n/2];
        double[][] B12 = new double[n/2][n/2];
        double[][] B21 = new double[n/2][n/2];
        double[][] B22 = new double[n/2][n/2];

        // Dividir matrices A y B en sub-matrices
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + n/2];
                A21[i][j] = A[i + n/2][j];
                A22[i][j] = A[i + n/2][j + n/2];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + n/2];
                B21[i][j] = B[i + n/2][j];
                B22[i][j] = B[i + n/2][j + n/2];
            }
        }

        // Calcular productos parciales
        double[][] M1 = multiply(add(A11, A22), add(B11, B22));
        double[][] M2 = multiply(add(A21, A22), B11);
        double[][] M3 = multiply(A11, subtract(B12, B22));
        double[][] M4 = multiply(A22, subtract(B21, B11));
        double[][] M5 = multiply(add(A11, A12), B22);
        double[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
        double[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

        // Calcular productos finales
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                C[i][j] = M1[i][j] + M4[i][j] - M5[i][j] + M7[i][j];
                C[i][j + n/2] = M3[i][j] + M5[i][j];
                C[i + n/2][j] = M2[i][j] + M4[i][j];
                C[i + n/2][j + n/2] = M1[i][j] - M2[i][j] + M3[i][j] + M6[i][j];
            }
        }

        return C;
    }

    private static double[][] add(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    private static double[][] subtract(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }


}