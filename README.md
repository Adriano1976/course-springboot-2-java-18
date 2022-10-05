# Projeto web services com Spring Boot e JPA / Hibernate

Esse projeto foi elaborado e seguindo as instruções do Professor Nelio Alves em seu curso de java. Ele consiste em uma api que tem como objetivo fazer o controle das vendas e identificar a relação dos produtos com o cliente em si, como também organizar esses dados de acordo com o nome do produto, o tipo de categoria como também a ordem pelo qual foi comprado pelo consumidor.

Durante a sua construção, pois possível criar as principais funções básicas de uma api, sendo elas a de inserção de dados, visualização de uma lista completa de informações dos dados como também a visualização de um determinada informação de um único item identificado por um código ou id informado pelo usuário. Também foi criado a função para alterar uma informação específica de dados de acordo com o código do conjunto de dados informado pelo usuário, se o mesmo existir na base de dados. Por fim, foi também criado a função de deletar uma linha de informações de acordo com a instrução passada pelo usuário através de um código de identificação registrado na base de dados caso ela venha a existir.

Para realizar os testes com a api, basta fazer uma clonagem do projeto em sua máquina e exportar para a sua IDE de preferência para que a mesma faça a administração das dependências do seu projeto para depois fazer a execução da mesma. Quando estiver funcionando, basta usar o Postman para realizar os testes de inserção, visualização, alteração e remoção de dados. Caso não tenha o Postman, basta acessar https://www.postman.com/downloads/ para baixa-lo em seu computador.

Para concluir, nesse projeto tive que fazer algumas adaptações durante a sua construção devido as atualizações das dependências e plug-ins, como também as diferenças existente na IDE Intellij. Mas mesmo assim, conseguir fazer com que a aplicações funcionasse da mesmo forma aplicando os conhecimentos adquiridos durante os curso da Universidade, Udemy e da DIO. Portanto, gostaria de agradescer por encontrar pessoas tão capacitadas que usam seus conhecimentos e experiência para compartilhar com aqueles que desejam aprender e entrar nesse mercado tão amplo e democrático.

## Objetivos

* Criar projeto Spring Boot Java.
* Implementar modelo de domínio.
* Estruturar camadas lógicas: resource, service, repository.
* Configurar banco de dados de teste (H2).
* Povoar o banco de dados.
* CRUD - Create, Retrieve, Update, Delete.
* Tratamento de exceções.

## Pré-requisitos

Lógica de programação (qualquer linguagem).
Programação orientada a objetos (qualquer linguagem).

## Ferramentas

* Spring Boot
* Apache Tomcat
* Maven
* Hibernate
* JPA Mapping
* H2 Database
* PostgreSQL
* Postman
* Heroku

## Modelo Conceitual do Projeto

Nesse projeto está sendo construido um pequeno sistema (API REST) de um departamento de vendas, com os seguintes casos de uso:

Buscar todos usuários, produtos, categorias e ordens de compra.
Buscar um usuário, produto, categoria e ordem de compra pelo seu id.
Inserir um novo usuário, produto, categoria...

![projeto - model 1](https://user-images.githubusercontent.com/17755195/191788487-d898cc8b-2d7f-4991-a87c-7e8fd9ce86d1.png)

## Projeto Funcionando no Heroku

* https://workshopcourse-java-springboot.herokuapp.com/
* https://workshopcourse-java-springboot.herokuapp.com/products
* https://workshopcourse-java-springboot.herokuapp.com/orders
* https://workshopcourse-java-springboot.herokuapp.com/users
* https://workshopcourse-java-springboot.herokuapp.com/categories

## Visualizando o Projeto com o Postman

* https://documenter.getpostman.com/view/20651332/2s83zdvmJ6
