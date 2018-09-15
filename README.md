# Teste Java Adsim

Solução desenvolvida em Java com a ferramenta Spring Boot.
Servidor Apache Tomcat que já está embutido no Spring Boot.
Persistência implementada utilizando H2, uma *in-memory database* embutida na ferramenta, pelo fato do Spring Boot possibilitar rodar a aplicação sem instalar anteriormente um banco de dados de terceiro.

## O que é necessário para executar o código

* É necessária uma variável de ambiente de sistema chamada `JAVA_HOME` e com o valor o caminho da JDK (Não JRE), exemplo 'C:\Program Files\Java\jdk1.8.0_101'.

* Para rodar a aplicação é necessário ter o Apache Maven instalado no computador, segue abaixo como instalá-lo:
-- Clone o repositório ou faça o download em zip e extraia para a pasta que desejar
-- Abra uma janela de comando e dê `cd` até a pasta ‘..\talento-java\TalentoJava\’
-- Digite `mvnw clean install` e dê enter, isso irá fazer com que o maven seja instalado e todas as dependências necessárias sejam instaladas e fará a construção do arquivo executável do programa

## Como executar a aplicação

* Clone o repositório ou faça o download em zip e extraia para a pasta que desejar

* Se você ainda não tiver feito os comandos do maven mostrados a cima:
    * Abra uma janela de comando e dê `cd` até a pasta ‘..\talento-java\TalentoJava\’
    * Digite `mvnw clean install` e dê enter, isso irá fazer com que o maven seja instalado e todas as dependências necessárias sejam instaladas e fará a construção do arquivo executável do programa

* Abra uma janela de comando e dê `cd` até a pasta ‘..\talento-java\TalentoJava\target\’

* Digite `java -jar TalentoJava-1.0.0.jar` e dê enter, isso irá fazer com que o servidor tomcat embutido no Spring Boot seja inicializado e rode o programa (Uma outra maneira de rodar a aplicação é utilizar o comando `mvn spring-boot:run` no lugar do `mvn clean install`, isso fará a aplicação rodar direto sem precisar do comando do java)

* Abra o navegador de sua escolha e vá a url `localhost:8080/`

* Pronto, adicione os livros vendidos e então, no final do dia, faça o envio deles ao fornecedor.

* Você pode navegar pelo sistema através da barra de navegação no topo da página.