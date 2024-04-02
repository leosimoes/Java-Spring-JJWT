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

3. Add datasource, jpa and h2 settings in `application.properties`:

```properties
# ===================================================================
#                   APPLICATION
# ===================================================================
spring.application.name=Java-Spring-JJWT
# ===================================================================
#                   DATASOURCE AND H2 DATABASE
# ===================================================================
# H2 - Datasource
spring.datasource.url=jdbc:h2:mem:jjwtapp
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
# H2 - Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2
# Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.ddl-auto=update
# http://localhost:8080/h2/
```

4. Add Enum `Role` which can be `ROLE_USER` or `ROLE_ADMIN`:

![Img-02-Role](images/Img-02-Role.png)

5. Add Class `JJWTUser`:
- annotated with @Entity, @Table(name="jjwt_users"), @Data, @NoArgsConstructor, @AllArgsConstructor;
- with attributes id, name, login, password, roles.

![Img-03-JJWTUser](images/Img-03-JJWTUser.png)

6. Add `JJWTUserRepository` Interface:
- annotated with `@Repository`;
- extends `JpaRepository<JJWTUser, String>`;
- has the method `Optional<JJWTUser> findByLogin(String login)`;

![Img-04-JJWTRepository](images/Img-04-JJWTRepository.png)

7. Add a value for `api.security.token.secret` in `application.properties`:

```properties
# ===================================================================
#                   SECURITY
# ===================================================================
api.security.token.secret=chaveSecreta
```

8. Add `TokenService` Class:
- in the `security` package;
- with the attributes `secret`, `ISSUER`, `EXPIRATION_HOURS` and `ZONE_OFFSET`;
- with the private methods `Instant calculateExpiration()` and `Algorithm getAlgorithm()`;
- with public methods `String generateToken(JJWTUser jjwtUser)` and `String validateToken(String token)`.

![Img-05-JJWTService](images/Img-05-JJWTService.png)


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