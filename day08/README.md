# Day 9
## Spring

Summary: Today you will learn about enterprise-level Java development and the basics

of the Spring framework

Spring Framework is an integral part of the most Java-based corporate systems. This framework makes it much easier to configure applications and relate components to each other. Due to this, a developer can fully focus on business logic implementation.

Spring operation principle is fully based on DI/IoC patterns which you should learn about before using this technology.

The central concept in Spring framework is a bin (component) that represents an object inside an ApplicationContext container. The container also creates links between bins.

There are several ways to configure bins:

1. Using an xml.file.
1. Using a Java configuration (configuring with annotations).
1. Combined configuration.

XML configurationallows to change application’s behavior without a reassembly. In turn, Java configuration makes code more developer-friendly.

## Exercise 00 
Turn-in directory : *ex*00*/*
Files to turn in : Spring-folder
Allowed functions : All

Let’s implement a loosely-coupled system comprised of a set of components (bins) and compliant with IoC/DI principles.

Let’s assume there is Printer interface designed to display a specific message.

This class has two implementations: PrinterWithDateTimeImpl and PrinterWithPrefixImpl.

The first class outputs messages by specifying output date/time using LocalDateTime,

while the second class can be used to set a text prefix for a message.

In turn, both Printer implementations have a dependency on Renderer interface that

sends messages onto the console. Renderer also has two implementations: RendererStandardImpl (outputs a message via standard System.out) and RendererErrImpl (outputs messages

via System.err).

Renderer also has a dependency on PreProcessor interface that pre-processes messages. Implementation of PreProcessorToUpperImpl translates all letters into upper case, while implementation of PreProcessorToLower translates all letters into lower case.

UML diagram of classes is shown below:

![diagram](diagram.png)

An example of code using these classes in a standard way:

    public class Main {
        public static void main(String[] args) {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer); 
        printer.setPrefix("Prefix");
        printer.print("Hello!");
      }
    }

Running this code will deliver the following result:

`PREFIX HELLO`

- You need to describe context.xml file for Spring, where all settings for each compo- nent and links between them will be specified.

Using these components with Spring looks as follows:

    public class Main {
        public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
      }
    }

## Exercise 01
Turn-in directory : *ex*01*/* \
Files to turn in : Service-folder \
Allowed functions : All

JdbcTemplate and its extension NamedParameterJdbcTemplate are convenient mechanisms for working with databases. These classes allow to eliminate writing template code for query execution and processing, as well as the need to intercept exceptions under check. In addition, they provide a convenient RowMapper concept for ResultSet processing and converting resulting tables into objects.

Now, you need to implement the User model with the following fields:

- Identifier
- Email

You also need to implement CrudRepository<T> interface with the following methods:

- T findById(Long id)
- List<T> findAll()
- void save(T entity)
- void update(T entity)
- void delete(Long id)

UsersRepository interface declared as UsersRepository extends CrudRepository<User> shall contain the following method:

- Optional<T> findByEmail(String email)

In addition, two implementations of UsersRepository are required: UsersRepositoryJdbcImpl (uses standard Statements meachanisms) and UsersRepositoryJdbcTemplateImpl (is based on![ref2] JdbcTemaplte/NamedParameterJdbcTemaple). Both classes shall accept DataSource object as a constructor argument.

In context.xml file,bins shall be declared for both repository types with different identifiers, as well as two bins of DataSource type: DriverManagerDataSource and HikariDataSource.

In addition, data for connecting to DB shall be specifiedin db.properties fileand included in context.xml using ${db.url} placeholders.

Example of db.properties:

db.url=jdbc:postgresql://localhost:5432/database db.user=postgres

db.password=qwerty007 db.driver.name=org.postgresql.Driver

In Main class, operation of findAll method shall be demonstrated using both repositories:

    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
    System.out.println(usersRepository.findAll());
    usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
    System.out.println(usersRepository.findAll());

Project structure:

    Service
    
      src
    
        main
    
          java
    
            school21.spring.service
    
              models
    
                User
    
              repositories
    
                CrudRepository
    
                UsersRepository
    
                UsersRepositoryJdbcImpl
    
                UsersRepositoryJdbcTemplateImpl 
              
              application
    
                Main
    
          resources
    
            db.properties
    
            context.xml
    
      pom.xml


## Exercise 02 
Turn-in directory : *ex*02*/* \
Files to turn in : Service-folder \
Allowed functions : All

Now, you need to configureSpring-application configurationmechanisms using annotations. To do so, use the configuration class marked as @Configuration. Inside this class, you need to describe bins for connecting to DataSource DB using @Bean annotation. As in the previous task, connection data shall be located inside db.properties file.You also need to make sure context.xml is not present.

Also implement UsersService/UsersServiceImpl interface/class pair with a dependency on UsersRepository declared in it. Insertion of correct repository bin shall be implemented using @Autowired annotation (similarly, you need to bind DataSource inside repositories). Collisions in automatic binding shall be resolved with @Qualifier annotation.

Bins for UsersService and UsersRepository shall be definedusing @Component annotation.

In UsersServiceImpl, implement String signUp(String email)method that registers a new user and saves its details in DB. This method returns a temporary password assigned to the user by the system (this information should also be saved in the database).

To check if your service works correctly, implement an integration test for UsersServiceImp using an in-memory database (H2 or HSQLDB). The context configuration for test environment (DataSource for in-memory database) shall be described in a separate TestApplicationConfig class. This test shall check if a temporary password has been returned in signUp method.

Project structure:

    Service
    
      src
    
        main
    
          java
    
            school21.spring.service

              config

                ApplicationConfig
    
              models
    
                User
    
              repositories
    
                CrudRepository
    
                UsersRepository
    
                UsersRepositoryJdbcImpl
    
                UsersRepositoryJdbcTemplateImpl 
                
              services
                
                UsersService
                
                UsersServiceImpl
                
              application
    
                Main
    
          resources
    
            db.properties
        test
          
          java
            
            school21.spring.service
              
              config
                
                TestApplicationConfig
            
            services
                
                UsersServiceImplTest
      pom.xml
