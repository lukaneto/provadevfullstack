package com.vivo.luiz.provadevfullstack.exception;

import com.vivo.luiz.provadevfullstack.exception.data.ErrorDetalhe;
import com.vivo.luiz.provadevfullstack.exception.data.ErrorsData;
import org.springframework.http.HttpStatus;

import java.util.List;


public interface UtilException <E> {
    ErrorsData getErrorData(HttpStatus httpStatus, E exception);
    List<ErrorDetalhe> getListErrorDetalhe(E ex);
}
