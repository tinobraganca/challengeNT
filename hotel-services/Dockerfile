FROM mediasol/openjdk21-slim-jprofiler

COPY build/libs/hotel-services-0.0.7-SNAPSHOT.jar hotel-services.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/hotel-services.jar"]