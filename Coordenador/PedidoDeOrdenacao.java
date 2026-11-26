public class PedidoDeOrdenacao extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final long idOperacao;
    private final int[] vetor;

    public PedidoDeOrdenacao(long idOperacao, int[] vetor)
    {
        if (idOperacao < 0)
            throw new IllegalArgumentException("O identificador da operação não pode ser negativo");
        if (vetor == null)
            throw new IllegalArgumentException("O vetor não pode ser nulo");

        this.idOperacao = idOperacao;
        this.vetor = vetor.clone();
    }

    public long getIdOperacao()
    {
        return this.idOperacao;
    }

    public int[] getVetor()
    {
        return this.vetor.clone();
    }
}
