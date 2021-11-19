package com.vivo.luiz.provadevfullstack.service;


import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> consultarId(Long id);
    Optional<Cliente> consultarCpf(String cpf);
    Optional<Cliente> salvarNovo(CriaCliente criaCliente);
    Optional<Cliente> atualizar(AtualizaCliente atualizaCliente);
    Page<Cliente> filtraCliente(FiltraCliente filtraCliente);
}
