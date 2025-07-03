# das-1-walter
projeto read.me

# AULA 05/03/25

-- Padrão arquitetura: Solução para um problema específico

- Microsserviços
- Publisher/Subscriber
- MVC -> Separa as responsabilidades, entre view (tela), model (dados) e controller (comportamento)
- Camadas

-- Estilo: Organização do projeto

- Arquiteturas em camadas Três camadas -> Cliente, lógica, banco.
- Divisão de responsabilidade
- Segurança
- Manutenibilidade
- Performance
- Camada de apresentação (tela)
  - Requisitos próprios
- Camada de aplicação (lógica de negócio)
  - Escalabilidade
  - Controle de regras de negócio
- Camada de persistência (banco de dados)
  - Banco de dados relacional - consolidado
  - Resolve problemas de concorrência
  - Permite compartilhamento de dados

# Aula 06/03/2025
Who Needs an Architect? - https://martinfowler.com/ieeeSoftware/whoNeedsArchitect.pdf
- O que é arquitetura? - É o core do produto, do que todas as pessoas entendem que seja necessário para rodar, a lógica principal, é entender quais dos pequenos componentes do sistemas compõem a lógica do negócio/produto.
- Qual o comportamento do arquiteto da "Matrix"? - Toma todas as decisões importantes porque ninguém está com o nivel de entendimento sobre o sistema para conseguir tomar as decisões.
- Qual o comportamento do arquiteto ideal? - Toma menos decisões por conseguir melhorar os times e companheiros de equipe no entendimento das funções principais do sistema. Aumentando o nível do outro, ele ganha leverage para não ter todas as decisões de arquitetura centradas em uma pessoa só.

# Aula 19/03/2025

Filas e Tópicos -> Kafka, RabbitMQ
Fan out
Tópico só manda
Fila -> pooling 

# AULA 16/04/2025

Características operacionais de arquitetura: Disponibilidade, continuidade, desempenho, recuperabilidade, segurança, robustez e escalabilidade.
- Operação da arquitetura, como será a performance final da aplicação.


Características estruturais: configuração, extensão, instabilidade, reutilização, localização, manutenção, portabilidade, suporte e atualização. 

- Estrutura de código.

Características Transversais: acessibilidade, armazenamento, autenticação, autorização, legalidade, privacidade, segurança, suporte, usabilidade.

- Restrições específicas para o problema que a arquitetura resolve.
Toda arquitetura será um misto dessas características, não podendo ter todas, visto que uma pode onerar outra. Mirar na arquitetura que menos onera o sistema e que mais atende o requisito do sistema, independente das melhores técnicas.

# AULA 23/04

Bola de lama -> ausencia de arquitetura definida, mais comum, alto custo de manutenção, dificuldade de escalabilidade.

Débito técnico -> coisas que deveriam ter sido feitas mas não foram por diversas razões.

Arquitetura -> Cliente/Servidor.
- Desktop e servidor banco de dados -> Database centric, fácil de trocar informação, resolve concorrência.
- Navegador e servidor web.
- Três camadas.

Arquiteturas monolíticas
- Lógica do sistema em um unico repositório.
- Camadas
- Pipelines
- Microkernel
Arquiteturas distribuídas
- baseado em serviços
- orientado a eventos
- baseada em espaços
- orientada a serviços
- microsserviços

Falácias
- redes é seguro
- Latência é zero
- Banda larga é infinita
- A topologia nunca muda

Desafios sistemas distribuídos:
 - Logs distribuídos.
 - Transações distribuídas: Diversos microsserviços conversando entre si.
 - Manutenção e versionamento de serviços.


Arquiteturas resumo:

Arquitetura baseada em camadas: A estrutura da empresa impacta em como será desenvolvido o software.
- Separação de responsabilidade.
- Camadas devem falar apenas com suas camadas adjacentes.
- Tendência de se tornar difícil de manutenção.

Arquitetura pipeline:
- Cadeia de programas que entregam resultados um para o outro.
- MapReduce:
  - Map: Passa por todos os elementos e modifica-os
  - Reduce: Reduz todos os elementos

- STDIN & STDOUT
- Produtores: um programa gera o resultado para o inicio de outro programa
- Filtros : como o grep, procura um arquivo
- Transformadores : Recebe em um formato e altera a entrada, como um map.
- Consumidor : Recebe por final e armazena a informação no banco etc.

-  Exemplos reais: serviços de streaming, kafka.
-  NODE RED -> Scratch bolado

Arquitetura microkernel:

Sistema central com vários componentes que ele pode se conectar na execução (IDE e seus plugins, browser, libs java internas ou externas, é microkernel)
Depender de abstrações ao invés de implementação.
Contratos, 

# 28/05
Arquitetura em serviços

Sistema com aplicações focadas no domínio do problema, podendo ser reutilizado em outros pontos, é agrupada pelo domínio ao invés da lógica técnica.
Essas aplicações que resolvem o problema do domínio, são interligadas por meio de um hub, (barramento de serviços) onde é agrupado o caminho para os serviços.
Então não existe comunicação entre domínios na aplicação, cada serviço se comunica com o barramento específico de outro domínio e esse barramento é quem conhece cada serviço do domínio
e chama baseado na necessidade. Comportamento similar a um API Gateway, se não for igual.
Facilita troca de serviços pelo baixo acoplamento entre serviços, manutenção.
Ponto ruim: gera um ponto único de falha, se esse gateway falhar, não existe comunicação entre os serviços dos domínios.


# 29/05
Arquitetura de microsserviços

Sistema com mini aplicações com contexto delimitado.
Cada aplicação deve ter seu domínio e realizar ações apenas condizentes com o domínio definido pelo desenvolvedor.
Essa arquitetura favorece o desacoplamento de lógica de negócio e ajuda nos conceitos de composição e herança para desenvolvimento, visto que tudo dentro do contexto
condiz com uma regra básica de ser algo conhecido.

Microsserviços seguem o principio de isolamento de dados, é necessário atenção ao utilizar visto que deve-se ter uma alta coordenação entre equipes para garantir a integridade dos dados.
A comunicação entre microsserviços garante a interoperabilidade entre os serviços;

Características da arquitetura: escalabilidade e elasticidade.
