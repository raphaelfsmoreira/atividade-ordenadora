import java.util.*;

// Divide a soma de 1 ate N entre varios trabalhadores,
// pede a cada um a sua parte e junta os resultados.
public class Coordenador
{
    public static void main (String[] args)
    {
        if (args.length<2)
        {
            System.err.println ("Uso esperado: java Coordenador N host:porta [host:porta ...]");
            System.err.println ("Exemplo:      java Coordenador 1000 host1:3001 host2:3002\n");
            return;
        }

        long n;
        try
        {
            n = Long.parseLong (args[0]);
            if (n<1) throw new Exception();
        }
        catch (Exception erro)
        {
            System.err.println ("N deve ser um numero inteiro positivo!\n");
            return;
        }

        int quantos = args.length-1;
        ArrayList<ConversaComTrabalhador> conversas =
        new ArrayList<ConversaComTrabalhador> ();

        try
        {
            long tamanho = n / quantos;
            long inicio  = 1;

            for (int i=0; i<quantos; i++)
            {
                String[] hostEPorta = args[i+1].split(":");
                long fim = (i==quantos-1) ? n : inicio+tamanho-1; // o ultimo leva o resto

                ConversaComTrabalhador conversa =
                new ConversaComTrabalhador (hostEPorta[0],
                                            Integer.parseInt(hostEPorta[1]),
                                            inicio, fim);
                conversas.add (conversa);
                inicio = fim+1;
            }
        }
        catch (Exception erro)
        {
            System.err.println ("Indique os trabalhadores como host:porta!\n");
            return;
        }

        // dispara todas as conversas ao mesmo tempo
        for (ConversaComTrabalhador conversa:conversas)
            conversa.start();

        // espera todas terminarem (ja conhecem join de threads)
        long total = 0;
        for (ConversaComTrabalhador conversa:conversas)
        {
            try
            {
                conversa.join();
            }
            catch (InterruptedException erro)
            {}

            if (!conversa.deuCerto())
            {
                System.err.println ("\nUm trabalhador falhou; o resultado seria incompleto!");
                return;
            }

            total += conversa.getResultado();
        }

        System.out.println ("\nSoma de 1 ate "+n+" = "+total);
        System.out.println ("Conferindo pela formula n(n+1)/2 = "+(n*(n+1)/2));
    }
}
