# Day 1

## Sockets

Summary: Today you will implement the basic mechanism of a client/server application

based on Java Sockets API

The client/server interaction is the backbone of up-to-date systems. Server performs a large volume of business logic and information storage. As a result, the client application load is significantly reduced.

Dividing the logic into server and client components enables to flexibly build a general system architecture if server and client implementations are maximally independent.

Exercise 00 : Registration

|![ref4]|Exercise 00|
| - | - |
|Registration||
|Turn-in directory : *ex*00*/*||
|Files to turn in : Chat-folder||
|Allowed functions : All||

Before you start creating a full-scale, multi-user chat, you need to implement core functionality and build the foundational architecture of the system.

Now you need to create two applications: socket-server and socket-client. Server shall support connecting a single client and be made as a separate Maven project. Server JAR file is launched as follows:

$ java -jar target/socket-server.jar -- port=8081![](Aspose.Words.0f989fbf-04ed-46c6-91d2-c63ce1a9f0d4.007.png)

Client is also a separate project:

$ java -jar target/socket-client .jar -- server-port=8081![](Aspose.Words.0f989fbf-04ed-46c6-91d2-c63ce1a9f0d4.008.png)

In this task, you need to implement the registration functionality. Example of the client operation:



|Hello from Server!|
| - |
|> signUp|
|Enter username:|
|> Marsel|
|Enter password:|
|> qwerty007|
|Successful!|

Connection must be closed after Successful! message appears.

To ensure secure storage of passwords, use a hashing mechanism with PasswordEncoder

and BCryptPasswordEncoder (see Spring Security components). Bin for this component

shall be described in a class of SocketsApplicationConfigconfigurationand used in UsersService.

6
Module 7 – Piscine Java Sockets![ref3]

Key client/server interaction logic and the use of UsersService via Spring Context shall ![ref2]be implemented in Server class.

Additional requirements:

- Use a single DataSource HikariCP
- Repository operation shall be implemented via JdbcTemplate
- Services, repositories, utility classes shall be context bins.

Server application architecture (client application is at the developer’s discretion):

Char![](Aspose.Words.0f989fbf-04ed-46c6-91d2-c63ce1a9f0d4.009.png)

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

7

<a name="_page7_x72.00_y74.49"></a>Chapter IV![ref2]

Exercise 01 : Messaging



|![ref4]|Exercise 01|
| - | - |
|Messaging||
|Turn-in directory : *ex*01*/*||
|Files to turn in : Chat-folder||
|Allowed functions : All||

Once you have implemented the application backbone, you should provide multi-user message exchange.

You need to modify the application so that it supports the following chat user life cycle:

1. Registration
1. Sign in (if no user is detected, close a connection)
1. Sending messages (each user connected to the server must receive a message)
1. Logout

Example of the application operation on the client side:



|Hello from Server!|
| - |
|> signIn|
|Enter username:|
|> Marsel|
|Enter password:|
|> qwerty007|
|Start messaging|
|> Hello!|
|Marsel: Hello!|
|NotMarsel: Bye!|
|> Exit|
|You have left the chat.|

Each message shall be saved in the database and contain the following information: Sender

￿￿ Message text

8
Module 9 – Piscine Java Sockets

- Sending time![ref2]

Note:

- For comprehensive testing, several jar files of the client application shall be run.

9

<a name="_page9_x72.00_y74.49"></a>Chapter V![ref2]

Exercise 02 : Rooms



|![ref4]|Exercise 02|
| - | - |
|Rooms||
|Turn-in directory : *ex*02*/*||
|Files to turn in : Chat-folder||
|Allowed functions : All||

To make our application fully-featured, let’s add the concept of "chatrooms" to it. Each chatroom can have a certain set of users. The chatroom contains a set of messages from participating users.

Each user can:

1. Create a chatroom
1. Choose a chatroom
1. Send a message to a chatroom
1. Leave a chatroom

When the user re-enters the application, 30 last messages shall be displayed in the room the user visited previously.

Example of the application operation on the client side:



|Hello from Server!|
| - |
|signIn|
|SignUp|
|Exit|
|> 1|
|Enter username:|
|> Marsel|
|Enter password:|
|> qwerty007|
|Create room|
|Choose room|
|Exit|
|> 2|
|Rooms:|
|First Room|
|SimpleRoom|

11
Module 12 – Piscine Java Sockets![ref3]

JavaRoom![](Aspose.Words.0f989fbf-04ed-46c6-91d2-c63ce1a9f0d4.010.png)![ref2]

Exit

- 3

Java Room --- JavaMan: Hello!

- Hello!

Marsel: Hello!

- Exit

You have left the chat.

Using JSON format for message exchange will be a special task for you. In this way, each user command or message must be transferred to the server (and received from the server) in the form of a JSON line.

For example, a command for sending a message may look as follows (specific contents of messages are at the discretion of a developer):



|{||
| - | :- |
||"message" : "Hello!",|
||"fromId" : 4,|
||"roomId": 10|
|}||
