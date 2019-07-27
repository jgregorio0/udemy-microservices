# Spring Boot Microservices and Spring Cloud
> Ref: [https://www.udemy.com/spring-boot-microservices-and-spring-cloud/learn/lecture/13622796#overview](https://www.udemy.com/spring-boot-microservices-and-spring-cloud/learn/lecture/13622796#overview)

# Index
- Build and run RESTful Microservices
- Implement User Authentication
- Eureka Discovery Service
- Implement User Authorization with Spring Security and JWT
- Zuul API Gateway and a Load Balancer
- Learn to use JPA to persist data into a Database
- Cloud Cloud Config Server
- Learn to install MySQL Server and persist data into MySQL
- Spring Cloud Bus and Rabbit MQ
- H2 in-memory database and H2 Console
- Spring Boot Actuator
- Learn to use HTTP Postman
- Use Spring Security
- Learn to use Spring Initializer
- Learn to use Spring Tool Suite

# Microservices
Software development technique based on a collection of services.
- Light granularity 
- Each service is responsable of a small part of busyness logic.
- Improves modularity.
- Parallel development.
- Easily scalable.

# The meaning of the HTTP-verbs
> Ref: [https://hersengarage.nl/rest-api-design-as-a-craft-not-an-art-a3fd97ed3ef4](https://hersengarage.nl/rest-api-design-as-a-craft-not-an-art-a3fd97ed3ef4)

Mapping resources onto URLs allows clients to manipulate a resource with one of the following verbs:
- POST, to create a new resource (or any other non-idempotent operation*)
- PUT, to replace an existing resource
- PATCH, to (partially) update an existing resource
- DELETE, to delete an existing resource
- GET, to retrieve an existing resource

# Maven start project
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.6.RELEASE</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

<properties>
    <java.version>1.8</java.version>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

# Spring Boot main application class
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplicationClass {

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}
```

# Controller
```
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUser() {
        return "GET, to retrieve an existing resource";
    }

    @PostMapping
    public String crerateUser(){
        return "POST, to create a new resource (or any other non-idempotent operation*)";
    }

    @PutMapping
    public String replaceUser() {
        return "PUT, to replace an existing resource";
    }

    @PatchMapping
    public String updateUser() {
        return "PATCH, to (partially) update an existing resource";
    }

    @DeleteMapping
    public String deleteUser(){
        return "DELETE, to delete an existing resource";
    }
}
```