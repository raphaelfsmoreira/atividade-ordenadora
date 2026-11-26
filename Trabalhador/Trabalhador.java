import java.util.*;

// Equivalente ao "Servidor" do Fazedor de Continhas,
// mas aqui ele faz parte de um grupo de trabalhadores
// que cooperam, cada um em sua propria maquina/porta.
public class Trabalhador
{
    public static String PORTA_PADRAO = "3001";

    public static void main (String[] args)
    {
        if (args.length>1)
        {
            System.err.println ("Uso esperado: java Trabalhador [PORTA]\n");
            return;
        }

        String porta=Trabalhador.PORTA_PADRAO;

        if (args.length==1)
            porta = args[0];

        ArrayList<Parceiro> usuarios =
        new ArrayList<Parceiro> ();

        try
        {
            AceitadoraDeConexao aceitadoraDeConexao =
            new AceitadoraDeConexao (porta, usuarios);
            aceitadoraDeConexao.start();
        }
        catch (Exception erro)
        {
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }

        System.out.println ("Trabalhador ativo na porta "+porta+"!");
        System.out.println ("Para desativa-lo, use Ctrl+C\n");
    }
}
