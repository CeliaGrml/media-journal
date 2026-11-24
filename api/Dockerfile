FROM maven:3.9.15-amazoncorretto-25-alpine AS maven
WORKDIR /tmp/build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn package -DskipTests && mv target/*.jar target/app.jar

FROM amazoncorretto:25.0.3-alpine3.23

WORKDIR /app
COPY --from=maven /tmp/build/target/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
