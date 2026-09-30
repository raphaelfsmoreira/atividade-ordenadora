package ordenadora;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        InterfaceConsole console = new InterfaceConsole(scanner);

        int[] vetor = console.obterVetor();

        // Uma vez criado o vetor, se inicia os trabalhos de divisão do vetor e ordenação.

        // A classe ordenadora é quem orquestra essas operações...



    }

}