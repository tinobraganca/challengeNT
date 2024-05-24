FROM mediasol/openjdk21-slim-jprofiler

COPY build/libs/search-service-0.0.21-SNAPSHOT.jar search-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/search-service.jar"]