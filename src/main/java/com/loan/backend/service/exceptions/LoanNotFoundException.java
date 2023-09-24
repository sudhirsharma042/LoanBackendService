package com.loan.backend.service.exceptions;

public class LoanNotFoundException extends RuntimeException{

	public LoanNotFoundException(String message){
		super(message);
	}
}
