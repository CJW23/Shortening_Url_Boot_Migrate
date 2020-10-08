package com.cjw.shorturl.exception;

public class WrongCurrentPasswordException extends Exception{
    public WrongCurrentPasswordException(String msg){
        super(msg);
    }
}
