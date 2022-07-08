FROM maven:3.8.4-jdk-11 as packager
ARG MY_WORKDIR=/usr/src/naming-server
COPY . "$MY_WORKDIR"
WORKDIR "$MY_WORKDIR"
RUN mvn clean package -DskipTests

FROM openjdk:11.0-jre-slim
ARG MY_WORKDIR=/usr/src/naming-server
COPY --from=packager "$MY_WORKDIR/target/naming-server-0.0.1-SNAPSHOT.jar" "/"
EXPOSE 9061
ENTRYPOINT ["java","-jar","/naming-server-0.0.1-SNAPSHOT.jar"]