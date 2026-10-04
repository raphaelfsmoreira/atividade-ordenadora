package ordenadora;


public class ThreadMisturadora extends Thread{

    private final int[] primeiro;
    private final int[] segundo;
    private int[] resultado;


    public ThreadMisturadora(int[] primeiro, int[] segundo){
        this.primeiro = primeiro;
        this.segundo = segundo;
    }

    @Override
    public void run(){
        resultado = MergeSort.intercalar(primeiro, segundo);
    }

    public int[] getResultado(){
        return resultado;
    }

}