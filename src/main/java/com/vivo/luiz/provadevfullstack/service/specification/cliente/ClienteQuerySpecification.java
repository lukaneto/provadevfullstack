package com.vivo.luiz.provadevfullstack.service.specification.cliente;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.service.specification.PredicateQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteQuerySpecification implements Specification<Cliente> {

    private final transient List<PredicateQuery> predicateQueryList;

    public ClienteQuerySpecification() {
        this.predicateQueryList = new ArrayList<>();
    }

    public void add(PredicateQuery predicateQuery){
        if(Optional.ofNullable(predicateQuery).isPresent()){
            predicateQueryList.add(predicateQuery);
        }
    }

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        for (PredicateQuery predicateQuery :predicateQueryList) {
            Predicate predicate = predicateQuery.getPredicate(root,query,criteriaBuilder);
            predicateList.add(predicate);
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
