//package com.ascending.training.april.service;
//
//import com.amazonaws.services.dynamodbv2.xspec.S;
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.model.CreateQueueRequest;
//import com.amazonaws.services.sqs.model.Message;
//import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
//import com.ascending.training.april.init.AppInitializer;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.lang.Thread.sleep;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppInitializer.class)
//public class MessageServiceTest {
//
//    @Autowired MessageService messageService;
//    @Autowired AmazonSQS amazonSQS;
//    @Autowired
//    Logger logger;
//    String queueName = "cytiscute";
//    String url;
//    List<String> list = new ArrayList<>();
//    String msg = "This is a compliment message for cyt";
//    String msg2 = "I like cyt";
//    List<Message> messages = new ArrayList<>();
//
//    @Before
//    public void setUp() throws InterruptedException {
//        //sleep(60*1000);
//
//        try{
//            messageService.getQueueUrl(queueName);
//        }catch (QueueDoesNotExistException e){
//            messageService.createQueue(queueName);
//        }
////        try {
////            sleep(50 * 1000);
////        } catch (InterruptedException e) {}
//    }
//
//    @After
//    public void tearDown(){
////        if(!(messageService.getQueueUrl(queueName) == null)){
////            System.out.println(String.format(">>>>>>>>> The queue %s has been deleted <<<<<<<<<", queueName));
////            messageService.deleteQueue(queueName);
////        }
//    }
//
//    @Test
//    public void createQueue()throws InterruptedException{
//        sleep(60*1000);
//        url = messageService.getQueueUrl(queueName);
//        System.out.println(String.format(">>>>>>>>>> This Queue %s has been initialized <<<<<<<<<<", queueName));
//        Assert.assertNotNull(url);
//    }
//
//    @Test
//    public void listQueue(){
//        list = messageService.listQueue();
//        for (int i = 0; i<list.size(); i++){
//            System.out.println(String.format("This is queue %s: ", i));
//            System.out.println(list.get(i));
//        }
//        Assert.assertNotNull(list);
//    }
//
//    @Test
//    public void sendMessage(){
//        messageService.sendMessage(queueName, msg);
//        messageService.sendMessage(queueName, msg2);
//    }
//
//    @Test
//    public void getMessage(){
//        messages = messageService.getMessage(queueName);
//        for (int i=0; i<messages.size(); i++){
//            System.out.println(String.format(">>>>>>> This is message %s: <<<<<<<<", i));
//            System.out.println(messages.get(i).getBody());
//        }
//        Assert.assertNotNull(messages);
//    }
//
//    @Test
//    public void deleteMessage(){
//        messageService.deleteMessage(queueName, msg);
//        messageService.deleteMessage(queueName, msg2);
//    }
//
//    @Test
//    public void deleteQueue()throws InterruptedException{
//        messageService.deleteQueue(queueName);
//        Assert.assertNull(url);
//        sleep(70*1000);
//    }
//}