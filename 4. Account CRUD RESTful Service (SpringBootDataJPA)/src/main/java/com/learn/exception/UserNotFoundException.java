package com.learn.exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User Not Found");
	}
	
}