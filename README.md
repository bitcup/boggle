# Boggle Solver

A web-based solver for the game [Boggle](https://en.wikipedia.org/wiki/Boggle).  The user can enter their Boggle board in the app and it will list all valid words contained in the board.

### Tech Stack
The app is written in Java on the backend and jQuery/Bootstrap on the front end.  It also uses:
* Spring Boot as application framework (IoC, API, web, testing)
* Apache Maven as build automation tool
* Project Lombok to reduce boilerplate Java code
* Docker for containerization and deployment to AWS ECS

### How to Run
The application can be built and run locally via Maven or Docker.

#### Via Maven
Requires Java 1.8 or openJDK 8 to be installed
```
$ cd boggle
$ ./mvnw spring-boot:run
```
Access app on `http://localhost:8080/`

#### Via Docker
Requires Docker to be installed
```
$ cd boggle
$ ./mvnw clean package docker:build -DskipTests
$ docker run -d -p 8080:8080 bitcup/boggle:1
```
Access app on `http://localhost:8080/`

### Resources
The following resources contributed ideas and/or code to the creation of this app:
* [Boggle Solver](http://www.geekviewpoint.com/java/boggle/solver) that uses a trie-based data structure for validating words against a dictionary, and a depth-first-search for word generation from the board.
* [English dictionary words](https://norvig.com/ngrams/) from the YAWL (Yet Another Word List) word list containing 263,533 words.
* [Spring Boot with Ajax](https://www.mkyong.com/spring-boot/spring-boot-ajax-example/) as an example of how to use jQuery to send a HTML form request to a Spring REST API and return a JSON response.   

### To Dos
To keep development time under the allotted limit, the following areas were not fully explored/developed:
* Test coverage: Only the `Solver` is covered by a unit test and the focus is on correctness.  Tests for performance or scalability are not included.
* Input validation: The app expects the board data to be input in a specific format, such as `GIZ\nUEK\nQSE` for a 3x3 grid.  Using any delimeters in between letters in the same row, or anything other than `\n` in between rows will result in an exception.    
* Various optimizations: While the approach used is reasonably performant, other optimizations could be implemented based on analysis of where the code is slowest or most resource-intensive.
* Javadoc: The code is mostly self documenting, but there are places in the code that could use some high level explanation.
