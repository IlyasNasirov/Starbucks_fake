FROM openjdk:11-jdk
WORKDIR /app
COPY /target/Starbucks_fake-0.0.1-SNAPSHOT.jar /app/Starbucks_fake.jar
CMD ["sh", "-c", "java -jar Starbucks_fake.jar --server.port=${PORT}"]