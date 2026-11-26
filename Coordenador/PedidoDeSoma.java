public class PedidoDeSoma extends Comunicado
{
    private long inicio;
    private long fim;

    public PedidoDeSoma (long inicio, long fim)
    {
        this.inicio = inicio;
        this.fim    = fim;
    }

    public long getInicio ()
    {
        return this.inicio;
    }

    public long getFim ()
    {
        return this.fim;
    }

    public String toString ()
    {
        return ("soma de "+this.inicio+" ate "+this.fim);
    }
}
