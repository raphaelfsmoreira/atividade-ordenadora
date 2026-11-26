public class MergeamentoPronto extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final long idOperacao;

    public MergeamentoPronto(long idOperacao)
    {
        if (idOperacao < 0)
            throw new IllegalArgumentException("O identificador da operação não pode ser negativo");

        this.idOperacao = idOperacao;
    }

    public long getIdOperacao()
    {
        return this.idOperacao;
    }
}
