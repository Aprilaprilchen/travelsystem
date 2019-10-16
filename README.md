## Maven clean compile Command Line
```bash
mvn clean compile flyway:migrate -DdbUrl=${DB_URL} -DdbPort=5432 -DdbName=${DB_NAME} -DdbUser=${DB_USER} -DdbPassword=${DB_PASSWORD}
```

## Unit Test Command Line
```bash
mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://${DB_URL}:5432/${DB_NAME} -Ddatabase.user=${DB_USER}  -Ddatabase.password=${DB_PASSWORD} -Dlogging.level.org.springframework=INFO -Dlogging.level.com.ascending=TRACE -Dserver.port=8080 -Dsecret.key=AABB123
```
## Compile War File
```bash
mvn compile package -DskipTests=true
```
