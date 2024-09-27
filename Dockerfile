FROM openjdk:11-jdk
WORKDIR /app
COPY /target/Starbucks_fake-0.0.1-SNAPSHOT.jar /app/Starbucks_fake.jar
ENTRYPOINT ["java", "-jar", "Starbucks_fake.jar"]