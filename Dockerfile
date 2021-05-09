FROM openjdk:12-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /Hades <!-- Name of the project -->

CMD ./gradlew run