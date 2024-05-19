package metodos;

import java.io.IOException;

public class StrassenWinograd extends TiempoEjecucion {
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "StrassenWinograd";
            return strassenWinograd(matriz);
    }

    private double[][] strassenWinograd(double[][] matriz) {
        int n = matriz.length;
        double[][] result = new double[n][n];
        return strassenWinograd(matriz, matriz, result, n);
    }

    private double[][] strassenWinograd(double[][] A, double[][] B, double[][] C, int n) {
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        int newSize = n / 2;
        double[][] A11 = new double[newSize][newSize];
        double[][] A12 = new double[newSize][newSize];
        double[][] A21 = new double[newSize][newSize];
        double[][] A22 = new double[newSize][newSize];
        double[][] B11 = new double[newSize][newSize];
        double[][] B12 = new double[newSize][newSize];
        double[][] B21 = new double[newSize][newSize];
        double[][] B22 = new double[newSize][newSize];

        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, newSize);
        splitMatrix(A, A21, newSize, 0);
        splitMatrix(A, A22, newSize, newSize);
        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, newSize);
        splitMatrix(B, B21, newSize, 0);
        splitMatrix(B, B22, newSize, newSize);

        double[][] M1 = strassenWinograd(addMatrices(A11, A22), addMatrices(B11, B22), new double[newSize][newSize], newSize);
        double[][] M2 = strassenWinograd(addMatrices(A21, A22), B11, new double[newSize][newSize], newSize);
        double[][] M3 = strassenWinograd(A11, subtractMatrices(B12, B22), new double[newSize][newSize], newSize);
        double[][] M4 = strassenWinograd(A22, subtractMatrices(B21, B11), new double[newSize][newSize], newSize);
        double[][] M5 = strassenWinograd(addMatrices(A11, A12), B22, new double[newSize][newSize], newSize);
        double[][] M6 = strassenWinograd(subtractMatrices(A21, A11), addMatrices(B11, B12), new double[newSize][newSize], newSize);
        double[][] M7 = strassenWinograd(subtractMatrices(A12, A22), addMatrices(B21, B22), new double[newSize][newSize], newSize);

        double[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        double[][] C12 = addMatrices(M3, M5);
        double[][] C21 = addMatrices(M2, M4);
        double[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        joinMatrices(C11, C, 0, 0);
        joinMatrices(C12, C, 0, newSize);
        joinMatrices(C21, C, newSize, 0);
        joinMatrices(C22, C, newSize, newSize);

        return C;
    }

    private double[][] addMatrices(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private double[][] subtractMatrices(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    private void splitMatrix(double[][] parent, double[][] child, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {
                child[i][j] = parent[i + rowOffset][j + colOffset];
            }
        }
    }

    private void joinMatrices(double[][] child, double[][] parent, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {
                parent[i + rowOffset][j + colOffset] = child[i][j];
            }
        }
    }
}