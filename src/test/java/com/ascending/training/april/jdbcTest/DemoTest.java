//package com.ascending.training.april.jdbcTest;
//
//import org.junit.*;
//import org.slf4j.LoggerFactory;
//
//import org.slf4j.Logger;
//
//public class DemoTest {
//    private static Logger logger = LoggerFactory.getLogger(DemoTest.class);
//
//    @BeforeClass
//    public static void initAllTest(){
//        logger.info(">>>>>>BeforeClass: start testing... ********\n");
//    }
//
//    @AfterClass
//    public static void endAllTest(){
//        logger.info(">>>>>>AfterClass: the tests are done! ********\n");
//    }
//
//    @Before
//    public  void beforeTest(){
//
//        logger.info(">>>>>>>Before: start unit testing...<<<<<<<<\n");
//    }
//
//    @After
//    public void afterTest(){
//
//        logger.info(">>>>>>>After: start unit testing...<<<<<\n");
//    }
//
//    @Test
//    public void test1() {
//        String str1 = new String("ABC");
//        String str2 = new String("ABC");
//        String str3 = "ABC";
//        String str4 = "ABC";
//
//        Assert.assertEquals(str2, str4);
//
//        Assert.assertSame(str2, str4);
//    }
//
//    @Test
//    public void test2() {
//        logger.trace("########## Test2 - Trace: test1 is done. ##########");
//        logger.debug("########## Test2 - Debug: test1 is done. ##########");
//        logger.info("########## Test2 - Info: test1 is done. ##########");
//        logger.warn("########## Test2 - Warn: test1 is done. ##########");
//        logger.error("########## Test2 - Error: test1 is done. ##########");
//    }
//
//}
