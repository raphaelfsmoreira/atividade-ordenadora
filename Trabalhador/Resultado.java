public class Resultado extends Comunicado
{
    private long valorResultante;

    public Resultado (long valorResultante)
    {
        this.valorResultante = valorResultante;
    }

    public long getValorResultante ()
    {
        return this.valorResultante;
    }

    public String toString ()
    {
        return ""+this.valorResultante;
    }
}
