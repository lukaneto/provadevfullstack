package com.vivo.luiz.provadevfullstack.service;


import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClienteService {
    Optional<ConsultaCliente> consultarId(Long id);
    Optional<ConsultaCliente> consultarCpf(String cpf);
    Optional<ConsultaCliente> salvarNovo(CriaCliente criaCliente);
    Optional<ConsultaCliente> atualizar(AtualizaCliente atualizaCliente);
    Page<ConsultaCliente> filtraCliente(FiltraCliente filtraCliente);
}
