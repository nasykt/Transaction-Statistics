package com.ts.Service;

import com.ts.Model.Statistics;
import com.ts.Model.Transaction;

public interface tsService {
    void newTransaction(Transaction transaction);
    Statistics getStatistics();
    void deleteAll();

}