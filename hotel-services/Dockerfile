FROM mediasol/openjdk21-slim-jprofiler

COPY build/libs/hotel-services-0.0.8-SNAPSHOT.jar hotel-services.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/hotel-services.jar"]