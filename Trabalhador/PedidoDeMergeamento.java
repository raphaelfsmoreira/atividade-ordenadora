public class PedidoDeMergeamento extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final long idOperacao;
    private final int[] primeiroVetor;
    private final int[] segundoVetor;

    public PedidoDeMergeamento(long idOperacao, int[] primeiroVetor, int[] segundoVetor)
    {
        if (idOperacao < 0)
            throw new IllegalArgumentException("O identificador da operação não pode ser negativo");
        if (primeiroVetor == null || segundoVetor == null)
            throw new IllegalArgumentException("Os vetores não podem ser nulos");

        this.idOperacao = idOperacao;
        this.primeiroVetor = primeiroVetor.clone();
        this.segundoVetor = segundoVetor.clone();
    }

    public long getIdOperacao()
    {
        return this.idOperacao;
    }

    public int[] getPrimeiroVetor()
    {
        return this.primeiroVetor.clone();
    }

    public int[] getSegundoVetor()
    {
        return this.segundoVetor.clone();
    }
}
