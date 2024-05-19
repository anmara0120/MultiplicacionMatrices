package metodos;

public class NaivOnArray extends TiempoEjecucion
{
    @Override
    public double[][] algoritmo(double [][] matriz) {
        nombreMetodo = "NaivOnArray";
        return NaivOnArray(matriz);
    }

    public double[][] NaivOnArray(double [][]matriz) {
        double[][] resultado = new double[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                resultado[i][j] = 0.0;
                for (int k = 0; k < matriz.length; k++) {
                    resultado[i][j] += matriz[i][k] * matriz[k][j];
                }
            }
        }
        return resultado;
    }
}
