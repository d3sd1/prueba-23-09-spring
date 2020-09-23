package com.asaitec.rest.controllerAdvice;

import com.asaitec.rest.exception.NoOperatorFoundException;
import com.asaitec.rest.exception.NoTicketFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * Handle exception for endpoint.
     * @param ex Exception thrown.
     * @param request Request to manage.
     * @return ResponseEntity with error description.
     */
    @ExceptionHandler({NoOperatorFoundException.class})
    public ResponseEntity<Object> handleNoOperatorFound(
            NoOperatorFoundException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Operator not found.", new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Handle exception for endpoint.
     * @param ex Exception thrown.
     * @param request Request to manage.
     * @return ResponseEntity with error description.
     */
    @ExceptionHandler({NoTicketFoundException.class})
    public ResponseEntity<Object> handleNoTicketFound(
            NoTicketFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                "Ticket not found.", new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
