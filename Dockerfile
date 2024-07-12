FROM openjdk:11
EXPOSE 8080
ADD target/catalog-project-service-0.0.1-SNAPSHOT.jar catalog-project-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/catalog-project-service-0.0.1-SNAPSHOT.jar"]
