FROM maven:3.6.1-jdk-8-slim AS build
COPY src /home/applicationlistener/src
COPY pom.xml /home/applicationlistener
USER root
RUN --mount=type=cache,target=/.m2 mvn -f /home/applicationlistener/pom.xml clean package -DskipTests=true

FROM openjdk:11-jre
COPY --from=build /home/applicationlistener/target/applicationlistener.jar /usr/local/lib/applicationlistener.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/applicationlistener.jar"]
