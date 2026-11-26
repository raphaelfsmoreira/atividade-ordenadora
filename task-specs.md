MANTENDO OS GRUPOS E INCLUSIVE O NÚMERO DOS GRUPOS DA ATIVIDADE 1, A MISSÃO DO EU GRUPO CONSISTE EM:

MODIFICAR O PROGRAMA DESENVOLVIDO E ENTREGUE NA ATIVIDADE 1 DA SEGUINTE FORMA:

a) MOVER deste programa para o programa descrito no item 2 (programa CLIENTE) a parte de perguntar o tamanho do vetor desejado e a parte de decidir digitar ou popular aleatoriamente o vetor;

b) TRANSFORMAR o que sobrar deste programa em um servidor que deverá ser posto em execução em várias máquinas e que recebe do programa CLIENTE um pedaço do vetor mencionado acima em (a) para ordenar conforme ordenava quando a atividade 1 foi entregue, ou seja, usando PARALELAMENTE os processadores disponíveis na máquina onde executa.
A main do servidor deverá iniciar a thread AceitadoraDeConexao para então passar a esperar a digitação do comando DESATIVAR que se for digitado encerrará a execução do servidor.
A thread AceitadoraDeConexao deverá iniciar a thread SupervisoraDeConexao e então esperar seu encerramento, fazendo uso de seu método join.
A thread Supervisora de conexão deverá atender a mensagens (instâncias de classes que herdam da classe Comunicado) solicitando ordenação ou mergeamento, além de resultado de ordenação e solicitação de mergeamento, que serão respondidas com mensagens indicando ordenação pronta, megeamento pronto e o resultado em si de uma ordenação/mergeamento, classe que deverá conter um vetor (o vetor resultante).
Esse SERVIDOR deverá atender apenas a um CLIENTE em um dado momento no tempo (por isso o uso do método join), embora possa atender em sequência outro CLIENTE que eventualmente rode e solicite conexão após o CLIENTE anterior haver mandado mensagem informando que está encerrando o uso do SERVIDOR.

IMPLEMENTAR um programa CLIENTE que pergunte o tamanho do vetor desejado e se prefere digitar valor por valor do vetor ou se prefere populá-lo aleatoriamente.
Isso feito, o programa CLIENTE deve solicitar conexão com os SERVIDORES que foram executados para lhe servir e cujo IP e porta de acesso serão informadas ao CLIENTE, por exemplo via o vetor args da main do CLIENTE.
DETALHE: os tamanhos das partes do vetor do CLIENTE que serão distribuídas aos SERVIDORES para ordenação deverão ter tamanhos proporcionais ao poder computacional da máquina que executar cada SERVIDOR.