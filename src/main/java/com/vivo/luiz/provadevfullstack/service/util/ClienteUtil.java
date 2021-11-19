package com.vivo.luiz.provadevfullstack.service.util;

import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;

import java.util.Optional;

public interface ClienteUtil {
    Optional<Cliente>  getClienteAtualizado(Optional<Cliente> clienteOptional, AtualizaCliente atualizaCliente);
}
