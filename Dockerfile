FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

MAINTAINER verma.birendrakumar@bdo.com.ph

USER root

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar


ENV TZ=Asia/Manila

EXPOSE 8080

HEALTHCHECK CMD curl --fail http://localhost:8080/ms-evolution-native/actuator/health || exit 1

CMD ["java", "-jar", "-Xmx4096m", "-Xms1024m", "./app.jar"]


