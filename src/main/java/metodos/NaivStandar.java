package metodos;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebas
 */
public class NaivStandar {
    public void NaivStandard(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M){
        double aux;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aux = 0.0;
                for (int k = 0; k < P; k++) {
                    aux += matrizA[i][k] * matrizB[k][j];
                }
                matrizC[i][j] = aux;
            }
        }
    }
}
