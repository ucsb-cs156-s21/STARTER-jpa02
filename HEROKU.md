# Deploying a Spring Boot application to Heroku via connecting to Github

To deploy this application to Heroku:

1. Create a Heroku account on the free plan.

   You should not need to enter a credit card, but you may need to verify your email.

2. Login to your Heroku account and navigate to the Heroku Dashboard.

   Create a new Heroku Application with the name `jpa02-yourGitHubId`, substituting
   your GitHub id in place of `yourGitHubId`.

   Example: `jpa02-cgaucho`

   If you find that your GitHub id is too long, or the application name is already taken,
   then modify your application name as needed.  (You'll be letting us know where your
   application is deployed so that we can check.)

3. Ensure that your repo contains a `Procfile` with appropriate contents, as explained below.

   A `Procfile` is a file simply called `Procfile` (exactly with that
   spelling, uppercase/lowercase, and no file extension, i.e. `Procfile`,
   not `Procfile.txt`).  It will contain something like this:

   ```
   web: java $JAVA_OPTS -cp target/hello-1.0.0.jar edu.ucsb.cs156.spring.hello.Application
   ```

   Here's the short explanation of how to check if the contents of this file are correct:

   * Do a `mvn clean package` in your repo
   * Then, type in the command you see *without* the `web:` and `$JAVA_OPTS` parts, e.g.
     ```
     java -cp target/hello-1.0.0.jar edu.ucsb.cs156.spring.hello.Application
     ```
     If this starts up the application on `http://localhost:8080` then the line is
     likely correct.

     If not, then see the longer explanation later in this file under
     "Explanation of the Procfile".
     
4. Navigate to the application you created on the Heroku Dashboard. 

   Navigate to <https://dashboard.heroku.com/> and login with your account.

   If not already there, select the application you created (e.g. `jpa02-cgaucho`).

   You will see a `Deploy` tab.   Click on this, and you'll then see a GitHub button
   towards the middle of the screen. Click on this, and you'll have an opportunity to
   select your repo and connect it to the Heroku application.

   Once you've connected your repo, scroll down to the `Manual Deploy` section
   select the appropriate branch (likely `main`) and click `Deploy Branch`.

   This should launch a window where a log of the activity while deploying comes up.
   It may take a while (e.g. 5 minutes) before the process is finished.  When it is
   finished, you should be able to navigate to your repo either by pressing the
   `Open App` button at the upper right hand corner of the Dashboard interface, or
   by navigating to <https://your-app-name.herokuapp.com> (where `your-app-name` is
   replaced by, for example, `jpa02-cgaucho`.)

   

# Explaining the `Procfile`

Here's a longer explanation of the `Procfile`:

* The `web: java JAVA_OPTS` part should always be present:
  * The name `web:` indicates that what follows is the command that should be used
    to start up the command. 
  * The `java` part indicates that we are starting up the Java Virtual Machine (JVM).
  * The `$JAVA_OPTS` part is necessary for Heroku to ensure that
    the the Java Virtual Machine (JVM) is configured appropriately for Heroku.

* The `-cp target/hello-1.0.0.jar` part should be changed based on the name of the
  the `.jar` file produced when you type `mvn package`.  This may change if the
  the `artifact` and/or `version` elements in the `pom.xml` are altered.

  To be sure what you have is correct, run `mvn package` and then type `ls target`
  to make sure that you have the name of the `.jar` file correct.

* The `edu.ucsb.cs156.spring.hello.Application` part is the full package and class
  name of the class in your Spring Boot application that contains the main, e.g.

  ```java
  package edu.ucsb.cs156.spring.hello;

  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;

  @SpringBootApplication
  public class Application {
    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }
  }
  ```

