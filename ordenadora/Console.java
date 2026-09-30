package ordenadora;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InterfaceConsole{

    private final Scanner scanner;

    // Construtor da interface do console recebe um objeto Scanner para input vindo da main.

    public InterfaceConsole(Scanner scanner){

        this.scanner = scanner;

    }

    // Primeira pergunta ao usuário é o tamanho do vetor, seguido de que se deseja completá-lo
    // com valores inseridos manualmente ou que sejam preenchidos com valores aleatórios.

    public int[] obterVetor(){

        int tamanho = lerInteiro(
                "Quantos elementos deseja no vetor?", // Enunciado de pergunta no console
                1, // Valor mínimo para validação.
                Integer.MAX_VALUE // Valor máximo possível num int em Java.
        );

        System.out.println("\n Como deseja preencher o vetor?");
        System.out.println("[1] - Digitar valores manualmente");
        System.out.println("[2] - Gerar valores aleatoriamente");

        int opcao = lerInteiro(
                "Escolha sua opção:",
                1,
                2
        )

        if(opcao == 1){ vetor = gerarVetorManualmente(tamanho); }

        return gerarVetorAleatorio(tamanho);

    }

    // Método para obter os valores inseridos manualmente pelo usuário para preencher o vetor

    private int[] gerarVetorManualmente(int tamanhoVetor){

        int[] vetor = new int[tamanho];

        for(i = 0; i < tamanho; i++){

            // Vetor na posição i recebe um int
            vetor[i] = lerInteiro(
                    "Elemento [" + i + "] = ",
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE
            );
        }

        return vetor;
    }

    private int[] gerarVetorAleatorio(int tamanhoVetor){

        int[] vetor = new int[tamanho];

        for(i = 0; i < tamanho; i++){

            vetor[i] = Random.nextInt(100000);

        }

        System.out.println("Vetor aleatório gerado");

        return vetor;

    }


    // Método para ler inteiros do console.
    // Cada leitura é associada a um enunciado com mensagem (pergunta ao usuário),
    // e um valor mínimo e máximo que passará por validação.
    private int lerInteiro(String mensagem, int minimo, int maximo) {

        // Fica recursivamente tentando obter um int válido
        while (true) {

            System.out.print(mensagem);

            // Lê a linha inteira para evitar problemas de quebra
            // de linha ao misturar nextInt() e nextLine().
            String entrada = scanner.nextLine().trim();

            try {
                int valor = Integer.parseInt(entrada);

                // Validação.
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }

                System.out.printf(
                        "Digite um valor entre %d e %d.%n",
                        minimo,
                        maximo
                );
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public int exibirMenu(int[] vetorOrdenado, long tempoExecucao){

        while(True){
            System.out.println("======= VETOR ORDENADO COM SUCESSO ========");
            System.out.println("[1] - Exibir vetor ordenado");
            System.out.println("[2] - Exibir um intervalo do vetor");
            System.out.println("[3] - Exibir tempo de execução");
            System.out.println("[4] - Testar novo vetor");
            System.out.println("");
            System.out.println("[5] - Sair");

        }

        switch (opcao){
            case 1: hi
            case 2:
            case 3:
            case 4:
                return;
            case 5:
                System.exit(0);

        }
    }
}