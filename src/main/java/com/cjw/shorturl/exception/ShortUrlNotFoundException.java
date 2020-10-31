package com.cjw.shorturl.exception;

public class ShortUrlNotFoundException extends Exception {
    public ShortUrlNotFoundException(String message){
        super(message);
    }
}
