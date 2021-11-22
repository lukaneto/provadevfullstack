package com.vivo.luiz.provadevfullstack.exception.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetalhe {
    private String campo;
    private String mensagem;
    private Object valor;
}
