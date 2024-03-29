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

## Instância de Domínio do Projeto

![web services springboot - 01](https://user-images.githubusercontent.com/17755195/194159496-389280d4-a430-439b-9891-1551a4408862.png)

## Camadas Lógicas do Projeto

![web services springboot - 02](https://user-images.githubusercontent.com/17755195/194160003-a00426c7-8b78-4061-91e7-784ba16e02e2.png)

## Projeto Funcionando no Heroku

* https://workshopcourse-java-springboot.herokuapp.com/
* https://workshopcourse-java-springboot.herokuapp.com/products
* https://workshopcourse-java-springboot.herokuapp.com/orders
* https://workshopcourse-java-springboot.herokuapp.com/users
* https://workshopcourse-java-springboot.herokuapp.com/categories

## Visualizando o Projeto com o Postman

# Por que devo trabalhar com a API Client Postman?

A API Client Postman é uma ferramenta de desenvolvimento de software usada para testar e documentar APIs. Aprender a trabalhar com a Postman pode trazer vários benefícios, como:

* Testes automatizados: A Postman permite que você crie testes automatizados para suas APIs, o que pode economizar tempo e esforço e reduzir erros manuais. Você pode criar testes para cada chamada de API, validar a resposta e até mesmo automatizar fluxos de trabalho complexos.

* Documentação de API: A Postman permite que você crie documentação clara e precisa para suas APIs. Isso pode ajudar outros desenvolvedores a entender como usar sua API e quais são os recursos disponíveis.

* Ambiente de desenvolvimento integrado: A Postman oferece um ambiente de desenvolvimento integrado (IDE) para trabalhar com APIs. Isso significa que você pode gerenciar todas as suas chamadas de API e testes em um único lugar, sem precisar alternar entre diferentes ferramentas.

* Colaboração: A Postman permite que você compartilhe suas chamadas de API, coleções e testes com outros membros da equipe, o que pode facilitar a colaboração e a comunicação.

* Suporte multiplataforma: A Postman é uma ferramenta multiplataforma, o que significa que você pode usá-la em diferentes sistemas operacionais e dispositivos.

* Comunidade: A Postman tem uma grande comunidade de usuários e desenvolvedores, o que significa que você pode encontrar muitos recursos, tutoriais e exemplos online para ajudá-lo a aprender e usar a ferramenta.

Em resumo, aprender a trabalhar com a API Client Postman pode ajudá-lo a testar, documentar e colaborar no desenvolvimento de suas APIs. Além disso, a ferramenta oferece recursos de automação, ambiente integrado, suporte multiplataforma e comunidade ativa para ajudá-lo a se tornar um desenvolvedor mais habilidoso.

![api-gestao-de-vendas-postman](https://user-images.githubusercontent.com/17755195/196309582-3def9c85-3b5d-4672-aacc-2f949e398004.png)

Portanto, o Postman é um API Client que facilita aos desenvolvedores criar, compartilhar, testar e documentar APIs. Isso é feito, permitindo aos usuários criar e salvar solicitações HTTP e HTTPs simples e complexas, bem como ler suas respostas. Mas como testar sua API? 
Configure o ambiente dos testes: 

* configure o ambiente com base nos requisitos da API. 
* Configure o banco de dados e o servidor de acordo com os requisitos da aplicação. 
* Em seguida, faça uma chamada de API para garantir que tudo esteja configurado e que nada seja interrompido antes de começar os testes.

* https://documenter.getpostman.com/view/20651332/2s83zdvmJ6

## Visualizando o Projeto com o Swagger UI

![api-gestao-de-vendas-swagger](https://user-images.githubusercontent.com/17755195/196309324-cc95288b-5b3a-4fe2-aeea-062296eb0749.png)

O Swagger é um framework composto por diversas ferramentas que, independente da linguagem, auxilia a descrição, consumo e visualização de serviços de uma API REST. Em suma, o Swagger visa padronizar este tipo de integração, descrevendo os recursos que uma API deve possuir, como endpoints, dados recebidos, dados retornados, códigos HTTP e métodos de autenticação, entre outros. 

* https://workshopcourse-java-springboot.herokuapp.com/swagger-ui.html#/

## Visualizando o Projeto com o JavaDoc

![api-gestao-de-vendas-javadoc](https://user-images.githubusercontent.com/17755195/196307213-3c77f5e0-5153-457f-b796-f3b2acbca0bf.png)

Javadoc é um gerador de documentação criado pela Sun Microsystems para documentar a API dos programas em Java, a partir do código-fonte. O resultado é expresso em HTML. É constituído, basicamente, por algumas marcações muitos simples inseridas nos comentários do programa. Portanto, é uma ferramenta para a criação de documentação de pacotes, classes, atributos e métodos Java a partir do processamento do código fonte com comentários em formato adequado onde é possível navegar de um documento para outro de forma a ajudar em compreender a relação entre as classes e métodos.

* https://adriano1976.github.io/gestao-de-venda-javadoc/

## Autor do Projeto

Adriano Santos

* https://www.linkedin.com/in/adrianosantos-dev/
