package com.cjw.shorturl.exception;

import com.cjw.shorturl.ConstConfig;
import com.cjw.shorturl.dto.CreateUrlResponseDTO;
import com.cjw.shorturl.dto.UserSettingResponseDTO;
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
    public final UserSettingResponseDTO handleWrongCurrentPassword(Exception e) {
        if (e instanceof WrongCurrentPasswordException) {
            return new UserSettingResponseDTO(e.getMessage(), ConstConfig.WRONG_PASSWORD.getVal());
        } else {
            return new UserSettingResponseDTO(e.getMessage(), ConstConfig.SAME_PASSWORD.getVal());
        }
    }

    @ExceptionHandler(UrlException.class)
    public final CreateUrlResponseDTO handleUrl(Exception e){
        return new CreateUrlResponseDTO("false", e.getMessage());
    }
}
