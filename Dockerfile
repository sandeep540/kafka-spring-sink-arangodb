FROM maven:3.8-eclipse-temurin-17 AS build-env
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
WORKDIR /app
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true

FROM gcr.io/distroless/java17-debian11
COPY --from=build-env /app/target/*.jar /app/target
WORKDIR /app/target
CMD ["kafka-sink-*.jar"]