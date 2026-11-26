package ordenadora;

import java.util.Scanner;

final class Teclado{

    private static final Scanner SCANNER = new Scanner(System.in);

    // Classe de metodos estaticos. Nao precisa de construtor publico...
    private Teclado() {}

    public static void aguardarTecla() {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        SCANNER.nextLine();
    }

    // Metodo para leitura de int com as validaçoes (valor minimo e maximo permitidos)
    public static int lerInteiro(int minimoValido, int maximoValido) {

        // Excecao para tempo de compilacao
        if (minimoValido > maximoValido) {
            throw new IllegalArgumentException(
                    "O mínimo não pode ser maior que o máximo."
            );
        }

        while (true) {
            String input = SCANNER.nextLine().trim();

            try {
                int valor = Integer.parseInt(input);

                if (valor >= minimoValido && valor <= maximoValido) {
                    return valor;
                }

                System.out.println(
                        "Por favor, insira um número de "
                                + minimoValido + " a " + maximoValido + "."
                );

                // Captura a excecao de erro de parsing nao possivel (usuario insere um char, por exemplo).
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }
    }

    // Sobrecarga para sem intervalo de validacao (implicitamente seria o menor e maior valor de int
    // para a linguagem...

    public static int lerInteiro(){
        return lerInteiro(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Sobrecarga do metodo para exibição de cabeçalho com mensagem
    public static int lerInteiro(
            String mensagem,
            int minimoValido,
            int maximoValido
    ) {
        System.out.println(mensagem);

        return lerInteiro(minimoValido, maximoValido);
    }
}
