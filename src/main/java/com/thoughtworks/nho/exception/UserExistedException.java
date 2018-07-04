package com.thoughtworks.nho.exception;

public class UserExistedException extends RuntimeException {

    public UserExistedException(String s) {
        super(s);
    }
}
