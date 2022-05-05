FROM amazoncorretto:17 as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests

FROM amazoncorretto:17-alpine-jdk
COPY --from=builder /src/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
