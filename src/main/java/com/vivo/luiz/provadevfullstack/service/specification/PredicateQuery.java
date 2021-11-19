package com.vivo.luiz.provadevfullstack.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateQuery <T>{

    Predicate getPredicate(Root<T> root, CriteriaQuery<T> query, CriteriaBuilder criteriaBuilder);
}
