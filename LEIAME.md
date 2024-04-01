# Spring Security - Java JSON Web Token (JJWT)
Projeto em Java com Spring e Gradle para autenticação usando Java JSON Web Token (JJWT).


## Passos
Os passos da implementação do projeto:

1. Criar projeto (no IntelliJ) com:
- Linguagem Java (17);
- Spring Framework (6.2.3);
- Dependências: Web, Security, DevTools, JPA, H2, Lombok, Actuator, Validation.

![Image-01-IntelliJ](images/Img-01-IntelliJ.png)

2. Adicionar dependência java-jwt do Auth0 obtida no 
[Maven Repository](https://mvnrepository.com/artifact/com.auth0/java-jwt/4.4.0)
para o arquivo `build.gradle` (ou `pom.xml`):

```groovy
implementation group: 'com.auth0', name: 'java-jwt', version: '4.4.0'
```
ou
```groovy
implementation 'com.auth0:java-jwt:4.4.0'
```
ou
```xml
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>4.4.0</version>
</dependency>
```


## Referências
Maven Repository - Auth0 - Java JWT:
https://mvnrepository.com/artifact/com.auth0/java-jwt/4.4.0

Fernanda Kipper | Dev - PROJETO FULLSTACK COM LOGIN USANDO SPRING SECURITY + JWT | BACKEND:
https://www.youtube.com/watch?v=tJCyNV1G0P4

Fernanda Kipper | Dev - Autenticação e Autorização com Spring Security, JWT Tokens e Roles:
https://www.youtube.com/watch?v=5w-YCcOjPD0

GitBook - Auth Database - Gleyson Sampaio: 
https://glysns.gitbook.io/spring-framework/spring-security/auth-database

GitBook - JWT - JSON Web Token - Gleyson Sampaio: 
https://glysns.gitbook.io/spring-framework/spring-security/spring-security-e-jwt