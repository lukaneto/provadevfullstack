package com.vivo.luiz.provadevfullstack.exception.impl;

import com.vivo.luiz.provadevfullstack.exception.UtilException;
import com.vivo.luiz.provadevfullstack.exception.ValorEntradaInvalidaException;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilExceptionValorEntradaInvalidaExceptionImpl implements UtilException<ValorEntradaInvalidaException> {

    @Override
    public ErrorsData getErrorData(HttpStatus httpStatus, ValorEntradaInvalidaException exception) {
        return new ErrorsData(httpStatus.getReasonPhrase(),"Campos inconsistentes ",getListErrorDetalhe(exception));
    }

    @Override
    public List<ErrorDetalhe> getListErrorDetalhe(ValorEntradaInvalidaException ex) {
        List<ErrorDetalhe> errorDetalhes = new ArrayList<>();
        errorDetalhes.add(new ErrorDetalhe(ex.getNomeCampo(),ex.getMessage(),
                Optional.ofNullable(ex.getValorRecebido()).orElse("null")
        ));
        return errorDetalhes;
    }
}
