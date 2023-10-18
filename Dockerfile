FROM openjdk:19
WORKDIR /app
COPY target/http-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]


#
#FROM maven:3.8.4-openjdk-19 as builder
#WORKDIR /app
#COPY . /app/.
#RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true
#
#FROM eclipse-temurin:19-jre-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#EXPOSE 8181
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]