package com.vivo.luiz.provadevfullstack.service.util.impl;

import com.vivo.luiz.provadevfullstack.exception.ValorEntradaInvalidaException;
import com.vivo.luiz.provadevfullstack.service.util.ValidacaoCliente;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoClienteImpl implements ValidacaoCliente {
    @Override
    public boolean isCpfClienteValido(String cpf) {
        if(!Optional.ofNullable(cpf).isPresent()) {
            throw new ValorEntradaInvalidaException("O cpf é obrigatorio para pesquisar","cpf",Optional.ofNullable(cpf).orElse("null"));
        }
        if(!getCpf(cpf)){
            throw new ValorEntradaInvalidaException("O cpf deve ser valido para pesquisar","cpf",Optional.ofNullable(cpf).orElse("null"));
        }
        return true;
    }

    private boolean getCpf(String cpf){
        Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cpf);
        StringBuilder valor = new StringBuilder();
        while (matcher.find()){
            valor.append(matcher.group().trim());
        }
        return !valor.toString().trim().isEmpty() && valor.toString().trim().length()>=11;
    }
}
