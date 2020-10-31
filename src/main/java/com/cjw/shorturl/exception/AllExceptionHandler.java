package com.cjw.shorturl.exception;

import com.cjw.shorturl.ConstConfig;
import com.cjw.shorturl.dto.CreateUrlResponse;
import com.cjw.shorturl.dto.UserSettingResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ShortUrlNotFoundException.class)
    public final String handleShortUrlNotFound(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({WrongCurrentPasswordException.class, SamePasswordException.class})
    public final UserSettingResponse handleWrongCurrentPassword(Exception e) {
        if (e instanceof WrongCurrentPasswordException) {
            return new UserSettingResponse(e.getMessage(), ConstConfig.WRONG_PASSWORD.getVal());
        } else {
            return new UserSettingResponse(e.getMessage(), ConstConfig.SAME_PASSWORD.getVal());
        }
    }

    @ExceptionHandler(UrlException.class)
    public final CreateUrlResponse handleUrl(Exception e){
        return new CreateUrlResponse("false", e.getMessage());
    }
}
