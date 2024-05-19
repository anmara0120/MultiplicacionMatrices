import metodos.NaivLoopUnrollingTwo;
import metodos.NaivOnArray;

import static persistencia.FicheroUtils.leerMatriz;

public class Principal {
    private static final int CASO_MAXIMO = 8;
    public static void main(String[] args) {
        int caso = 0;
        int i = 8;
        do {
            double [][] matriz = leerMatriz(i + "x" + i + ".txt");

            NaivOnArray naivOnArray = new NaivOnArray();
            naivOnArray.ejecutar(matriz);



            NaivLoopUnrollingTwo naivLoopUnrollingTwo = new NaivLoopUnrollingTwo();
            naivLoopUnrollingTwo.ejecutar(matriz);

            caso++;
            i*=2;
        }while (caso < CASO_MAXIMO);

    }

}
