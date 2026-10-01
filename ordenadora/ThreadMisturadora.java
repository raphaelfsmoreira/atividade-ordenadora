package ordenadora;

import java.util.Arrays;


public class ThreadMisturadora{

    public int[][] split(int[] vetor) throws InterruptedException{

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

    public void threadSplit(int [][]vectorChunks){
        Thread[] threads = new Thread[vectorChunks.length];

        for (int i = 0; i < vectorChunks.length; i++){
            int[] chunk = vectorChunks[1];
            int threadId = i + 1;

            threads[i] = new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println("[" + name + "] A processar Chunk " + threadId + ": " + Arrays.toString(chunk));
                
                OrdenadorParalelo.ordenar(chunk);
            });

            threads[i].setName("ThreadOrdenadora-" + threadId);
            threads[i].start();

        }
    }



}