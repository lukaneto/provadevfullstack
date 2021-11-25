package com.vivo.luiz.provadevfullstack.exception.impl;

import com.vivo.luiz.provadevfullstack.exception.UtilException;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilExceptionConstraintViolationExceptionImpl implements UtilException<ConstraintViolationException> {

    @Override
    public ErrorsData getErrorData(HttpStatus httpStatus, ConstraintViolationException exception) {
        return new ErrorsData(httpStatus.getReasonPhrase(),"Campos inconsistentes ",getListErrorDetalhe(exception));
    }

    @Override
    public List<ErrorDetalhe> getListErrorDetalhe(ConstraintViolationException ex) {
        List<ErrorDetalhe> errorDetalhes = new ArrayList<>();
        for (@SuppressWarnings("rawtypes") ConstraintViolation cv : ex.getConstraintViolations()){
            errorDetalhes.add(new ErrorDetalhe(cv.getPropertyPath().toString(),cv.getMessage(),
                    Optional.ofNullable(cv.getInvalidValue()).orElse("null").toString()
            ));
        }
        return errorDetalhes;
    }
}
