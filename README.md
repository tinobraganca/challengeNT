# Desafio NT

*Projeto desenvolvido para atender os requisitos do desafio*

## Notes
1. As imagens referentes a arquitetura estão na pasta docs/.
2. Vou estar anexando export do postman para realizar testes das chamadas.

## Resumo de arquitetura
O projeto se baseia em uma arquitetura de 3 microservices para conseguir se escalado horizontalmente:

Microservices:
1. Hotel Service - Responsavel pelos os serviços de direitos do hotel exemplo(cadastro de quartos, recebimento de notificacao de booking)
2. Search Service - Responsavel pelos os serviços de busca de quartos e hoteis disponiveis para booking exemplo:(busca de quarto por localizacao preço)
3. User Service - Responsavel pelos os serviços de direitos do usuario e de booking do usuario a um quarto de hotel

### Pontos altos
1. Todos os projetos contam com sua observability integradados Grafana:
- Usado Prometheus para receber os dados colotados gerar graficos e dashboards em cima de metricas da app.
- Usado o Tempo para receber tracers em Tempo real e cruzar com os demais informações 
- Usado o Loki para receber os logs da app e cruzar com os demais indicadores do Grafana
- Usado o Jaeger All in one para efetuar o tracerID de salto de microserviços.
2. Toda base de dados é criada pelo e está versionada Pelo Flyway, os scripts de versionamento da base estão no projeto Hotel Service em resources/db/
3. Os Projetos contam com RestControllerAdvicer para mapear error e responder mensagens de erros já tratadas
4. Por preferencia usei Java Records na criação dos DTO e não utilizei o Lombok, para evitar o excesso de anotações no codigo
5. Projeto está todo estruturado para gerar uma imagem de cada projeto e um docker compose para subir toda estrutura necessaria do projeto com um comando docker compose up -d --build
6. O projeto User service conta com integração com AWS SQS de mensageria para eventos de mensagens.
7. Usado PostgreSQL para já ter disponivel busca por latitude e longitude em um mapa

## Pontos de melhoria com maior disponibilidade de tempo
1. Implementa um microservice responsavel apenas para controle de wallet e pagamento do usuario deixando separado do user-service
2. Um schema de DB separado apenas para microserviço de pagamento e wallet do usuario
3. Implentar o Feign para comunicação do User Service com service responsavel para aprovar o pagamento 
4. Implementar os demais crud de todos os projetos.
5. Talvez para melhor performace implementa o Apache Kafka para eventos que seriam comuns em todos microservices
6. Em caso extremos alterar o Search service para consultar um elasticsearch ao inves do banco direto na primeira listagem de tela
7. Implementar nos demais services o consumo das mensagens do AWS SQS
8. Implementar demais casos de tests

## Stack 

- Java 21
- Spring Boot 3.2.5
- PostgreSQL 6.5 Instancia na AWS
- Gradle 8.7
- Grafana
- Prometheus
- Loki
- Tempo 
- Jaeger
- OpenTelemetry


## Rodando o projeto local

Build Docker:
1. Para que seja possível rodar localmente é necessário ter Docker instalado.
   2. Rodar o comando abaixo:

            docker compose up -d --build

Obs: Local a publicação das mensagem no SQS não está acontecendo porque credentials estão paremetrizados apenas para aws

## Rodando o projeto Cloud
    
Projeto está rodando na AWS

Jaeger: http://ec2-3-230-167-213.compute-1.amazonaws.com:16686

Grafana: http://ec2-3-230-167-213.compute-1.amazonaws.com:3000 login/senha=admin 

Hotel-service: 
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8083/swagger-ui/index.html
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8083/actuator

Search-service: 
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8081/swagger-ui/index.html
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8081/actuator

User-service: 
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8082/swagger-ui/index.html
- http://ec2-3-230-167-213.compute-1.amazonaws.com:8082/actuator

3. Clonar ou baixar o projeto na Azure

4. Com o projeto baixado importar através do IntelliJ seguindo o passo a passo abaixo:

     4.1 Ao abrir o IntelliJ clicar em "Import Project";

     4.2 Selecionar o Projeto e clicar em Ok;

     4.3 Marcar a opção "Import project from external modal", selecionar a opção "Gradle" e clicar em Next;

     4.4 Marcar a opção "Use auto-import" e clilcar em Finish;

     4.5 Após a importação as dependências serão baixadas;

     4.6 Configurar o IntelliJ:
      - Em Settings -> Build, Execution, Deployment:
      Marcar a opção "Compiler independent modules in parallel" e ajustar para 2000 o campo "Build process heap size";

    - Em Project Structure -> Project:
      Project SKD -> Apontar para o jdk instalada (1.8);
	  Project language level -> Escolher a opção " 8- Lambdas, type annotations etc.";

    - Botão "Select Run/Debug Configuration":
	  Escolher "Edit Configurations" -> Botão "+" -> Escolher a opção "Gradle":
   	  Preencher o campo "Gradle project" apontando para o caminho do projeto Extanet
	  Preencher o campo "Task" com a opção "bootRun"

### Stack

- Java 8
- Tomcat 8.5
- Docker
- Gradlew
- Docker
- Kubernetes


## Processo de Deploy

Passo a passo

 
1. Criação de uma branch feature/time/funcionalidade


```bash
git checkout main  
git pull  
git checkout -b feature/time/nome-da-funcionalidade
```
 
Para iniciar o desenvolvimento de uma nova funcionalidade, é necessário criar uma nova branch a partir da branch de padrão (main) com o nome feature/time/nome-da-funcionalidade. É importante seguir o padrão de nomenclatura para facilitar a identificação.

O workflow de segue o modelo de promoção de funcionalidades entre os ambientes de desenvolvimento, homologação e produção. Nesse modelo, não é necessário manter uma branch por ambiente (develop, stage e master/production).

Cada nova funcionalidade desenvolvida é promovida entre os ambientes através de uma TAG que garante sua imutabilidade e referência. O fluxo de promoção de funcionalidades na pipeline é o seguinte:

- Desenvolvimento e/ou Homologação: A TAG (e.g `1.5.1`) será buildada e implantada no ambiente de desenvolvimento (NonProd) e terá um tempo para ser validada pela equipe (e.g 5 dias). Após esse período o time pode optar por promover o "state"/tag para o próximo ambiente  
- Produção: Após o ambiente NonProd, a funcionalidade é promovida para produção, sendo necessário a aprovação de um participante do grupo "Servicos Colaborar" que, por sua vez, é composto por alguns TL's e TM's.

Com esse modelo de promoção de funcionalidades, é possível garantir a estabilidade e a qualidade do código fonte em todos os ambientes, além de permitir a rastreabilidade das alterações feitas em cada versão da aplicação.

 
2. Criação de um Pull Request

 
Após o desenvolvimento da funcionalidade, é necessário criar um Pull Request para que o Tech Lead e pelo menos um desenvolvedor possam revisar e aprovar as alterações. O Pull Request deve conter informações claras sobre a funcionalidade desenvolvida e quaisquer outras informações relevantes.

3. Geração de uma documentação no padrão SemVer

 
Antes de concluir a funcionalidade, é necessário gerar uma documentação (`changelog` e `commit`) no padrão SemVer. O Semantic Versioning (SemVer) é uma convenção de versionamento usada para garantir a compatibilidade entre diferentes versões de um software. O formato da numeração de versão é MAJOR.MINOR.PATCH, onde:

- MAJOR: quando há alterações incompatíveis com versões anteriores;
- MINOR: quando há adição de novas funcionalidades compatíveis com versões anteriores;
- PATCH: quando há correção de bugs compatíveis com versões anteriores.

4. Conclusão e deploy

 
Após a aprovação do Pull Request, geração da documentação e TAG no padrão SemVer, a pipeline de CI/CD irá fazer o build e deploy no Kubernetes.

```
⚠️WARNING
Atualmente o projeto do EXTRANET precisa que a pipeline seja startada de forma manual para que o o executor possa escolher o time/banco de dados a ser buildado
```

## Conclusão

Este processo de deploy permite que a equipe de desenvolvimento mantenha um controle rigoroso sobre as alterações feitas no código fonte e garanta que as novas funcionalidades sejam adicionadas de forma segura e confiável.