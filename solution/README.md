README
====

HOW TO RUN ME?
====

Navigate to ../solution folder and run in command line:
    
    ./gradlew bootRun
    
Dependencies required:
* Gradle
* JDK 8+

Server runs on <i><b>port 9000</b></i>.

    Alternatively:
Open the project in an IDE of choice and read the LOMBOM - ANNOTATION PROCESSING paragraph below.
This is only required if you are not using Lombok already.

USEFUL COMMANDS
====
- <i><b>./gradlew build</b></i> (builds the project)
- <i><b>./gradlew test</b></i> (executes the tests)

NOTE
===
If Springboot is loading up slow on Mac - run in command line: 

        scutil --set HostName "localhost"
This is an issue with newest versions of Mac OS X where localhost is not resolved to 127.0.0.1. The above command resolves this issue.

TECHNOLOGIES USED
====
* Java
* Springboot
* [JSONdoc](http://jsondoc.org/) - Documentation for Java RESTful API.
* [Gradle](https://gradle.org/) - A dependency management and build tool.
* [Lombok](https://projectlombok.org) - A Java library that simplifies the majority of the Java boilerplate code 
such as getters and setters.
* [Flyway](https://flywaydb.org) - Version Control for the Database. Allows easy migrations and schema validations.
* [H2 Database](http://www.h2database.com/html/main.html) - (Default) In-memory database that is very small and very fast. 
Perfect for non-production testing.
* [PostgreSQL](https://hub.docker.com/r/library/postgres) - (Optional) H2 can be replaced by PostgreSQL by commenting out the H2 dependencies and adding in the PostgreSQL in application.properties and build.gradle. Restart the server and Flyway will automatically run the required sql scripts.
* [MySQL](https://hub.docker.com/r/library/mysql/) - (Optional) H2 can be replaced by MySQL by commenting out the H2 dependencies and adding in the MySQL in application.properties and build.gradle. Restart the server and Flyway will automatically run the required sql scripts.

LOMBOK - ANNOTATION PROCESSING
====
[Lombok](https://projectlombok.org) is a handy tool that helps you save time and keep pojo files clean.
For example, in a usual project a developer would end up generating large number of getters & setters. 
With Lombok, all you need to do is add the appropriate annotation and they will be automatically generated for you.

To setup, you need the following:
1. Add the dependency to your project build file, e.g. in grails (compile ('org.projectlombok:lombok:1.16.14')).
2. Add annotation processing plugin to your IDE, e.g. in IDEA find and download (Lombok plugin).
3. Make sure your IDE configuration is correct, e.g. in IDEA under Compiler -> Annotation Processors 
"enable annotation processing" must be enabled and 
"enable annotation processors from project classpath" must be selected.

API DOCUMENTATION
====
* Navigate to [JSONdoc UI page](http://localhost:9000/jsondoc-ui.html)
* In the URL type in <i><b>http://localhost:9000/jsondoc</b></i>
