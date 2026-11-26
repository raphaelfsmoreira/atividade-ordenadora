import java.io.*;
import java.net.*;

// Uma thread por trabalhador: cada uma faz o papel de
// "Cliente" do Fazedor de Continhas, mas todas rodam ao
// mesmo tempo, cada uma falando com um servidor diferente.
public class ConversaComTrabalhador extends Thread
{
    private String host;
    private int    porta;
    private long   inicio;
    private long   fim;

    private long    resultado = 0;
    private boolean deuCerto  = false;

    public ConversaComTrabalhador
    (String host, int porta, long inicio, long fim)
    throws Exception
    {
        if (host==null)
            throw new Exception ("Host ausente");

        this.host   = host;
        this.porta  = porta;
        this.inicio = inicio;
        this.fim    = fim;
    }

    public void run ()
    {
        try
        {
            Socket conexao = new Socket (this.host, this.porta);

            ObjectOutputStream transmissor =
            new ObjectOutputStream (conexao.getOutputStream());

            ObjectInputStream receptor =
            new ObjectInputStream (conexao.getInputStream());

            Parceiro trabalhador =
            new Parceiro (conexao, receptor, transmissor);

            System.out.println ("Enviando para "+this.host+":"+this.porta+
                                " -> soma de "+this.inicio+" ate "+this.fim);

            trabalhador.receba (new PedidoDeSoma (this.inicio, this.fim));

            Comunicado comunicado = trabalhador.envie ();
            if (comunicado instanceof Resultado)
            {
                this.resultado = ((Resultado)comunicado).getValorResultante();
                this.deuCerto  = true;
            }

            trabalhador.receba (new PedidoParaSair ());
            trabalhador.adeus  ();
        }
        catch (Exception erro)
        {
            System.err.println ("Falha com o trabalhador "+this.host+":"+this.porta);
        }
    }

    public boolean deuCerto ()
    {
        return this.deuCerto;
    }

    public long getResultado ()
    {
        return this.resultado;
    }
}
