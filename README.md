## Documentación
### Swagger
    http://localhost:8282/q/swagger-ui
### Git hub ejemplo
    https://github.com/GalaxyTraining/CURS-000409/tree/04.-Sesion04

### Docker build
```bash
  docker build -f Dockerfile -t csip/patient-management-api:1.0.0 .
```

### Docker run
```bash
  docker run -d \
    --name patient-management-api \
    -p 8282:8282 \
    -e JAVA_OPTS_APPEND="-Dquarkus.profile=prod -Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager" \
    -e APP_CONFIG_DATABASE_USERNAME=postgres \
    -e APP_CONFIG_DATABASE_PASSWORD=123456789 \
    -e APP_CONFIG_DATABASE_SERVER=192.168.100.19 \
    -e APP_CONFIG_DATABASE_PORT=5432 \
    -e APP_CONFIG_DATABASE_NAME=db_patient_management \
    csip/patient-management-api:1.0.0
```

### Docker compose
```bash
  docker compose -f docker-compose.yml -p patient-management-api --env-file .env up --force-recreate --build -d
```
