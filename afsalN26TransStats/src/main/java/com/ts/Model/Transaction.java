package com.ts.Model;


import java.time.Instant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;


@Validated
public class Transaction {
	@Valid
	@NotNull
    private Double amount;
	@Valid
	@NotNull
    private Instant timestamp;


    public Transaction(Double amount, Instant timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }


    public Instant getTimestamp() {
        return timestamp;
    }


}

