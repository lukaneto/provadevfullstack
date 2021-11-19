package com.vivo.luiz.provadevfullstack.service.util.impl;

import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.service.util.ClienteUtil;


import java.util.Optional;

public class ClienteUtilImpl implements ClienteUtil {
    @Override
    public Optional<Cliente> getClienteAtualizado(Optional<Cliente> clienteOptional, AtualizaCliente atualizaCliente) {
        if(clienteOptional.isPresent()){

            Cliente cliente = new Cliente();
            cliente.setId(atualizaCliente.getId());
            cliente.setNome(Optional.ofNullable(atualizaCliente).map(AtualizaCliente::getNome).orElse(clienteOptional.map(Cliente::getNome).orElse("")));
            cliente.setCpf(Optional.ofNullable(atualizaCliente).map(AtualizaCliente::getCpf).orElse(clienteOptional.map(Cliente::getCpf).orElse("")));
            cliente.setTelefone(Optional.ofNullable(atualizaCliente).map(AtualizaCliente::getTelefone).orElse(clienteOptional.map(Cliente::getTelefone).orElse("")));
            return Optional.ofNullable(cliente);
        }
        return Optional.empty();
    }


}
