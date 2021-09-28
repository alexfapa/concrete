# Desafio para vaga de Java - Concrete

## Pacotes existestes:
 1. config: contem as classes de configuração necessárias
 2. controllers: contem classes com os endpoints necessários
 3. exceptions: contém as classes de implementação das excptions
 4. dto: contém as classes de manipulação de objatos externos
 5. entities: contém as classes de entidade
 6. repositories: contém as interfaces de manipulação de banco de dados
 7. security: contém as classes que trazem as configurações de segurança
 8. services: contém as classes de serviços do sistema
 9. validation: contém as classes de validação
 
 ## Cadastro:
 * Endpoint: /usuarios/cadastro
 * Requisição: POST
 -- Realiza o cadastro de usuário recebendo como parametros email, senha, telefone(s) se houver.
 Exemplo de json para cadastro:
>
>```json
>{   
>      "name": "Teste",
>      "email": "teste3@teste.com",
>      "password":"123",
>      "phones": [
>          {
>              "ddd": "88",
>              "number": "992222223"
>          },
>          {
>              "ddd": "88",
>              "number": "993333323"
>          }
>       ]
>}
>```

## Login:
* Endpoint: /login
* Requisição: POST
-- Realiza o login do usuário e em caso de sucesso retorna um header com um token de acesso que expira em 30 minutos bem como o campo 'Location' com o link para acessar o usuário que realizou o login.

Exemplo de json para login:
>
>```json
>{
>    "email" : "teste@teste.com",
>    "senha" : "123"
>}
>```
 
 ## Acesso:
 * Endponit: /usuarios/{id} ou /usuarios
 * Requisição: GET
 -- Realiza a consulta de um determinado usuário de acordo com o id indicado ou em caso de não enviar id retorna todos os usuários.
 
 ** Observações: 
  1. O acesso aos usuários somente pode ser realizado mediante inserção de token de acesso gerado ao efetuar login
  2. O token de acesso deve ser inserido no cabeçalho com um header, onde a Key deve ser "Authorization" e o value deve ser "Bearer + accessToken"
  3. O token é persistido no banco de dados.
  
 Exemplo de retorno em caso de sucesso:
 >
>```json
>{
>        "id": 1,
>        "name": "Teste",
>        "email": "teste@teste.com",
>        "phones": [
>            {
>                "ddd": "88",
>                "number": "992222222"
>            },
>            {
>                "ddd": "88",
>                "number": "993333333"
>            }
>        ],
>        "created": "2021-09-28T00:16:55.000+00:00",
>        "modified": "2021-09-28T00:17:21.000+00:00",
>        "last_login": "2021-09-28T00:17:21.000+00:00",
>        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJleHAiOjE2MzI3OTAwNDF9.j6NQWpYOeNJtSs9tSJrCDnlxs-4lVKU4YzAaL2sEu5EB5W0iglweDNEHMFPDJQzJkrJEVoDjD22ImwknFz3Vvg"
>    }
>```
 
 
 
