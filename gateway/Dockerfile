FROM maven:3.5-jdk-8-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD pom.xml /usr/src/app
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . /usr/src/app
RUN ["mvn", "package"]

EXPOSE 8081

CMD ["mvn", "spring-boot:run"]
