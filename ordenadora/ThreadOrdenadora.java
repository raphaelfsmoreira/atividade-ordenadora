package ordenadora;

public class ThreadOrdenadora extends Thread{

    // Vetor de entrada
    private final int[] vetor;
    // Vetor de saida apos passar pela ordenação
    private int[] resultado;

    public ThreadOrdenadora(int[] vetor){
        this.vetor = vetor;
    }

    // Cada thread receberá uma partição do vetor e executará o metodo de ordenação do Merge Sort
    @Override
    public void run(){
        resultado = MergeSort.ordenar(vetor);
    }

    public int[] getResultado(){
        return resultado;
    }

}
