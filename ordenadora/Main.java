package ordenadora;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        while (true) {

            int[] vetorEntrada = Console.telaInicial();

            // =============================================================================================
            // ORDENAÇÃO SEQUENCIAL (sem criação de threads, apenas pela recursividade de MergeSort.ordenar()
            // =============================================================================================

            System.out.println("\nIniciando ordenação sequencial...");

            long tempoIncioSequencial = System.nanoTime();

            int[] ordenarSequencial = MergeSort.ordenar(vetorEntrada);

            long tempoFimSequencial =  System.nanoTime();

            long tempoExecucaoSequencial = tempoFimSequencial - tempoIncioSequencial;

            System.out.println("\nOrdenação sequencial concluída!");

            // ===============================================================================================
            // ORDENAÇÃO PARALELA
            // ===============================================================================================

            System.out.println("\nIniciando ordenação paralela...");

            long tempoInicioParalela = System.nanoTime();

            // Lembrando que toda a lógica da organização da ordenação está na classe Ordenadora

            // Obter quantidade de Threads
            int quantidadeThreads = Coordenadora.obterQuantidadeThreads(vetorEntrada.length);

            // Particionamento do vetor
            int[][] particoes = Coordenadora.particionarVetor(vetorEntrada, quantidadeThreads);

            // Organização das threads ordenadoras
            particoes = Coordenadora.ordenarParticoes(particoes);

            // Organização das threads misturadoras

            int[] resultado = Coordenadora.misturarParticoes(particoes);

            long tempoFimParalela = System.nanoTime();

            long tempoExecucaoParalela = tempoFimParalela - tempoInicioParalela;

            System.out.println("\nOrdenação paralela concluída!");

            Console.telaDeResultado(vetorEntrada,
                    resultado,
                    tempoExecucaoSequencial,
                    tempoExecucaoParalela);

        }
    }
}