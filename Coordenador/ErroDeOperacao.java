public class ErroDeOperacao extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final long idOperacao;
    private final String mensagem;

    public ErroDeOperacao(long idOperacao, String mensagem)
    {
        if (idOperacao < 0)
            throw new IllegalArgumentException("O identificador da operação não pode ser negativo");
        if (mensagem == null || mensagem.isBlank())
            throw new IllegalArgumentException("A mensagem de erro não pode ser vazia");

        this.idOperacao = idOperacao;
        this.mensagem = mensagem;
    }

    public long getIdOperacao()
    {
        return this.idOperacao;
    }

    public String getMensagem()
    {
        return this.mensagem;
    }
}
