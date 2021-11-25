package com.vivo.luiz.provadevfullstack.exception.impl;

import com.vivo.luiz.provadevfullstack.exception.UtilException;
import com.vivo.luiz.provadevfullstack.exception.ValorEntradaInvalidaException;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilExceptionMethodArgumentNotValidExceptionImpl implements UtilException<MethodArgumentNotValidException> {

    @Override
    public ErrorsData getErrorData(HttpStatus httpStatus, MethodArgumentNotValidException exception) {
        return new ErrorsData(httpStatus.getReasonPhrase(),"Campos inconsistentes ",getListErrorDetalhe(exception));
    }

    @Override
    public List<ErrorDetalhe> getListErrorDetalhe(MethodArgumentNotValidException ex) {
        List<ErrorDetalhe> errorDetalhes = new ArrayList<>();
        for (FieldError cv : ex.getBindingResult().getFieldErrors()){
            errorDetalhes.add(new ErrorDetalhe(cv.getField(),cv.getDefaultMessage(),
                    Optional.ofNullable(cv.getRejectedValue()).orElse("null").toString()
            ));
        }
        return errorDetalhes;
    }
}

