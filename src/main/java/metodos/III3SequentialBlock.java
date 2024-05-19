package metodos;

public class III3SequentialBlock extends TiempoEjecucion
{
    @Override
    public double[][] algoritmo(double[][] matriz) {
        nombreMetodo = "III3SequentialBlock";
        return lll_3SequentialBlock(matriz);
    }

    public double[][] lll_3SequentialBlock(double [][] matriz){
        int size = matriz.length;
        int bsize = (int) Math.sqrt(size);
        double [][] matrizResultado = new double [size][size];
        for (int i1 = 0; i1 < size; i1 += bsize) {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    for (int i = i1; i < i1 + bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                matrizResultado[i][j] += matriz[i][k] * matriz[k][j];
                            }
                        }
                    }
                }
            }
        }
        return matrizResultado;
    }
}

