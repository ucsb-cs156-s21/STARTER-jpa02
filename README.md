# STARTER-jpa02

* TODO: Change the title of this README 
  in the text `# STARTER-jpa02` above
  to match the name of your repo, i. e., `lab02-yourgithubid`, then delete
  this TODO item.

* TODO: Correct the links to repo below, 
  then delete this TODO.  Replace it with 
  a link to your repo, e.g. 
  https://github.com/ucsb-cs156-f20/jpa02-cgaucho

Repo: https://ucsb-cs156-f20/STARTER-jpa02

* TODO: Correct the links to app on Heroku below, 
  then delete this TODO.  Replace it with 
  a link to your running app on Heroku, e.g.
  https://cs156-f20-jpa02-cgaucho.herokuapp.com


On Heroku: https://cs156-f20-jpa02-cgaucho.herokuapp.com

* TODO: Correct the links to codecov report below
  then delete this TODO.  Replace it with 
  a link to your codecov report.

On Codecov: https://codecov.io/gh/ucsb-cs156-f20/jpa02-cgaucho


# About this repo

This is a minimal "Hello World" type webapp built with Spring Boot.


# What can you do with this code?

| Command | What it does   |
|----------|---------------------------------------|
| `mvn compile` | Should result in a clean compile |
| `mvn test` | Runs JUnit tests on the code base |
| `mvn test jacoco:report` | Runs JUnit tests, and if all tests pass, computes code coverage.  The code coverage report (Jacoco) can be found in `target/site/jacoco/index.html` |
| `mvn test org.pitest:pitest-maven:mutationCoverage` | Runs JUnit tests, and if all tests pass, runs pit (pitest.org) mutation testing to measure effectivness of test suite |
| `mvn package` | Builds the jar file `target/gs-spring-boot-0.1.0.jar` |
| `mvn spring-boot:run` | Runs the code to startup a web server.  Access it via `http://localhost:8080` on the *same machine* where the server is running.  Use CTRL/C to stop it. |
| `java -cp target/hello-1.0.0.jar edu.ucsb.cs156.spring.hello.Application` | If done after `mvn package`, runs the code to startup a web server.  |
| `java -jar target/hello-1.0.0.jar | If done after `mvn package`, this is another way to start up the web server.|


# Sources

The code in this repo is in support of
jpa02 for Fall 2020 for CMPSC 156.

The code in this repo is based in part on the tutorial here:
<https://spring.io/guides/gs/spring-boot/>, and the code here in the
`complete` directory of this repo
<https://github.com/spring-guides/gs-spring-boot.git>.  

That code has been
modified for use in UCSB CMPSC 156 as described
below.

# Modifications from the original

* Java 11 support
  * Converting `pom.xml` to use Java 11
* Heroku Support
  * Ensuring that the `PORT` environment variable is
    used to define the port on which Spring Boot starts the web server 
  * Providing a `Procfile` for deployment on Heroku
  * Added a `system.properties` file in the main directory
    which specifies the Java version for Heroku.
* Testing and CI
  * Adding JUnit tests
  * Adding jacoco as a plugin to measure test 
    case coverage
  * Adding pitest for mutation test coverage.
  * Adding support for GitHub Actions to run
    the test cases, compute jacoco report,
    upload code coverage reports to Codecov.io,
    and produce pitest artifacts.


