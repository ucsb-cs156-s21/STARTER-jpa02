# Deploying to Heroku via connecting to Github

See: <https://ucsb-cs56.github.io/f19/lab/lab02>

#  Deploying to Heroku via the heroku-maven-plugin

There are several ways to deploy a Spring Boot app to Heroku.  One way is by using the `heroku-maven-plugin`.  

In order to be able to deploy this app to Heroku via Maven, we have added one dependency to the `pom.xml`, 
following the instructions here: <https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin>

```xml
<project>
  ...
  <build>
    ...
    <plugins>
      <plugin>
        <groupId>com.heroku.sdk</groupId>
        <artifactId>heroku-maven-plugin</artifactId>
        <version>2.0.11</version>
      </plugin>
    </plugins>
  </build>
</project>
```

To use this plugin, you need a Heroku application; you can create a new one or use an existing one.

You can do that with the Heroku CLI, if it is installed on your system, or you can do it in the Heroku web interface at this URL: <https://dashboard.heroku.com/new-app>.  Choose an app name, or leave it blank and one will be chosen for you.

Once you have created a new Heroku application, one thing you'll have a few things available to you is the 
URL of the deployed app, e.g. `warm-falls-30932.herokuapp.com`

The name of the Heroku app in this case is `warm-falls-30932`.

As part of configuring the plugin, you'll need to add this to the configuration like this:


```xml
      <plugin>
        <groupId>com.heroku.sdk</groupId>
        <artifactId>heroku-maven-plugin</artifactId>
        <version>2.0.11</version>
        <configuration>
          <appName>warm-falls-30932</appName>
          <processTypes>
            <web>java $JAVA_OPTS -cp target/classes:target/dependency/* Main</web>
          </processTypes>
        </configuration>
      </plugin>
```

You also need to define the `processType`.  This can be a bit tricky, since it depends on exactly how the application is configured.  In the example below, `hello.Application` is the fully qualified name of the class in which the `main` method is defined, the one that has the `@SpringBootApplication` on it.   By fully qualified, we mean that we need the full package name in front of it.    By convention, that would typically start with `com.` or `edu.` but in this case, the code simply has `hello.` as the top level package name.

```xml
      <plugin>
        <groupId>com.heroku.sdk</groupId>
        <artifactId>heroku-maven-plugin</artifactId>
        <version>2.0.11</version>
        <configuration>
          <appName>warm-falls-30932</appName>
          <processTypes>
            <web>java $JAVA_OPTS -cp target/classes:target/dependency/* hello.Application</web>
          </processTypes>
        </configuration>
      </plugin>
```

With this in place, you can try the following command:

```
mvn clean heroku:deploy
```

This may take a long time the first time you run it, as you'll be downloading many jar files for the first time. Those get cached so that subsequent runs should be faster.

A commmon error you might get is this one:

```
[ERROR] Failed to execute goal com.heroku.sdk:heroku-maven-plugin:2.0.11:deploy (default-cli) on project gs-spring-boot: Failed to deploy application: Insufficient privileges to "warm-falls-30932" statuscode:403 responseBody:{"id":"forbidden","message":"You do not have access to the app warm-falls-30932."} -> [Help 1]
```

This indicates that you need to authenticate yourself to Heroku; the most straightforward way to do that is using the Heroku CLI.  If you can install that on the platform where you are running (or if it's already there), login to the same
Heroku account that you used to create the application or one that has write access to it via:

```
heroku login
```

Then try the command again.

# Deploying to Heroku via a Procfile

If you cannot get the Heroku CLI for the platform on which you are running, an alternate way to deploy is to 
link your repo to Heroku via Github using the Heroku Dashboard so that every time you push to GitHub, it deploys the application to Heroku automatically.  To make this work, you need to create a `Procfile`

A `Procfile` is a file simply called `Procfile` (exactly with that spelling, uppercase/lowercase, and no file extension, i.e. `Procfile`, not `Procfile.txt`).  It will contain something like this:

```
web: java -jar target/myapp-1.0.0.jar
```

For this application, we'll adapt the contents of the `Procfile` from the `<web>` element of the `pom.xml`:

```
web: java $JAVA_OPTS -cp target/classes:target/dependency/* hello.Application
```

# A common error: Failed to bind to PORT

A very common error when trying to get a Spring-Boot app to start on Heroku is this one:

```
2019-09-17T01:57:12.872530+00:00 app[web.1]: webMvcTagsProvider
2019-09-17T01:57:12.872587+00:00 app[web.1]: webServerFactoryCustomizerBeanPostProcessor
2019-09-17T01:57:12.872645+00:00 app[web.1]: websocketServletWebServerCustomizer
2019-09-17T01:57:12.872718+00:00 app[web.1]: welcomePageHandlerMapping
2019-09-17T01:58:29.339504+00:00 heroku[web.1]: State changed from starting to crashed
2019-09-17T01:58:29.173956+00:00 heroku[web.1]: Error R10 (Boot timeout) -> Web process failed to bind to $PORT within 90 seconds of launch
2019-09-17T01:58:29.174051+00:00 heroku[web.1]: Stopping process with SIGKILL
2019-09-17T01:58:29.319157+00:00 heroku[web.1]: Process exited with status 137
2019-09-17T01:58:31.461474+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/" host=warm-falls-30932.herokuapp.com request_id=73ea2e0d-ea15-45fb-b93d-c9a3bfe17866 fwd="68.227.83.190" dyno= connect= service= status=503 bytes= protocol=https
2019-09-17T01:58:32.171625+00:00 heroku[router]: at=error code=H10 desc="App crashed" method=GET path="/favicon.ico" host=warm-falls-30932.herokuapp.com request_id=a4667c4c-6310-4384-b246-91c09bb9f3a6 fwd="68.227.83.190" dyno= connect= service= status=503 bytes= protocol=https
```

The root cause here is that Spring Boot, by default, wants to start up on port 8080.  Heroku, howeever, may be running many web server processes on a single host in its datacenter, and it isn't going to run all of them (or perhaps any of them) on port 8080.  Instead, it tells the application which port it *should* be running on, and it's the applications responsibility to choose that port.   So, we typically have to make a small change to our example code for Spring Boot apps when we decide we want to deploy on Heroku.

This webpage has more information: <https://www.callicoder.com/deploy-host-spring-boot-apps-on-heroku/>

There are two ways of making the change.  
The easiest is to add this code to the `src/main/resources/application.properties` file.  Note that this path must 
be precisely as specified here: `src/main/resources/application.properties` with no spelling or case differences.

```
server.port=${PORT:8080}
```

This says: use the `$PORT` environnmet variable, but if it isn't defined, use `8080`.

The other way is to pass ` -Dserver.port=$PORT `in the `Procfile` or `<web>` element of the Maven Heroku task.
