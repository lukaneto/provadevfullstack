package com.vivo.luiz.provadevfullstack.service.specification.cliente;

import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.service.specification.PredicateQuery;
import com.vivo.luiz.provadevfullstack.service.specification.impl.PredicateEqualImpl;

import java.util.Optional;

public class ClienteSpecification {
    private final ClienteQuerySpecification clienteQuerySpecification;
    private final FiltraCliente filtraCliente;

    public ClienteSpecification(ClienteQuerySpecification clienteQuerySpecification,FiltraCliente filtraCliente){
        this.clienteQuerySpecification = clienteQuerySpecification;
        this.filtraCliente = Optional.ofNullable(filtraCliente).orElse(new FiltraCliente());
    }

    public ClienteQuerySpecification getClienteQuerySpecification(){
        clienteQuerySpecification.add(predicateQuery("nome", filtraCliente.getNome()));
        clienteQuerySpecification.add(predicateQuery("cpf", filtraCliente.getCpf()));
        clienteQuerySpecification.add(predicateQuery("telefone", filtraCliente.getTelefone()));
        return clienteQuerySpecification;
    }

    private PredicateQuery predicateQuery(String chave, String valor){
        if(isStringEmpty(chave) || isStringEmpty(valor)) return null;
        return new PredicateEqualImpl(chave,valor);
    }

    private boolean isStringEmpty(String valor){
        return Optional.ofNullable(valor).orElse("").trim().isEmpty();
    }
}
