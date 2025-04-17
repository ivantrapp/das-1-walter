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
