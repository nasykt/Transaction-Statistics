package com.ts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.ts.Model.Statistics;
import com.ts.Model.Transaction;
import com.ts.Service.tsService;

import java.time.Instant;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class tsTest {
    private final long second = 60000;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private tsService staticService;


    @Test
    public void testIfCurrentTimeReturnCreated() {

        assertEquals(HttpStatus.CREATED, restTemplate.postForEntity("/transactions", new Transaction(200.00, Instant.now()), Object.class)
                .getStatusCode());

    }

    @Test
    public void testIfPastTimeReturnNoContent() {

        assertEquals(HttpStatus.NO_CONTENT, restTemplate.postForEntity("/transactions", new Transaction(100.00, Instant.now().minusMillis(second)), Object.class)
                .getStatusCode());

    }

    @Test
    public void testIfFutureTimeReturnUnprocessable() {

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, restTemplate.postForEntity("/transactions", new Transaction(100.00, Instant.now().plusMillis(second)), Object.class)
                .getStatusCode());

    }

    

    @Test
    public void testStatisticsIfReturnCorrectCount(){
        staticService.deleteAll();
        staticService.newTransaction(new Transaction(25.00,Instant.now()));
        staticService.newTransaction(new Transaction(25.00,Instant.now()));
        staticService.newTransaction(new Transaction(25.00,Instant.now()));
        staticService.newTransaction(new Transaction(25.00,Instant.now()));
        staticService.newTransaction(new Transaction(25.00,Instant.now()));
        Statistics statics = staticService.getStatistics();
        assertTrue(statics.getCount().equals(5L));
    }
    @Test
    public void testStatisticsIfReturnCorrectSum(){
        staticService.deleteAll();
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        Statistics statics = staticService.getStatistics();
        assertTrue(statics.getSum().toString().equals("100.00"));
    }
    @Test
    public void testStatisticsIfReturnCorrectAvg(){
        staticService.deleteAll();
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(30.00,Instant.now()));
        staticService.newTransaction(new Transaction(40.00,Instant.now()));
        staticService.newTransaction(new Transaction(10.00,Instant.now()));
        staticService.newTransaction(new Transaction(60.00,Instant.now()));
        Statistics statics = staticService.getStatistics();
        assertTrue(statics.getAvg().toString().equals("32.00"));
    }
    @Test
    public void testStatisticsIfReturnCorrectMax(){
        staticService.deleteAll();
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(30.00,Instant.now()));
        staticService.newTransaction(new Transaction(40.00,Instant.now()));
        staticService.newTransaction(new Transaction(10.00,Instant.now()));
        staticService.newTransaction(new Transaction(60.00,Instant.now()));
        Statistics statics = staticService.getStatistics();
        assertTrue(statics.getMax().toString().equals("60.00"));
    }
    @Test
    public void testStatisticsIfReturnCorrectMin(){
        staticService.deleteAll();
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(30.00,Instant.now()));
        staticService.newTransaction(new Transaction(40.00,Instant.now()));
        staticService.newTransaction(new Transaction(10.00,Instant.now()));
        staticService.newTransaction(new Transaction(60.00,Instant.now()));
        Statistics statics = staticService.getStatistics();
        assertTrue(statics.getMin().toString().equals("10.00"));
    }
    @Test
    public void deleteTest(){
        staticService.newTransaction(new Transaction(20.00,Instant.now()));
        staticService.newTransaction(new Transaction(30.00,Instant.now()));
        staticService.newTransaction(new Transaction(40.00,Instant.now()));
        staticService.deleteAll();
        Statistics statics =staticService.getStatistics();
        assertTrue(statics.getCount().equals(0L));
    }

}
