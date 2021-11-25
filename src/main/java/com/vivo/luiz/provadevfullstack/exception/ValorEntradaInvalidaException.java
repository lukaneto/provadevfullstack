package com.vivo.luiz.provadevfullstack.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValorEntradaInvalidaException extends RuntimeException{
    private static final long serialVersionUID=1l;
    private final String nomeCampo;
    private final String valorRecebido;


    public ValorEntradaInvalidaException(final String mensagem, final String nomeCampo,final String valorRecebido){
        super(mensagem);
        this.nomeCampo= nomeCampo;
        this.valorRecebido=valorRecebido;
    }
}
