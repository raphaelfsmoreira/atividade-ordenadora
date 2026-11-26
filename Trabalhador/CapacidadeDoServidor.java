public class CapacidadeDoServidor extends Comunicado
{
    private static final long serialVersionUID = 1L;

    private final int processadoresDisponiveis;

    public CapacidadeDoServidor(int processadoresDisponiveis)
    {
        if (processadoresDisponiveis < 1)
            throw new IllegalArgumentException("A capacidade deve ser positiva");

        this.processadoresDisponiveis = processadoresDisponiveis;
    }

    public int getProcessadoresDisponiveis()
    {
        return this.processadoresDisponiveis;
    }
}
