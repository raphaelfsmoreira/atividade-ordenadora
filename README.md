# Merge Sort Paralelo em Java

Projeto acadêmico de implementação e análise de desempenho do algoritmo **Merge Sort**, nas versões **sequencial** e **paralela**, utilizando threads nativas do Java.

## Objetivo

Aplicar os conceitos de programação concorrente, divisão de trabalho e sincronização de threads à ordenação de vetores de números inteiros. O programa permite comparar o tempo de execução das duas abordagens sobre a mesma entrada.

## Funcionalidades

- Criação de um vetor `int[]` com tamanho definido pelo usuário.
- Preenchimento manual ou geração aleatória dos elementos.
- Ordenação sequencial com Merge Sort recursivo.
- Ordenação paralela com threads ordenadoras e misturadoras.
- Sincronização das threads com `join()`.
- Exibição do vetor original, do vetor ordenado e dos tempos de execução.
- Reinício do fluxo para testar um novo vetor.

## Funcionamento

### Versão sequencial

O método `MergeSort.ordenar()` divide recursivamente o vetor em duas metades, ordena cada metade e utiliza `MergeSort.intercalar()` para reunir os elementos em ordem crescente. A execução acontece na thread chamadora.

### Versão paralela

A execução é organizada em duas fases:

1. **Ordenação das partições:** a quantidade inicial de threads é baseada no número de processadores lógicos disponíveis, reservando um processador quando possível e limitando a quantidade de threads ao número de elementos. O vetor é dividido em partições de tamanhos aproximadamente iguais, e cada `ThreadOrdenadora` executa o Merge Sort em uma delas.
2. **Intercalação em rodadas:** depois que todas as ordenadoras terminam, cada `ThreadMisturadora` intercala um par de partições ordenadas. As threads de uma rodada são iniciadas antes de serem aguardadas com `join()`. Se houver uma partição sem par, ela passa diretamente para a rodada seguinte. O processo continua até restar um único vetor.

Exemplo com oito partições:

```text
8 partições → 8 threads ordenadoras
8 partições → 4 threads misturadoras → 4 partições
4 partições → 2 threads misturadoras → 2 partições
2 partições → 1 thread misturadora  → 1 vetor ordenado
```

A classe `Coordenadora` reúne os métodos estáticos de particionamento, execução das ordenadoras e execução das misturadoras. A `Main` chama essas etapas em sequência.

## Medição de desempenho

Os tempos são medidos com `System.nanoTime()` e exibidos em milissegundos:

```java
double tempoMs = (fim - inicio) / 1_000_000.0;
```

O ganho relativo da execução paralela pode ser expresso pelo **speedup**:

\[
S = \frac{T_{sequencial}}{T_{paralelo}}
\]

- `S > 1`: a execução paralela foi mais rápida.
- `S = 1`: os tempos foram equivalentes.
- `S < 1`: a execução sequencial foi mais rápida.

O tempo paralelo total deve incluir particionamento, criação e sincronização das threads e intercalação. A comparação utiliza os mesmos dados de entrada. Para análises confiáveis, recomenda-se repetir as medições após o aquecimento da JVM e comparar os resultados agregados.

## Estrutura do código

```text
ordenadora/
├── Main.java               # Fluxo principal
├── Console.java            # Entrada, menus e exibição dos resultados
├── MergeSort.java          # Ordenação recursiva e intercalação
├── Coordenadora.java       # Particionamento e gerenciamento de threads
├── ThreadOrdenadora.java   # Ordena uma partição
└── ThreadMisturadora.java  # Intercala duas partições ordenadas
```

> A estrutura acima representa a organização discutida para a versão final do projeto. O código-fonte deve ser mantido atualizado com ela.

## Requisitos e execução

- **Java 17 ou superior** (para suporte às funcionalidades de linguagem utilizadas no projeto).
- JDK instalado e disponível no terminal.
- Não são necessárias bibliotecas externas para o algoritmo.

Na raiz do projeto, considerando os arquivos `.java` dentro da pasta `ordenadora/` e os nomes de pacote `ordenadora`:

```bash
javac -d out ordenadora/*.java
java -cp out ordenadora.Main
```

No Windows PowerShell, se o curinga não for expandido pelo terminal, compile usando o IntelliJ IDEA ou o VS Code com a extensão Java, ou informe os arquivos `.java` explicitamente ao `javac`.

## Interação pelo console

O programa solicita o tamanho e a forma de preenchimento do vetor. Após as ordenações, o menu de resultados permite consultar a entrada original, a saída ordenada, os tempos de execução, iniciar outro teste ou sair.

Para entradas muito grandes, recomenda-se exibir apenas uma amostra dos elementos, evitando sobrecarregar o terminal.

## Observações

- A implementação descrita trabalha com `int[]`.
- O uso de múltiplas threads não garante ganho de desempenho para todos os tamanhos de vetor: o particionamento e o gerenciamento das threads também têm custo.
- A sincronização com `join()` garante que os resultados de uma rodada estejam disponíveis antes de iniciar a seguinte; falhas ocorridas dentro das threads devem ser tratadas separadamente.

## Contexto acadêmico

Projeto desenvolvido para estudo de **Programação Paralela e Concorrente**, com foco em threads, sincronização e comparação entre algoritmos sequenciais e paralelos.
