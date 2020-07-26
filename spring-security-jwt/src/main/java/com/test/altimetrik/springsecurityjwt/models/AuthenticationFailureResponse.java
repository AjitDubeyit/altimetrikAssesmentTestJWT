package com.test.altimetrik.springsecurityjwt.models;

public class AuthenticationFailureResponse {
	private final String message;

    public AuthenticationFailureResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
