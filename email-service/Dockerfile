FROM maven:3.8.4-jdk-11 as packager
ARG MY_WORKDIR=/usr/src/email-service
COPY . "$MY_WORKDIR"
WORKDIR "$MY_WORKDIR"
RUN mvn clean package -DskipTests

FROM openjdk:11.0-jre-slim
ARG MY_WORKDIR=/usr/src/email-service
COPY --from=packager "$MY_WORKDIR/target/email-service-0.0.1-SNAPSHOT.jar" "/"
EXPOSE 9080
ENTRYPOINT ["java","-jar","/email-service-0.0.1-SNAPSHOT.jar"]