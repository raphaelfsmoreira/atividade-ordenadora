// Classe que faz toda a lógica de dividir o vetor e coordenar as threads de ordenaçao e intercalação do mergesort.

// O fluxo lógico é o seguinte:
// 0. Obter o número de threads que existirão com base nos processadores disponíveis (método obterQuantidadeThreads()).
// 1. Particionamento do vetor de acordo com o número de threads (método particionarVetor()).
// 2. Criar as threads ordenadoras (metodo OrdenarParticoes()).
// 3. Inicia as threads
// 4. Aguarda cada uma com join().
// 5. Criar e executar as threads misturadoras (metodo
// 6. Retornar o vetor ordenado

package ordenadora;

import java.util.Arrays;

public class Coordenadora {

    private Coordenadora(){}

    public static int obterQuantidadeThreads(int tamanhoVetor){
        // Obtem a quantidade de processadores disponiveis
        int processadoresDisponiveis = Runtime.getRuntime().availableProcessors();

        // A quantidade de threads será, no mínimo 1 ou no máximo o número de elementos do vetor
        // Não há razão para ter mais threads do que número de elementos...
        int quantidadeThreads = Math.max(
                1,
                Math.min(processadoresDisponiveis - 1, tamanhoVetor)
        );

        System.out.println("Processadores disponíveis = " + processadoresDisponiveis);
        System.out.println("Número de threads = "+ quantidadeThreads);

        return quantidadeThreads;
    }

    public static int[][] particionarVetor(int[] vetor, int quantidadeParticoes){

        // Lembrando que a quantidade de particoes do vetor será a quantidade de threads calculada.

        int[][] particoes = new int[quantidadeParticoes][];

        int tamanhoBase = vetor.length / quantidadeParticoes;
        int resto = vetor.length % quantidadeParticoes;

        // Para o caso de divisões não exatas do vetor
        // Por exemplo: 11 elementos para 3 threads.
        // 11 / 3 = 3 partições
        // 11 % 3 = 2 resto.
        // Logo, o tamanho inicial planejado seria de 3 particoes de tamanho 3, mas sobrariam 2 elementos sem estar em
        // partições.
        // A ideia é redistribuir os elementos que sobraram nas primeiras (n-1) partições. Desse modo,
        // sempre há n-1 particoes de tamanho ()
        // Para o exemplo, então, teriamos 2 partições de 4 elementos e 1 partição de 3 elementos (4+4+3 = 11).

        int inicio = 0;

        for (int i = 0; i < quantidadeParticoes; i++) {

            // O tamanho do array irá aumentar em 1 para as (n-1) partições.
            int tamanho = tamanhoBase + (i < resto ? 1 : 0);

            particoes[i] = Arrays.copyOfRange(
                    vetor, inicio, inicio + tamanho
            );

            // A próxima iteração inicia no índice do tamanho da partição anterior...
            inicio += tamanho;
        }

        return particoes;
    }

    public static int[][] ordenarParticoes(int[][] particoes) throws InterruptedException{

        // A quantidade de particoes é estritamente igual ao número de threads definido...
        ThreadOrdenadora[] ordenadoras = new ThreadOrdenadora[particoes.length];

        // Cada thread é instanciada e recebe sua respectiva partição...

        for(int i = 0; i < particoes.length; i++){

            ordenadoras[i] = new ThreadOrdenadora(particoes[i]);

            ordenadoras[i].start();
            System.out.println("Iniciando thread ordenadora " + (i+1));
        }

        // Aguarda as threads ordenadoras

        for(int i = 0; i < particoes.length; i++){

            ordenadoras[i].join();

            // Cada partição recebe sua ordenada respectiva
            particoes[i] = ordenadoras[i].getResultado();

        }

        return particoes;
    }

    public static int[] misturarParticoes(int[][] particoes) throws InterruptedException{

        while(particoes.length > 1){

            // Define quantos pares de intercalação haverá, e se haverá uma partição sem par
            int pares = particoes.length / 2;
            int restoParticoes = particoes.length % 2;

            // Aqui haverá
            ThreadMisturadora misturadoras[] = new ThreadMisturadora[pares];

            // A matriz armazena o indice das partições que participarão da próxima rodada
            int[][] proximasParticoes = new int[pares + restoParticoes][];

            // Criando e iniciando as misturadoras

            for(int i = 0; i < pares; i++){

                // Lembrando que o metodo do merge de intercalar recebe duas particoes.
                // No caso, recebera duas particoes adjacentes (0,1), (1,2), etc.
                misturadoras[i] = new ThreadMisturadora(
                        particoes[2*i],
                        particoes[2*i + 1]
                );

                misturadoras[i].start();

            }

            // Aguarda a conclusao de cada rodada
            for(int i = 0; i < pares; i++){

                misturadoras[i].join();

                proximasParticoes[i] = misturadoras[i].getResultado();
            }

            // Carrega a partição sem par caso haja uma

            if(restoParticoes == 1){

                proximasParticoes[pares] = particoes[particoes.length - 1];

            }

            particoes = proximasParticoes;
        }

        return particoes[0];

    }

}


