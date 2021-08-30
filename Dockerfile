FROM openjdk:8-alpine

COPY target/uberjar/raid-analyzer.jar /raid-analyzer/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/raid-analyzer/app.jar"]
