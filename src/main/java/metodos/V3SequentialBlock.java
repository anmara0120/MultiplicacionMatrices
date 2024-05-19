package metodos;

public class V3SequentialBlock {
    public void v_3SequentialBlock(double [][] matrizA, double [][] matrizB, int size){
        int bsize = (int) Math.sqrt(size);
        double [][] matrizResultado = new double [size][size];
        for (int i1 = 0; i1 < size; i1 += bsize) {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    for (int i = i1; i < i1 + bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                matrizResultado[i][k] += matrizA[i][j] * matrizB[j][k];
                            }
                        }
                    }
                }
            }
        }
    }
}
