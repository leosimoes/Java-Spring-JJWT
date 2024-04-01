# Spring Security - Java JSON Web Token (JJWT)
Java project with Spring and Gradle for authentication using Java JSON Web Token (JJWT).


## Steps
The steps of project implementation:

1. Create project (in IntelliJ) with:
- Java language (17);
- Spring Framework (6.2.3);
- Dependencies: Web, Security, DevTools, JPA, H2, Lombok, Actuator, Validation.

![Image-01-IntelliJ](images/Img-01-IntelliJ.png)

2. Add Auth0 java-jwt dependency obtained from
   [Maven Repository](https://mvnrepository.com/artifact/com.auth0/java-jwt/4.4.0)
   for the `build.gradle` (or `pom.xml`) file:

```groovy
implementation group: 'com.auth0', name: 'java-jwt', version: '4.4.0'
```
or
```groovy
implementation 'com.auth0:java-jwt:4.4.0'
```
or
```xml
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>4.4.0</version>
</dependency>
```


## References
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