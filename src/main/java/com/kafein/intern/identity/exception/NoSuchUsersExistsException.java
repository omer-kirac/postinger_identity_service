package com.kafein.intern.identity.exception;

public class NoSuchUsersExistsException extends RuntimeException{
    private String message;
    public NoSuchUsersExistsException(){}

    public NoSuchUsersExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
