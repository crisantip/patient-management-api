# ----------- STAGE 1: COMPILACIÓN ----------------
FROM maven:3.9.9-eclipse-temurin-21-noble AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# ----------- STAGE 2: RUNTIME CON UBI + OPENJDK ----------------
# https://github.com/quarkusio/quarkus/discussions/50130
FROM registry.access.redhat.com/ubi9/openjdk-21:1.21

USER root

ENV TZ=America/Lima

RUN microdnf update -y && \
    microdnf clean all

COPY --chown=185 --from=build /app/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=build /app/target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=build /app/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=build /app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080

USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]
