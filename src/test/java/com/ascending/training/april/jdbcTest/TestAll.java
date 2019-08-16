package com.ascending.training.april.jdbcTest;

import com.ascending.training.april.jdbcTest.AreaDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
//()里有{}代表array
@Suite.SuiteClasses({
        AreaDaoTest.class
}

)
public class TestAll {
}

/*
run specific test suit by maven:
mvn test -Dtest=com.ascending.training.repository.TestAll \
   -Ddatabase.driver=org.postgresql.Driver \
   -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect \
   -Ddatabase.url=jdbc:postgresql://localhost:5432/training_db \
   -Ddatabase.user=admin \
   -Ddatabase.password=Training123!
 */