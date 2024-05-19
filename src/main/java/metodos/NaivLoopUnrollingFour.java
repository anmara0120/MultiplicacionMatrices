package metodos;

import java.io.FileWriter;
import java.io.IOException;

public class NaivLoopUnrollingFour {
    public static double[][] NaivLoopUnrollingFour(double [][]A, double [][]B, double[][] Result, int N, int P, int M) throws IOException {
        long startTime = System.nanoTime();
        int i, j, k;
        double aux;
        if (P % 4 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 4) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j] + A[i][k+2]*B[k+2][j]
                                + A[i][k+3]*B[k+3][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else if (P % 4 == 1) {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j] + A[i][k+2]*B[k+2][j]
                                + A[i][k+3]*B[k+3][j];
                    }
                    Result[i][j] = aux + A[i][PP]*B[PP][j];
                }
            }
        } else if (P % 4 == 2) {
            int PP = P - 2;
            int PPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j] + A[i][k+2]*B[k+2][j]
                                + A[i][k+3]*B[k+3][j];
                    }
                    Result[i][j] = aux + A[i][PP]*B[PP][j] + A[i][PPP]*B[PPP][j];
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
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j] + A[i][k+2]*B[k+2][j]
                                + A[i][k+3]*B[k+3][j];
                    }
                    Result[i][j] = aux + A[i][PP]*B[PP][j] + A[i][PPP]*B[PPP][j]
                            + A[i][PPPP]*B[PPPP][j];
                }
            }
        }
        //Tiempo de ejecución del algoritmo
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e9;
        guardarTiempoEjecucion(executionTime, "TN.txt","metodos.NaivLoopUnrollingFour");
        System.out.println("Tiempo de ejecución: " + executionTime + " segundos");
        return Result;
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

    public static void guardarTiempoEjecucion(double time, String txt, String algoritmo) throws IOException {
        FileWriter fileWriter = new FileWriter(txt, true);
        fileWriter.write("T(n) de " + algoritmo + ": " + String.valueOf(time) + " segundos");
        fileWriter.write("\n");
        fileWriter.close();
    }
}
