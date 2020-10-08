package com.cjw.shorturl.exception;

public class SamePasswordException extends Exception {
    public SamePasswordException(String msg){
        super(msg);
    }
}
