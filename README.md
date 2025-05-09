# Simple Java App for LogObserverConnect

# PreRequisits
Java 17 and maven installed
Docker to build images

## General
in `pom.xml`and `docker/Dockerfile`change the AppDynamics information according to your environment.
Copy a java agent into the folder `artifact/javaagent`

## Building
`mvn clean install`
This creates the jar file in ./target

## Running
`java -jar target/*.jar`
Runs the application with default settings.
The application will listen on port 8091

alternatively you can run
`mvn spring-boot:run`

## Accessing the App 
The application exposes 3 enpoints:
- `/hello`
- `/goodbye`
- `/greeting?name=someone&age=49`
  The parameters name and age are optional

for example:
`curl 'localhost:8091/greeting?name=Alex&age=49'`

## Log Configuration
The log configuration for logback can be found in
`src/main/resources/logback-spring.xml` and is included in the jar file. For changes of the pattern to take effect, modify this file and rebuild the project.

To use an external logback configuration, you can follow these instructions. https://stackoverflow.com/questions/50576521/spring-boot-does-not-load-logback-spring-xml

## Dockerfile
`mvn compile jib:dockerBuild` for building the image localy. You may need to adjust naming in `pom.xml`
`mvn compile jib:build -Dimage=your-docker-id/demo-app:custom-tag` for building the image and push it to the registry.
You can also pass the controller information on the build process instead of modifying the pom.xml.
```shell
mvn clean install jib:dockerBuild \
  -Dappdynamics.controller.hostName=<host> \
  -Dappdynamics.controller.port=443 \
  -Dappdynamics.controller.accountName=<account> \
  -Dappdynamics.controller.accountAccessKey=<key> \
  -Dappdynamics.controller.ssl.enabled=true \
  -Dappdynamics.applicationName=LogObserverConnectTest \
  -Dappdynamics.tierName=Demo-App \
  -Dappdynamics.nodeName=Demo-App-Node \
  -Dimage=your-docker-id/demo-app:custom-tag
```

## Running the docker application
```docker run -p 8091:8091 loc-demo-app```

