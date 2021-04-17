package am.basic.springdemo.commons.handlers;


import am.basic.springdemo.commons.model.ErrorResponse;
import am.basic.springdemo.commons.model.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@ControllerAdvice
public class ResponseExceptionHandler {


    // this method will give automatically response when one of controllers throws IOException
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> handleException(IOException exception, HttpServletRequest webRequest) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", new Date());
        response.put("status", "ERROR");
        response.put("statusCode", HttpStatus.INSUFFICIENT_STORAGE.value());
        return new ResponseEntity<>(response, HttpStatus.INSUFFICIENT_STORAGE);
    }


    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleException(ResponseException exception, HttpServletRequest webRequest) {
        ErrorResponse responseDto = new ErrorResponse();
        responseDto.setTimestamp(new Date());
        responseDto.setPath(webRequest.getServletPath());
        responseDto.setError(exception.getStatus().getReasonPhrase());
        responseDto.setStatus(exception.getStatus().value());
        responseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseDto, exception.getStatus());

    }


}
