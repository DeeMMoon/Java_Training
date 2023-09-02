# Day 1

## Sockets

Summary: Today you will implement the basic mechanism of a client/server application

based on Java Sockets API

The client/server interaction is the backbone of up-to-date systems. Server performs a large volume of business logic and information storage. As a result, the client application load is significantly reduced.

Dividing the logic into server and client components enables to flexibly build a general system architecture if server and client implementations are maximally independent.

## Exercise 00 
Turn-in directory : *ex*00*/* \
Files to turn in : Chat-folder \
Allowed functions : All

Before you start creating a full-scale, multi-user chat, you need to implement core functionality and build the foundational architecture of the system.

Now you need to create two applications: socket-server and socket-client. Server shall support connecting a single client and be made as a separate Maven project. Server JAR file is launched as follows:

`$ java -jar target/socket-server.jar -- port=8081`

Client is also a separate project:

`$ java -jar target/socket-client .jar -- server-port=8081`

In this task, you need to implement the registration functionality. Example of the client operation:

    Hello from Server!
    > signUp
    Enter username:
    > Marsel
    Enter password:
    > qwerty007
    Successful!

Connection must be closed after Successful! message appears.

To ensure secure storage of passwords, use a hashing mechanism with PasswordEncoder

and BCryptPasswordEncoder (see Spring Security components). Bin for this component

shall be described in a class of SocketsApplicationConfigconfigurationand used in UsersService.

Key client/server interaction logic and the use of UsersService via Spring Context shall ![ref2]be implemented in Server class.

Additional requirements:

- Use a single DataSource HikariCP
- Repository operation shall be implemented via JdbcTemplate
- Services, repositories, utility classes shall be context bins.

Server application architecture (client application is at the developer’s discretion):

    Char
    
      SocketServer
    
        src
    
          main
    
            java
    
              edu.school21.sockets
    
                server
    
                  Server
    
                models
    
                  User
    
                services
    
                  UsersService
                  
                  UsersServiceImpl
    
                repositories
    
                  CrudRepository
                  
                  UsersRepository
                  
                  UsersRepositoryImpl
    
                app
    
                  Main
    
                config
    
                  SocketsApplicationConfig
    
          resources
    
            db.properties
    
      pom.xml
