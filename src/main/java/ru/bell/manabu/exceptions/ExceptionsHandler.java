package ru.bell.manabu.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bell.manabu.View.ResponseData;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler
    protected ResponseEntity<Object> handleUncaughtExceptions(RuntimeException ex, WebRequest request) {
        logger.error("Uncaught error", ex);

        ResponseData<String> error = new ResponseData<>();
        error.addError("Server error");

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
