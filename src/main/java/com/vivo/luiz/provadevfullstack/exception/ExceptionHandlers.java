package com.vivo.luiz.provadevfullstack.exception;

import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException ex){
        List<ErrorDetalhe> list = getListErrorDetalhe(ex);
        ErrorsData errorsData = getErrorData(HttpStatus.BAD_REQUEST,list);
        return new ResponseEntity<>(errorsData,HttpStatus.BAD_REQUEST);
    }

    private ErrorsData getErrorData(HttpStatus httpStatus, List<ErrorDetalhe> errorDetalhes){
        return new ErrorsData(httpStatus.getReasonPhrase(),"Campos inconsistentes ",errorDetalhes);
    }

    private List<ErrorDetalhe> getListErrorDetalhe(ConstraintViolationException ex) {
        List<ErrorDetalhe> errorDetalhes = new ArrayList<>();
        for (@SuppressWarnings("rawtypes") ConstraintViolation  cv : ex.getConstraintViolations()){
            errorDetalhes.add(new ErrorDetalhe(cv.getPropertyPath().toString(),cv.getMessage(),
                    Optional.ofNullable(cv.getInvalidValue()).orElse("null").toString()
                    ));
        }
        return errorDetalhes;
    }
}
