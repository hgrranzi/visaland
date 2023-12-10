FROM maven:3.8.5-openjdk-18 as build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . .

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]