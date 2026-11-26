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

A classe `Coordenadora` reúne os métodos de particionamento e execução das ordenadoras e misturadoras. `GeradorVetor`, `MergeSort` e `Teclado` são utilitários compartilhados no pacote `utils`.

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
client/
├── Main.java               # Fluxo principal
├── Console.java            # Entrada, menus e exibição dos resultados
├── Coordenadora.java       # Particionamento e gerenciamento de threads
├── ThreadOrdenadora.java   # Ordena uma partição
└── ThreadMisturadora.java  # Intercala duas partições ordenadas
utils/
├── GeradorVetor.java       # Geração manual e aleatória de vetores
├── MergeSort.java          # Ordenação recursiva e intercalação
└── Teclado.java            # Leitura e validação de dados no console
server/                     # Reservado para os componentes de servidor
```

## Requisitos e execução

- **Java 17 ou superior** (para suporte às funcionalidades de linguagem utilizadas no projeto).
- JDK instalado e disponível no terminal.
- Não são necessárias bibliotecas externas para o algoritmo.

Na raiz do projeto, compile os arquivos dos pacotes `client` e `utils`:

```bash
javac -d build client/*.java utils/*.java
java -cp build client.Main
```

No Windows PowerShell, se o curinga não for expandido pelo terminal, compile usando o IntelliJ IDEA ou o VS Code com a extensão Java, ou informe os arquivos `.java` explicitamente ao `javac`.

### Executar o exemplo distribuído

Na raiz do projeto, execute `run.bat` e escolha uma opção, ou passe-a diretamente:

```text
run.bat client
run.bat server
run.bat both
```

`client` inicia o Coordenador e solicita N e os endereços dos trabalhadores (`host:porta`). `server` inicia um Trabalhador na porta informada (3001 por padrão). `both` pergunta quantos trabalhadores iniciar (3 por padrão), abre cada um em uma nova janela usando portas consecutivas a partir da porta informada (3001 por padrão) e executa o Coordenador conectado a eles.

## Interação pelo console

O programa solicita o tamanho e a forma de preenchimento do vetor. Após as ordenações, o menu de resultados permite consultar a entrada original, a saída ordenada, os tempos de execução, iniciar outro teste ou sair.

Para entradas muito grandes, recomenda-se exibir apenas uma amostra dos elementos, evitando sobrecarregar o terminal.

## Observações

- A implementação descrita trabalha com `int[]`.
- O uso de múltiplas threads não garante ganho de desempenho para todos os tamanhos de vetor: o particionamento e o gerenciamento das threads também têm custo.
- A sincronização com `join()` garante que os resultados de uma rodada estejam disponíveis antes de iniciar a seguinte; falhas ocorridas dentro das threads devem ser tratadas separadamente.

## Contexto acadêmico

Projeto desenvolvido para estudo de **Programação Paralela e Concorrente**, com foco em threads, sincronização e comparação entre algoritmos sequenciais e paralelos.
