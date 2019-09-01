package com.ascending.training.april.inhibernateTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AreaDaoImplTest.class,
        CustomerDaoImplTest.class,
        HotelDaoImplTest.class
})

public class TestAll {
}
