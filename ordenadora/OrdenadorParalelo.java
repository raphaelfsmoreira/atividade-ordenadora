package ordenadora;

import java.util.Arrays;


public class OrdenadorParalelo{

    public int[][] ordenar(int[] vetor) throws InterruptedException{

        int processadores_disponiveis = Runtime.getRuntime().availableProcessors();

        int processorsNormalized = processadores_disponiveis > 1? processadores_disponiveis - 1 : 1;

        System.out.println(processadores_disponiveis + "Processadores disponíveis");
        System.out.println("Dividindo o vetor em " + processorsNormalized +" partes.");
        
        int vectorSize = vetor.length;
        int chunkSize = vectorSize / processorsNormalized;

        int[][] vectorChunks = new int[chunkSize][];

        for (int i = 0; i < chunkSize; i++){
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, vetor.length); 

            vectorChunks[i] = Arrays.copyOfRange(vetor, start, end);
        }

        return vectorChunks;


    }



}