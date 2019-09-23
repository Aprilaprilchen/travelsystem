package com.ascending.training.april.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.ascending.training.april.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class MessageServiceMockAWSTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AmazonSQS amazonSQS;
    @Autowired @Spy Logger logger;
    @InjectMocks MessageService messageService;
    String queueName = "cytsocute";
    String queueUrl = "http://this.is.a.fake.url.com";
    ListQueuesResult listQueuesResult = new ListQueuesResult();
    String msg = "This is a message";
    List<Message> list= new ArrayList<>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        logger.info(">>>>>>>> Start Testing... <<<<<<<<<");
    }

    @After
    public void tearDown(){
        logger.info(">>>>>>>> End Test <<<<<<<<");
    }

    @Test
    public void createQueue(){
        when(amazonSQS.getQueueUrl(queueName).getQueueUrl()).thenThrow(new QueueDoesNotExistException(""));
        messageService.createQueue(queueName);
        verify(amazonSQS, times(1)).createQueue(any(CreateQueueRequest.class));
    }

    @Test
    public void getQueueURL(){
        messageService.getQueueUrl(queueName);
        verify(amazonSQS, times(1)).getQueueUrl(anyString());
    }

    @Test
    public void listQueue(){
        when(amazonSQS.listQueues()).thenReturn(listQueuesResult);
        messageService.listQueue();
        verify(amazonSQS, times(1)).listQueues();
    }

    @Test
    public void deleteQueue(){
        messageService.deleteQueue(queueName);
        verify(amazonSQS, times(1)).deleteQueue(queueName);
    }

    @Test
    public void sendMessage(){
        messageService.sendMessage(queueName, msg);
        verify(amazonSQS, times(1)).sendMessage(any(SendMessageRequest.class));
    }

    @Test
    public void getMessage(){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        when(amazonSQS.receiveMessage(receiveMessageRequest).getMessages()).thenReturn(list);
        messageService.getMessage(queueName);
        verify(amazonSQS, times(1)).receiveMessage(receiveMessageRequest);
    }

    @Test
    public void deleteMessage(){
        messageService.deleteMessage(queueName, msg);
        verify(amazonSQS, times(1)).deleteMessage(anyString(), anyString());
    }
}
