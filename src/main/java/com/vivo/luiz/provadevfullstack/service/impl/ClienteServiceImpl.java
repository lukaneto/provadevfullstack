package com.vivo.luiz.provadevfullstack.service.impl;

import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.repository.ClienteRepository;
import com.vivo.luiz.provadevfullstack.service.ClienteService;
import com.vivo.luiz.provadevfullstack.service.specification.cliente.ClienteQuerySpecification;
import com.vivo.luiz.provadevfullstack.service.specification.cliente.ClienteSpecification;
import com.vivo.luiz.provadevfullstack.service.util.ClienteUtil;
import com.vivo.luiz.provadevfullstack.service.util.Converter;
import com.vivo.luiz.provadevfullstack.service.util.impl.ClienteUtilImpl;
import com.vivo.luiz.provadevfullstack.service.util.impl.ConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> consultarId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> consultarCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Cliente> salvarNovo(CriaCliente criaCliente) {
        Converter<CriaCliente,Cliente> converter = new ConverterImpl<>();
        Cliente cliente = converter.dtoParaEntidade(criaCliente,Cliente.class);
        return Optional.of(clienteRepository.save(cliente));
    }

    @Override
    public Optional<Cliente> atualizar(AtualizaCliente atualizaCliente) {
      Optional<Cliente> optionalClienteBanco = consultarId(atualizaCliente.getId());
      if(optionalClienteBanco.isPresent()){
          ClienteUtil clienteUtil = new ClienteUtilImpl() ;
          Optional<Cliente> cliente = clienteUtil.getClienteAtualizado(optionalClienteBanco,atualizaCliente);
          cliente.ifPresent(value -> clienteRepository.save(value));
          return  cliente;
      }
        return Optional.empty();
    }


    @Override
    public Page<Cliente> filtraCliente(FiltraCliente filtraCliente) {
        Pageable pageable = PageRequest.of(Optional.ofNullable(filtraCliente).map(FiltraCliente::getPagina).orElse(0), Optional.ofNullable(filtraCliente).map(FiltraCliente::getTamanho).orElse(20));
        ClienteSpecification clienteSpecification = new ClienteSpecification(new ClienteQuerySpecification(), filtraCliente);
        return clienteRepository.findAll(clienteSpecification.getClienteQuerySpecification(),pageable);
    }
}
