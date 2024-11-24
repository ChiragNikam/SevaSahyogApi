FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-slim

COPY --from=build /target/mentormate-server-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT [“java”,“-jar”,“demo.jar”]
