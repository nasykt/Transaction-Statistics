package com.ts.Service;

import org.springframework.stereotype.Service;

import com.ts.Exception.TransactionNotValidException;
import com.ts.Exception.TransactionTimeNotValidException;
import com.ts.Model.Statistics;
import com.ts.Model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;


@Service
public class tsServiceImpl implements tsService{

    private static final List<Transaction> tr = new ArrayList<>(); //to store transactions
    private Object lock = new Object(); //lock adding process for threadSafe
    private Statistics statistics;

    @Override
    public void newTransaction(Transaction transaction) {
    	
        if (!validateTimeDiff(transaction)) {
            throw new TransactionNotValidException();
        }
        synchronized (lock) {
        	tr.add(transaction);
           //statisticAnalysis();
        }


    }

    @Override
    public Statistics getStatistics() {
        if (statistics != null) {
            removeOldData();
        }
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    @Override
    public void deleteAll() {

    	tr.clear();
        statistics = new Statistics();


    }


    public boolean validateTimeDiff(Transaction transaction) {
    	
    
            
        if (Instant.now().toEpochMilli() < transaction.getTimestamp().toEpochMilli()) {
            throw new TransactionTimeNotValidException();
        }

        if ((Instant.now().toEpochMilli() - transaction.getTimestamp().toEpochMilli()) < 60000
                && Instant.now().toEpochMilli() >= transaction.getTimestamp().toEpochMilli()) {

            return true;
        }

        return false;
    }

    public void statisticAnalysis() {
        if (tr.isEmpty()) {
            statistics = new Statistics();
        } else {
            DoubleSummaryStatistics stat = tr.stream().mapToDouble(Transaction::getAmount)
                    .summaryStatistics();
            statistics = new Statistics(BigDecimal.valueOf(stat.getSum()).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getAverage()).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getMax()).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getMin()).setScale(2, RoundingMode.HALF_UP),
                    stat.getCount());
        }
    }

    public void removeOldData() {

    	tr.removeIf(
                transcation -> (!validateTimeDiff(transcation)));

        statisticAnalysis();


    }
}
