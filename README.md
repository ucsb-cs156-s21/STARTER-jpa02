# spring-boot-minimal-webapp

A minimal webapp built with Spring Boot.

This code started with the code available at this tutorial, as of 2019-09-16, 5pm

* Tutorial: <https://spring.io/guides/gs/spring-boot/>
* Code: <https://github.com/spring-guides/gs-spring-boot.git>
   * This repo contains only the content of the `complete` directory, moved to the top level
     so as to be more compatible with what is expected when you deploy to TravisCI or Heroku.
* Documentation: [ORIGINAL_README.adoc](/ORIGINAL_README.adoc)

# What can you do with this code?

| Command | What it does   |
|----------|---------------------------------------|
| `mvn compile` | Should result in a clean compile |
| `mvn test` | Runs one sucessful test |
| `mvn package` | Builds the jar file `target/gs-spring-boot-0.1.0.jar` |
| `java -jar target/gs-spring-boot-0.1.0.jar` | If done after `mvn package`, runs the code to startup a web server.  Access it via `http://localhost:8080` on the *same machine* where the server is running.  Use CTRL/C to stop it. |



