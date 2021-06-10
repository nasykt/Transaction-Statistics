package com.ts.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ts.Model.Transaction;
import com.ts.Service.tsService;

@RestController
public class tsController {
  
	    @Autowired
	    private tsService tss;
	 
	    @PostMapping("/transactions")
	    public ResponseEntity<?> newTransaction(@Valid @RequestBody Transaction transaction) {

	        tss.newTransaction(transaction);
	        return ResponseEntity.status(HttpStatus.CREATED).body(null);

	    }

	    @GetMapping("/statistics")
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<?> getStatics() {
            System.out.println("hi");
	        return ResponseEntity.status(HttpStatus.OK).body(tss.getStatistics());
	    }

	    @DeleteMapping("/transactions")
	    public ResponseEntity<?> deleteAll() {
	        tss.deleteAll();
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	    }
}
