#Passos do Desenvolvimento


-Criação do modelo de entidade e relacionamento (DER)
-Criação da Aplicação REST (Spring Boot - Java 11)
-Criação das Entidades de Modelo
-Criação dos DTO’s
-Criação dos Repositórios com JPA
-Criação dos Serviços
-Criação do Controller
-definição das rotas
  -Rotas GET: 
  -Rotas POST:
  -Rotas PUT:
  -Rotas Delete:
-Teste Unitários com JUNIT
-Criação da Aplicação Angular WEB




Criação do modelo de entidade e relacionamento (DER)

Uma entidade que possui um identificador id do tipo inteiro, um nome do tipo char com tamanho máximo de 50 caracteres, um atributo cpf do tipo char com tamanho máximo de 20 caracteres, e um campo telefone do tipo char com no máximo 20 caracteres, como representado na figura 1. Representado de acordo com o desafio solicitado.  


Figura 1 - representação da entidade e relacionamento.

Criação da Aplicação REST (Spring Boot - Java 11)
	
A aplicação é desenvolvida na arquitetura REST na especificação Java 11, utilizando o banco em memória o h2. Para documentar as rotas será utilizado o Swagger através da rota do link http://localhost:8081/swagger-ui/#/ e o banco atraves do link http://localhost:8081/h2-console/.

Criação das Entidades de Modelo


![alt text](https://i.ibb.co/93ph4yG/diagrama-DER-drawio.png)


Cliente: id, nome, cpf, telefone
Criação dos DTOs
CriaCliente : nome, cpf , telefone
ConsultaCliente: id, nome, cpf , telefone
AtualizaCliente: id, nome, cpf , telefone
FiltraCliente: nome, cpf e telefone, pagina, tamanho
Criação dos Repositórios com JPA
ClienteRepository:
métodos de consulta:
consultar um Cliente por id
consultar Cliente por cpf
consultar Cliente por nome
Criação dos Serviços
A criação de serviços é feita de acordo com o padrão MVC.
ClienteService
Consultar Cliente por id
Consultar Cliente por cpf
Consultar Cliente por filtro
Salvar cliente
Atualizar cliente
Criação do Controller 
A criação de controller é feita de acordo com o padrão MVC, sendo versionadas.
definição das rotas
Rotas GET:
Rota responsável por filtrar um cliente por nome, ou cpf ou telefone. Recebe como entrada o Dto FiltraCliente e retorna uma página de clientes. Caso não encontre retorna uma página vazia. Com o http status 200 (OK)
rota:  /cliente/v1/filtra?nome=&cpf=&telefone=  
Rotas responsável por consultar um cliente por id recebe como entrada um único inteiro e retorna um único cliente. Caso não encontre retorna uma exceção com o http status 404 (NotFound).
 rota: /cliente/v1/{id} 
Rotas responsável por consultar um cliente por cpf, recebe como entrada uma string e retorna um único cliente. Caso não encontre retorna uma exceção com o http status 404 (NotFound).
rota: /cliente/v1/cpf/{cpf}
Rotas POST:
rota responsável por salvar um novo cliente. Recebe como entrada um dto CriaCliente retorna o http status 201 (CREATED)
rota: /cliente/v1/salvar
Rotas PUT:
rota responsável por atualizar um cliente recebe como entrada um dto AtualizaCliente, e retorna um httpstatus 200 (OK)
rota: /cliente/v1/atualizar

Teste Unitários com JUNIT
		Os testes unitários foram desenvolvidos utilizando JUNIT 5 e Mockito. 
Criação da Angular WEB
