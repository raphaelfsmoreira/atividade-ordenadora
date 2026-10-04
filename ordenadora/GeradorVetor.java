package ordenadora;

import java.util.Random;

public class GeradorVetor {

    // Classe estática, apenas utilitaria para gerar vetores. Construtor privado.
    private GeradorVetor(){}

    public static int[] gerarManual(int tamanho){
        int[] retorno = new int[tamanho];


        System.out.println("Insira os valores de cada elemento do vetor manualmente");

        for(int i = 0; i < tamanho; i++){

            int entrada = Teclado.lerInteiro(
                    "Elemento" + "[" + i + "] = ",
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE
            );

            retorno[i] = entrada;

        }
        return retorno;
    }

    public static int[] gerarAleatorio(int tamanho){

        Random random = new Random();
        int[] retorno = new int[tamanho];

        for(int i = 0; i < tamanho; i++){
            // random.nextInt() sem argumentos gera todo o range de ints possivel
            // de Integer.MIN_VALUE ate Integer.MAX_VALUE.
            retorno[i] = random.nextInt();
        }

        System.out.print("Vetor de " + tamanho + " elementos gerado!");
        return retorno;
    }

}
