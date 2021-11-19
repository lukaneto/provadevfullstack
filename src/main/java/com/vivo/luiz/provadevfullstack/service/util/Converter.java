package com.vivo.luiz.provadevfullstack.service.util;
import org.springframework.data.domain.Page;

import java.util.List;


public interface Converter<T,E>{
     E dtoParaEntidade(T element,Class<E> entidadeClass);
     T entidadeParaDto(E element,Class<T> dtoClass);
     List<T> listaEntidadeParaDto(List<E> element, Class<T> dtoClass);
     Page<T> pageEntidadeParaDto(Page<E> ePage,  Class<T> dtoClass);
}

