#!/bin/bash
FROM gradle:jdk11 AS gradleimage

FROM --platform=linux/amd64 openjdk:11-jre-slim
ADD ./build/libs/inventory-service-1.0.0-SNAPSHOT.jar inventoryservice.jar
ENTRYPOINT ["java","-jar","/inventoryservice.jar"]