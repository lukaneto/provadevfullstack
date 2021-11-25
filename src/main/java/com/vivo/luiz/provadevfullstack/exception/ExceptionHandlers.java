package com.vivo.luiz.provadevfullstack.exception;

import com.vivo.luiz.provadevfullstack.exception.impl.UtilExceptionBindExceptionImpl;
import com.vivo.luiz.provadevfullstack.exception.impl.UtilExceptionConstraintViolationExceptionImpl;
import com.vivo.luiz.provadevfullstack.exception.impl.UtilExceptionMethodArgumentNotValidExceptionImpl;
import com.vivo.luiz.provadevfullstack.exception.impl.UtilExceptionValorEntradaInvalidaExceptionImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import  org.springframework.validation.BindException;



@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException ex){
        UtilException<ConstraintViolationException> utilException = new UtilExceptionConstraintViolationExceptionImpl();
        return new ResponseEntity<>(utilException.getErrorData(HttpStatus.BAD_REQUEST,ex),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ValorEntradaInvalidaException.class})
    protected ResponseEntity<Object> handlerValorEntradaInvalidaException(ValorEntradaInvalidaException ex){
        UtilException<ValorEntradaInvalidaException> utilException = new UtilExceptionValorEntradaInvalidaExceptionImpl();
        return new ResponseEntity<>(utilException.getErrorData(HttpStatus.BAD_REQUEST,ex),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        UtilException<MethodArgumentNotValidException> utilException = new UtilExceptionMethodArgumentNotValidExceptionImpl();
        return new ResponseEntity<>(utilException.getErrorData(HttpStatus.BAD_REQUEST,ex),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    protected ResponseEntity<Object> handlerMethodArgumentNotValidException(BindException ex){
        UtilException<BindException> utilException = new UtilExceptionBindExceptionImpl();
        return new ResponseEntity<>(utilException.getErrorData(HttpStatus.BAD_REQUEST,ex),HttpStatus.BAD_REQUEST);
    }
}
