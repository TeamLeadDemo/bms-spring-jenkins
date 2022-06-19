package com.demo.bms.exception;

public class BookEmptyException extends Exception{
	
	@Override
	public String getMessage() {
		return "Store is Empty!!";
	}
}
