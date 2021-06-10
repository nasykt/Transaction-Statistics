package com.ts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT,reason = "Transaction is old!")
public class TransactionNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

}
