# desafio-sicredi
Desafio Sicredi

Foi criado o repositório "desfio-sicredi" no GitHub.
Neste repositório há duas pastas.
A pasta "cooporativismo" que contém os fontes da API.
A pasta "db" que contém o arquivo de banco de dados.

O arquivo de banco dados ficará no diretório /db

-------------------------------------------------------------------------------------------------------------------

Detalhamento do projeto

Recursos da API


Recurso: Criar novo Associado
REQUEST 
POST http://localhost:8080/api/v1/associado
HEADER 
Content-Type: application/json
BODY:
{
    "cpf":"11111111111",
    "nome":"Fulano"
}
RESPONSE
BODY:
{
    "id":"1",
    "cpf":"11111111111",
    "nome":"Fulano"
}

Recurso: Localizar um Associado pelo Id
REQUEST
GET http://localhost:8080/api/v1/associado/{id}
Exemplo GET http://localhost:8080/api/v1/associado/1
RESPONSE
BODY:
{
    "id":"1",
    "cpf":"11111111111",
    "nome":"Fulano"
}

Recurso: Localizar um Associado pelo CPF
REQUEST
GET http://localhost:8080/api/v1/associado//cpf/{cpf}
Exemplo GET http://localhost:8080/api/v1/associado/cpf/11111111111
RESPONSE
BODY:
{
    "id":"1",
    "cpf":"11111111111",
    "nome":"Fulano"
}

Recurso: Criar nova Pauta
REQUEST 
POST http://localhost:8080/api/v1/pauta
HEADER 
Content-Type: application/json
BODY:
{
    "descricao":"Assunto em pauta"
}
RESPONSE
BODY:
{
    "id":"1",
    "descricao":"Assunto em pauta"
}

Recurso: Abrir sessão a partir de uma pauta
REQUEST 
PUT http://localhost:8080/api/v1/pauta/{idPauta}/abrir-sessao
HEADER 
Content-Type: application/json
RESPONSE
BODY:
{
    "status":"Sessão aberta 2022-12-16T11:39:00"
}

Recurso: Registrar votação
REQUEST 
POST http://localhost:8080/api/v1/pauta/{idPauta}/votacao
Exemplo POST http://localhost:8080/api/v1/pauta/1/votacao
HEADER 
Content-Type: application/json
BODY:
{
    "idAssociado":"1",
	"voto":"SIM"
}
RESPONSE
BODY:
{
    "mensagem":"Voto realizado com sucesso"
}

Recurso: Obter resultado da votação 
REQUEST
GET http://localhost:8080/api/v1/pauta/{idPauta}
Exemplo GET http://localhost:8080/api/v1/pauta/1
RESPONSE
BODY:
{
    "sim":"10",
    "nao":"5"
}



-------------------------------------------------------------------------------------------------------------------
Tarefa Bônus 4 - Versionamento da API

Conheço três formas de versionar APIs.

1 - Colocar a versão no path da URL(endpoint).
Exempleo: htt://dominio.com.br/api/v1/recurso

2 - Colocar a versão no Header(cabeçalho) da requisição.
Exemplo: htt://dominio.com.br/api/recurso
Versao: v1

3 - Colocar a versão como parâmetro Query String.
Exemplo: htt://dominio.com.br/api/recurso?versao=v1

Prefiro a primeira opção.
