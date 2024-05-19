import metodos.*;

import static persistencia.FicheroUtils.leerMatriz;

public class Principal {
    private static final int CASO_MAXIMO = 8;
    public static void main(String[] args) {
        int caso = 0;
        int i = 8;
        do {
            double [][] matriz = leerMatriz(i + "x" + i + ".txt");


           /* NaivOnArray naivOnArray = new NaivOnArray();
            naivOnArray.ejecutar(matriz);

            NaivLoopUnrollingTwo naivLoopUnrollingTwo = new NaivLoopUnrollingTwo();
            naivLoopUnrollingTwo.ejecutar(matriz);

            NaivLoopUnrollingFour naivLoopUnrollingFour = new NaivLoopUnrollingFour();
            naivLoopUnrollingFour.ejecutar(matriz);

            IV5EnhancedParallelBlock IV5EnhancedParallelBlock = new IV5EnhancedParallelBlock();
            IV5EnhancedParallelBlock.ejecutar(matriz);

            IV4ParallelBlock iv4ParallelBlock = new IV4ParallelBlock();
            iv4ParallelBlock.ejecutar(matriz);

            IV3SequentialBlock iv3SequentialBlock  = new IV3SequentialBlock();
            iv3SequentialBlock.ejecutar(matriz);

            III5EnhancedParallelBlock iii5EnhancedParallelBlock  = new III5EnhancedParallelBlock();
            iii5EnhancedParallelBlock.ejecutar(matriz);

            III4ParallelBlock iii4ParallelBlock  = new III4ParallelBlock();
            iii4ParallelBlock.ejecutar(matriz);

            III3SequentialBlock iii3SequentialBlock  = new III3SequentialBlock();
            iii3SequentialBlock.ejecutar(matriz);

            StrassenNaiv strassenNaiv = new StrassenNaiv();
            strassenNaiv.ejecutar(matriz);

            V4ParallelBlock v4ParallelBlock = new V4ParallelBlock();
            v4ParallelBlock.ejecutar(matriz);


            V3SequentialBlock V3SequentialBlock = new V3SequentialBlock();
            V3SequentialBlock.ejecutar(matriz);

            WinogradOriginal winogradOriginal = new WinogradOriginal();
            winogradOriginal.ejecutar(matriz);

            WinogradScaled winogradScaled = new WinogradScaled();
            winogradScaled.ejecutar(matriz);*/

            StrassenWinograd strassenWinograd = new StrassenWinograd();
            strassenWinograd.ejecutar(matriz);

            caso++;
            i*=2;
        }while (caso < CASO_MAXIMO);

    }

}
