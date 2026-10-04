package ordenadora;

import java.util.Arrays;

public final class Console{

    private Console() {}

    // Metodo que imprime a tela inicial do programa
    // Ele retorna o vetor de entrada inputado pelo usuario

    public static int[] telaInicial(){

        System.out.println("");

        System.out.println("Insira o tamanho do vetor a ser ordenado:");

        // O vetor devera ter, no minimo, um elemento.
        int tamanho = Teclado.lerInteiro(1, Integer.MAX_VALUE);

        System.out.println("Como deseja gerar os valores do vetor?");
        System.out.println("""
                [1] - Gerar manualmente
                [2] - Gerar aleatoriamente
                """);

        int opcao = Teclado.lerInteiro(1,2);

        // Os metodos de GerarVetor ja retornam um array de int que é o próprio retorno do
        // método corrente.
        return switch (opcao) {
            case 1 -> GeradorVetor.gerarManual(tamanho);
            case 2 -> GeradorVetor.gerarAleatorio(tamanho);
            default -> throw new IllegalStateException(
                    "Uma opção inesperada foi recebida:" + opcao
            );
        };
    }

    public static void telaDeResultado(
            int[] vetorOriginal,
            int[] vetorOrdenado,
            double tempoExecucaoSequencial,
            double tempoExecucaoParalela
    ) {

        System.out.println("Vetor ordenado com sucesso!");

        while(true){

            System.out.println("Selecione a opção:");
            System.out.println("""
                [1] - Exibir vetor original
                [2] - Exibir vetor ordenado
                [3] - Exibir tempo de execução
                [4] - Reiniciar novo vetor
                
                [5] - Sair
                """);

            int opcao = Teclado.lerInteiro(1, 5);

            switch (opcao) {
                case 1 -> imprimirVetor(vetorOriginal);
                case 2 -> imprimirVetor(vetorOrdenado);
                case 3 -> imprimirTempoDeExecucação(tempoExecucaoSequencial, tempoExecucaoParalela);
                case 4 -> { return; }
                case 5 -> System.exit(0);
            }
        }
    }

    private static void imprimirVetor(int[] vetor){

        System.out.println("""
            //
            // EXIBIÇÃO DO VETOR
            //
            """);

        System.out.println("Tamanho do vetor: " + vetor.length);

        System.out.println("""
            [1] - Exibir vetor completo
            [2] - Exibir os primeiros 100 elementos
            """);

        int opcao = Teclado.lerInteiro(1, 2);

        // O metodo Arrays.toString transforma o array em formato de string. Exemplo: [1, 2, 3, 4]
        switch (opcao) {
            case 1 -> {
                System.out.println(Arrays.toString(vetor));
            }

            case 2 -> {
                int limite = Math.min(100, vetor.length);

                int[] amostra = Arrays.copyOfRange(vetor, 0, limite);

                System.out.println(Arrays.toString(amostra));
            }
        }

        Teclado.aguardarTecla();
    }

    private static void imprimirTempoDeExecucação(double tempoExecucaoSequencial,
                                                  double tempoExecucaoParalela){

        double speedup = tempoExecucaoSequencial / tempoExecucaoParalela;

        System.out.println("""
                //
                // PERFORMANCE DE EXECUÇÃO
                //
                """);

        System.out.printf("Tempo sequencial: %.3f ms%n",
                tempoExecucaoSequencial);

        System.out.printf("Tempo paralelo:   %.3f ms%n",
                tempoExecucaoParalela);

        System.out.printf("Speedup:          %.2fx%n",
                speedup);

        Teclado.aguardarTecla();
    }
}
