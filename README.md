# Desafio Técnico Sicredi

<br/>

## ✅ Concluído

<br/>

## Conteúdos

* [Objetivo](#objetivo)
* [Como proceder](#como-proceder)
* [O que será analisado](#o-que-será-analisado)
* [Dicas](#dicas)
* [Pré-requisitos](#pré-requisitos)
* [Como Executar o Projeto](#como-executar-o-projeto)
* [Endpoints](#endpoints)
    * [Criar Associado](#criar-associado)
    * [Criar Pauta](#criar-pauta)
    * [Criar Sessão](#criar-sessão)
    * [Registrar Voto](#registrar-voto)
    * [Contar Votos](#contar-votos)
* [Informações Adicionais](#informações-adicionais)
* [Tecnologias](#tecnologias)

<br/>
<br/>

## Objetivo

<br/>

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
  um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
  é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java, usando Spring-boot, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

O foco dessa avaliação é a comunicação entre o backend e o aplicativo mobile. Essa comunicação é feita através de mensagens no formato JSON, onde essas mensagens serão interpretadas pelo cliente para montar as telas onde o usuário vai interagir com o sistema. A aplicação cliente não faz parte da avaliação, apenas os componentes do servidor. O
formato padrão dessas mensagens será detalhado no anexo 1.

## Como proceder

Por favor, **CLONE** o repositório e implemente sua solução, ao final, notifique a conclusão e envie o link do seu repositório clonado no GitHub, para que possamos analisar o código implementado.

Lembre de deixar todas as orientações necessárias para executar o seu código.

### Tarefas bônus

- Tarefa Bônus 1 - Integração com sistemas externos
    - Criar uma Facade/Client Fake que retorna aleátoriamente se um CPF recebido é válido ou não.
    - Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos
    - Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação. Essa operação retorna resultados aleatórios, portanto um mesmo CPF pode funcionar em um teste e não funcionar no outro.

```
// CPF Ok para votar
{
    "status": "ABLE_TO_VOTE
}
// CPF Nao Ok para votar - retornar 404 no client tb
{
    "status": "UNABLE_TO_VOTE
}
```

Exemplos de retorno do serviço

### Tarefa Bônus 2 - Performance

- Imagine que sua aplicação possa ser usada em cenários que existam centenas de
  milhares de votos. Ela deve se comportar de maneira performática nesses
  cenários
- Testes de performance são uma boa maneira de garantir e observar como sua
  aplicação se comporta

### Tarefa Bônus 3 - Versionamento da API

○ Como você versionaria a API da sua aplicação? Que estratégia usar?

## O que será analisado

- Simplicidade no design da solução (evitar over engineering)
- Organização do código
- Arquitetura do projeto
- Boas práticas de programação (manutenibilidade, legibilidade etc)
- Possíveis bugs
- Tratamento de erros e exceções
- Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
- Uso de testes automatizados e ferramentas de qualidade
- Limpeza do código
- Documentação do código e da API
- Logs da aplicação
- Mensagens e organização dos commits

## Dicas

- Teste bem sua solução, evite bugs
- Deixe o domínio das URLs de callback passiveis de alteração via configuração, para facilitar
  o teste tanto no emulador, quanto em dispositivos fisicos.
  Observações importantes
- Não inicie o teste sem sanar todas as dúvidas
- Iremos executar a aplicação para testá-la, cuide com qualquer dependência externa e
  deixe claro caso haja instruções especiais para execução do mesmo
  Classificação da informação: Uso Interno

## Anexo 1

### Introdução

A seguir serão detalhados os tipos de tela que o cliente mobile suporta, assim como os tipos de campos disponíveis para a interação do usuário.

### Tipo de tela – FORMULARIO

A tela do tipo FORMULARIO exibe uma coleção de campos (itens) e possui um ou dois botões de ação na parte inferior.

O aplicativo envia uma requisição POST para a url informada e com o body definido pelo objeto dentro de cada botão quando o mesmo é acionado. Nos casos onde temos campos de entrada
de dados na tela, os valores informados pelo usuário são adicionados ao corpo da requisição. Abaixo o exemplo da requisição que o aplicativo vai fazer quando o botão “Ação 1” for acionado:

```
POST http://seudominio.com/ACAO1
{
    “campo1”: “valor1”,
    “campo2”: 123,
    “idCampoTexto”: “Texto”,
    “idCampoNumerico: 999
    “idCampoData”: “01/01/2000”
}
```

Obs: o formato da url acima é meramente ilustrativo e não define qualquer padrão de formato.

### Tipo de tela – SELECAO

A tela do tipo SELECAO exibe uma lista de opções para que o usuário.

O aplicativo envia uma requisição POST para a url informada e com o body definido pelo objeto dentro de cada item da lista de seleção, quando o mesmo é acionado, semelhando ao funcionamento dos botões da tela FORMULARIO.

# desafio-votacao

<br/>

## Pré-requisitos

- Mysql 8
- JDK 17
- Postman
- Docker
- Docker Compose
- Intellij  (ou outra IDE de sua preferência)

<br/>

## Como Executar o Projeto

Com o ``Docker`` em execução, abra o terminal na raiz do projeto e execute:

```bash
docker-compose up -d
````

Este comando irá subir todos os containers definidos no arquivo docker-compose.yml. O processo pode levar alguns minutos.

<br/>

## Endpoints

<br/>

### Criar Associado

Requisição:

```sh
curl --location --request POST 'http://localhost:8100/associates' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Augusto Renato Aparício",
    "document": "278.065.817-74"
}'
```

Resposta:

```json
{
  "id": "873a17d8-5ff0-4723-a1ee-740ce6c21d27",
  "name": "Augusto Renato Aparício",
  "document": "27806581774",
  "createdAt": "14/04/2025 23:35:24"
}
```

<br/>

### Criar Pauta

Requisição:

```sh
curl --location --request POST 'http://localhost:8100/topics' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Lorem ipsum dolor sit amet.",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
}'
```

Resposta:

```json
{
  "id": "61fda446-146c-466b-a2c8-998dfabb3eab",
  "title": "Lorem ipsum dolor sit amet.",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "createdAt": "14/04/2025 23:34:55"
}
```

<br/>

### Criar Sessão

- ``idTopic``: ID da pauta obtido no endpoint anterior.

```sh
curl --location --request POST 'http://localhost:8100/sessions' \
--header 'Content-Type: application/json' \
--data '{
    "idTopic": "61fda446-146c-466b-a2c8-998dfabb3eab"
}'
```

Resposta:

```json
{
  "id": "e0275b22-c3e2-4931-a91b-73224af3a0f0",
  "start": "14/04/2025 23:35:06",
  "end": "14/04/2025 23:37:06",
  "topic": {
    "id": "61fda446-146c-466b-a2c8-998dfabb3eab",
    "title": "Lorem ipsum dolor sit amet.",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  }
}
```

<br/>

### Registrar Voto

- ``idAssociated``: ID do associado que você obtém no endpoint ``Criar Associado``
- ``idSession``: ID da sessão que você obtém no endpoint ``Criar Sessão``
- ``vote``: É um ``Enum`` e aceita apenas os valores ``YES`` ou ``NO``

```sh
curl --location --request POST 'http://localhost:8100/votings' \
--header 'Content-Type: application/json' \
--data '{
    "idAssociated": "7c5c738e-17bb-11f0-9044-581122c7752d",
    "idSession": "e0275b22-c3e2-4931-a91b-73224af3a0f0",
    "vote": "YES"
}'
```

<br/>

### Contar Votos

```sh
curl --location --request GET 'http://localhost:8100/votings/session/e31d6624-1b46-4afa-9fc9-bc4076b37e4d/count'
```

Resposta:

```json
{
  "Sim": 8,
  "Não": 2
}
```

- A contagem dos votos estará disponível apenas após o encerramento da sessão.

<br/>

## Informações Adicionais

> O projeto utiliza ``Flyway`` para controle de ``migrations``, garantindo a criação automática das tabelas e o cadastro inicial de dados, como associados.
>
> A aplicação está ``dockerizada``, facilitando o deploy e a configuração do ambiente.
>
> O tempo de expiração das sessões de votação é controlado pela variável de ambiente ``VOTING.SESSION.EXPIRATIONTIME``, definida no ``docker-compose.yml``. O valor deve ser informado em ``minutos``.
>
> A API está documentada utilizando ``springdoc-openapi``, e pode ser acessada via interface Swagger no seguinte endereço:
>
> 👉 [`http://localhost:8100/swagger-ui/index.html`](http://localhost:8100/swagger-ui/index.html)

<br/>

## Tecnologias

![Java](https://img.shields.io/static/v1?label=Java&message=17&color=green)
![Spring Boot](https://img.shields.io/static/v1?label=spring-boot&message=3.4.4&color=green)
![MySql](https://img.shields.io/static/v1?label=mysql&message=9.1.0&color=green)
![FlywayDB](https://img.shields.io/static/v1?label=flyway-db&message=10.20.1&color=green)
![SpringDoc](https://img.shields.io/static/v1?label=springdoc-openapi&message=2.8.6&color=green)
![Lombok](https://img.shields.io/static/v1?label=Lombok&message=1.18.38&color=green)
