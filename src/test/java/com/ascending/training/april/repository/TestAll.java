package com.ascending.training.april.repository;

import com.ascending.training.april.jdbcTest.AreaDaoTest;
import com.ascending.training.april.jdbcTest.CustomerDaoTest;
import com.ascending.training.april.jdbcTest.HotelDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AreaMappingTest.class,
        CustomerMappingTest.class,
        HotelMappingTest.class
})

public class TestAll {
}
