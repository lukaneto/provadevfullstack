package com.vivo.luiz.provadevfullstack.exception.impl;

import com.vivo.luiz.provadevfullstack.exception.UtilException;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.http.HttpStatus;
import  org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilExceptionBindExceptionImpl implements UtilException<BindException> {
    @Override
    public ErrorsData getErrorData(HttpStatus httpStatus, BindException exception) {
        return new ErrorsData(httpStatus.getReasonPhrase(),"Campos inconsistentes ",getListErrorDetalhe(exception));
    }

    @Override
    public List<ErrorDetalhe> getListErrorDetalhe(BindException ex) {
        List<ErrorDetalhe> errorDetalhes = new ArrayList<>();
        for (FieldError cv : ex.getBindingResult().getFieldErrors()){
            errorDetalhes.add(new ErrorDetalhe(cv.getField(),cv.getDefaultMessage(),
                    Optional.ofNullable(cv.getRejectedValue()).orElse("null").toString()
            ));
        }
        return errorDetalhes;
    }
}
