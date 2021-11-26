FROM openjdk:11
COPY "./target/client-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]