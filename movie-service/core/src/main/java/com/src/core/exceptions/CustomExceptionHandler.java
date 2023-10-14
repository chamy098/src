package com.src.core.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.src.datamodel.DTO.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) throws JsonProcessingException {
        String bodyOfResponse = ex.getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                OffsetDateTime.now(), HttpStatus.NOT_FOUND.value(), bodyOfResponse, request.getDescription(false));


        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}

