package com.vivo.luiz.provadevfullstack.service.impl;

import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.repository.ClienteRepository;
import com.vivo.luiz.provadevfullstack.service.ClienteService;
import com.vivo.luiz.provadevfullstack.service.specification.cliente.ClienteQuerySpecification;
import com.vivo.luiz.provadevfullstack.service.specification.cliente.ClienteSpecification;
import com.vivo.luiz.provadevfullstack.service.util.ClienteUtil;

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
    public Optional<ConsultaCliente> consultarId(Long id) {
        var converter = new ConverterImpl<ConsultaCliente,Cliente>();
        Optional<Cliente> cliente = consultaId(id);
        if(cliente.isPresent()){
            Optional<ConsultaCliente> consultaCliente =  Optional.ofNullable(converter.entidadeParaDto(cliente.get(),ConsultaCliente.class));
            return consultaCliente;
        }
        return Optional.empty();
    }



    private Optional<Cliente> consultaId(Long id) {
        if(Optional.ofNullable(id).isPresent()) {
            return clienteRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConsultaCliente> consultarCpf(String cpf) {
        var converter = new ConverterImpl<ConsultaCliente,Cliente>();
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isPresent()) {
            Optional<ConsultaCliente> consultaCliente = Optional.ofNullable(converter.entidadeParaDto(clienteOptional.get(), ConsultaCliente.class));
            return consultaCliente;
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConsultaCliente> salvarNovo(CriaCliente criaCliente) {
        var converter = new ConverterImpl<ConsultaCliente,Cliente>();
        var converterCriaCliente = new ConverterImpl<CriaCliente,Cliente>();
        Cliente cliente = converterCriaCliente.dtoParaEntidade(criaCliente,Cliente.class);
        cliente=  clienteRepository.save(cliente);
        return Optional.ofNullable(converter.entidadeParaDto(cliente,ConsultaCliente.class));
    }

    @Override
    public Optional<ConsultaCliente> atualizar(AtualizaCliente atualizaCliente) {
      if(Optional.ofNullable(atualizaCliente).map(AtualizaCliente::getId).isPresent()) {
          Optional<Cliente> optionalClienteBanco = consultaId(atualizaCliente.getId());
          if (optionalClienteBanco.isPresent()) {
              ClienteUtil clienteUtil = new ClienteUtilImpl();
              Optional<Cliente> cliente = clienteUtil.getClienteAtualizado(optionalClienteBanco, atualizaCliente);
              cliente.ifPresent(value -> clienteRepository.save(value));
              var converter = new ConverterImpl<ConsultaCliente,Cliente>();
              return Optional.ofNullable(converter.entidadeParaDto(cliente.get(),ConsultaCliente.class));
          }
      }
      return Optional.empty();
    }


    @Override
    public Page<ConsultaCliente> filtraCliente(FiltraCliente filtraCliente) {
        Pageable pageable = PageRequest.of(Optional.ofNullable(filtraCliente).map(FiltraCliente::getPagina).orElse(0), Optional.ofNullable(filtraCliente).map(FiltraCliente::getTamanho).orElse(20));
        ClienteSpecification clienteSpecification = new ClienteSpecification(new ClienteQuerySpecification(), filtraCliente);
        var converter = new ConverterImpl<ConsultaCliente,Cliente>();
        Page<Cliente> page =  clienteRepository.findAll(clienteSpecification.getClienteQuerySpecification(),pageable);
        return converter.pageEntidadeParaDto(page,ConsultaCliente.class);
    }
}
