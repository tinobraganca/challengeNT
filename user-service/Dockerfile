FROM mediasol/openjdk21-slim-jprofiler

COPY build/libs/user-service-0.0.6-SNAPSHOT.jar user-service.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/user-service.jar"]