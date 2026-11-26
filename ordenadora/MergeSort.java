package ordenadora;

import java.util.Arrays;

public class MergeSort{

    // O merge sort é uma classe utilitária. Não pode ser instanciada, portanto construtor private.
    private MergeSort(){ }

    public static int[] ordenar(int[] vetor){

        // Se o vetor estiver vazio ou ter um elemento, já o retorna
        if(vetor.length <= 1){
            return vetor;
        }

        int INICIO_VETOR = 0;
        int MEIO_VETOR = vetor.length / 2;
        int FIM_VETOR = vetor.length;

        // O método copyOfRange cria um vetor novo a partir de um vetor de argumento.
        // Copia-se de um indice inicio ate um indice fim (o indice fim é não incluso, por isso os itens não se
        // repetem nos vetores divididos).
        // Como nosso array é de um tipo primitivo, não precisa se preocupar com cópias rasas e referências...

        int[] esquerda = Arrays.copyOfRange(vetor, INICIO_VETOR, MEIO_VETOR);
        int[] direita = Arrays.copyOfRange(vetor, MEIO_VETOR, FIM_VETOR);


        // Chamada recursiva

        esquerda = ordenar(esquerda);
        direita = ordenar(direita);

        return intercalar(esquerda, direita);
    }

    public static int[] intercalar(int[] primeiro, int[] segundo){

        // Inicialização do vetor de retorno (fusão ordenada dos dois vetores de entrada).
        int[] retorno = new int[primeiro.length + segundo.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < primeiro.length && j < segundo.length) {
            if (primeiro[i] <= segundo[j]) {
                retorno[k++] = primeiro[i++];
            } else {
                retorno[k++] = segundo[j++];
            }
        }

        while (i < primeiro.length) {
            retorno[k++] = primeiro[i++];
        }

        while (j < segundo.length) {
            retorno[k++] = segundo[j++];
        }

        return retorno;
    }

}
