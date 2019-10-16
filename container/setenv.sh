export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://${DB_URL}:${DB_PORT}/${DB_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=${DB_USER}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=${DB_PASSWORD}"
export JAVA_OPTS="$JAVA_OPTS -Dlogging.level.org.springframework=INFO"
export JAVA_OPTS="$JAVA_OPTS -Dlogging.levelt.com.ascending=TRACE"
export JAVA_OPTS="$JAVA_OPTS -Dserver.port=8080"
export JAVA_OPTS="$JAVA_OPTS -Dsecret.key=AABB123"
export JAVA_OPTS="$JAVA_OPTS -Daws.accessKeyId=${AWE_ID}"
export JAVA_OPTS="$JAVA_OPTS -Daws.secretKey=${AWS_KEY}"