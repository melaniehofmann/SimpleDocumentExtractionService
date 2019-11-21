FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} document-extraction-service-0.0.1-SNAPSHOT.jar

RUN mkdir -p /usr/.aws

ADD build usr/.aws

RUN mkdir -p /root/.aws

ADD build root/.aws

ENTRYPOINT ["java","-jar","/document-extraction-service-0.0.1-SNAPSHOT.jar"]