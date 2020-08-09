# Navita API Patrimônio

### Introdução

API rest Java, utilizando spring boot, com autenticação utilizando JWT, base de dados postgresql, para auxiliar a gerência de patrimônios da empresa.

### Descrição

Para o desenvolvimento desse projeto foi utilizados Spring Boot, Spring Security, Autenticação utilizando JWT, JPA e Hibernate

### Autenticação

#### Dentro do projeto na pasta /desafio-api-rest-nav/src/main/resources/templates/db/scripts, está adicionado o script de criação da estrutura de tabelas.

#### Para a autenticação JWT tem 2 usuários default que adicionei para agilizar o fluxo do desenvolvimento, sendo eles: userDesafio e adminDesafio, ambos com a senha padrão "password", o token JWT está sendo retornado no header da requisição /login na chave "Authorization" no value "Bearer".

#### Conforme solicitado a requisição POST /usuarios não tem bloqueio de envio, assim como a requisição POST /login (passando o usuário e senha), as demais requisição precisam enviar no header o parâmetro "Authorization" juntamente com o valor no Bearer retornado da requisição ao endpoint /login.

#### Para visualizar os campos, bem como os endpoints disponíveis, basta acessar o swagger (/v1/swagger-ui.html).

#### As configuração da aplicação estão no arquivo application.properties na pasta /desafio-api-rest-nav/src/main/resources.

#### Durante o desenvolvimento foi utilizada uma base de dados no postegres chamada "desafio".

#### Criados testes de unidade para as classes de serviço, utilizando o Mockito para o mock dos acessos a base.