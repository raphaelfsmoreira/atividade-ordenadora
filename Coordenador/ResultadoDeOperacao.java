public class ResultadoDeOperacao extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final long idOperacao;
    private final int[] vetorResultante;

    public ResultadoDeOperacao(long idOperacao, int[] vetorResultante)
    {
        if (idOperacao < 0)
            throw new IllegalArgumentException("O identificador da operação não pode ser negativo");
        if (vetorResultante == null)
            throw new IllegalArgumentException("O vetor resultante não pode ser nulo");

        this.idOperacao = idOperacao;
        this.vetorResultante = vetorResultante.clone();
    }

    public long getIdOperacao()
    {
        return this.idOperacao;
    }

    public int[] getVetorResultante()
    {
        return this.vetorResultante.clone();
    }
}
