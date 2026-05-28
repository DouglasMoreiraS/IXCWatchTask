FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM debian:bookworm-slim

ENV TZ=America/Sao_Paulo
ENV SELENIUM_CHROME_DRIVER_PATH=/usr/bin/chromedriver
ENV SELENIUM_CHROME_BINARY_PATH=/usr/bin/chromium

WORKDIR /app

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        ca-certificates \
        chromium \
        chromium-driver \
        curl \
        fonts-liberation \
        openjdk-17-jre-headless \
        tzdata \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /build/target/IXCWatchTask-0.1.0.jar /app/IXCWatchTask.jar

EXPOSE 5052

ENTRYPOINT ["java", "-jar", "/app/IXCWatchTask.jar"]
