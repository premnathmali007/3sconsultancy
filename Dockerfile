FROM openjdk:23-jdk
ADD target/consultancy-app.jar consultancy-app.jar
ENTRYPOINT [ "java", "-jar", "/consultancy-app.jar" ]