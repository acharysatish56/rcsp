FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/acharysatish56/rcsp.git
FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app/rcsp /app
RUN mvn install -Dmaven.test.skip=true
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=1 /app/target/rcsp-0.0.1-SNAPSHOT.jar /app
CMD ["java -jar rcsp-0.0.1-SNAPSHOT.jar"]