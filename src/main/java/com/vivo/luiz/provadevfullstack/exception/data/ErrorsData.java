package com.vivo.luiz.provadevfullstack.exception.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorsData {
    private final String codigo;
    private final String mensagem;
    private final List<ErrorDetalhe> camposErrosDetalhes;

}
