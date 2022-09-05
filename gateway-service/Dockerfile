FROM maven:3.8.1-jdk-11-slim AS c2d431f3
WORKDIR /project
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk:11-jre
RUN mkdir -p /opt/logging \ && mkdir -p /opt/gateway-service/config
COPY --from=c2d431f3 /project/target/gateway-service.jar /opt/gateway-service/gateway-service.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=utf-8", "-Dsun.jnu.encoding=utf-8", "-jar", "/opt/gateway-service/gateway-service.jar" ]