package com.vivo.luiz.provadevfullstack.service.specification.impl;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.service.specification.PredicateQuery;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class PredicateEqualImpl implements PredicateQuery<Cliente> {

    private final String chave;
    private final Object valor;

    public PredicateEqualImpl(String chave, Object valor){
        this.chave=chave;
        this.valor=valor;
    }

    @Override
    public Predicate getPredicate(Root<Cliente> root, CriteriaQuery<Cliente> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(chave),valor);
    }
}
