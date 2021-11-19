package com.vivo.luiz.provadevfullstack.service.util.impl;

import com.vivo.luiz.provadevfullstack.service.util.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ConverterImpl<T,E> implements Converter<T,E> {

    @Override
    public E dtoParaEntidade(T element, Class<E> entidadeClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(element,entidadeClass);
    }

    @Override
    public T entidadeParaDto(E element, Class<T> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper.map(element,dtoClass);
    }

    @Override
    public List<T> listaEntidadeParaDto(List<E> element, Class<T> entidadeClass) {
        return element.stream().map(e->this.entidadeParaDto(e,entidadeClass)).collect(Collectors.toList());
    }

    @Override
    public Page<T> pageEntidadeParaDto(Page<E> ePage, Class<T> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return ePage.map(entity->modelMapper.map(entity,dtoClass));
    }
}
