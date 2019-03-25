FROM openjdk:8-jre-alpine

COPY libs/hello*.jar app.jar

CMD java -jar app.jar
