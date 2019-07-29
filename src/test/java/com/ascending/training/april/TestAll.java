package com.ascending.training.april;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.model.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;

@RunWith(Suite.class)
//()里有{}代表array
@Suite.SuiteClasses({
        AreaDaoTest.class
}

)
public class TestAll {

}