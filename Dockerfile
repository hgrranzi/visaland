FROM maven:3.8.5-openjdk-18-slim as build

WORKDIR /app

COPY . .

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]