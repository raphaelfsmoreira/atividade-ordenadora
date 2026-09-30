package ordenadora;


public class OrdenadorParalelo{

    public int[] ordenar(int[] vetor) throws InterruptedException{

        // Obtem a quantidade de processadores disponiveis
        int processadores_disponiveis = Runtime.getRuntime().availableProcessors();

        // Se existe apenas um processador disponível, haverá apenas uma parte dividida de vetor
        // Se não, haverá n-1 partes de divisão
        int quantidadeDePartes = processadores_disponiveis > 1? processadores_disponiveis - 1 : 1;

        System.out.println(processadores_disponiveis + "Processadores disponíveis");
        System.out.println("Dividindo o vetor em " + quantidadeDePartes +" partes.");



    }



}