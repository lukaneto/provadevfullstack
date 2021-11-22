package com.vivo.luiz.provadevfullstack.service.util;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.service.util.impl.ClienteUtilImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ClienteUtilImplTest {

    private ClienteUtilImpl util;

    private Cliente cliente;

    private AtualizaCliente atualizaCliente;


    private AtualizaCliente atualizaClienteNome;

    private AtualizaCliente atualizaClienteCpf;

    private AtualizaCliente atualizaClienteTelefone;

    @BeforeEach
    public void setUp(){
        util = new ClienteUtilImpl();
        cliente = getCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");
        atualizaCliente = AtualizaCliente.builder().id(cliente.getId()).cpf(cliente.getCpf()).nome(cliente.getNome()).telefone(cliente.getTelefone()).build();
        atualizaClienteNome = AtualizaCliente.builder().id(cliente.getId()).nome("teste").build();
        atualizaClienteTelefone = AtualizaCliente.builder().id(cliente.getId()).telefone("+55 (22) 2222-2222").build();
        atualizaClienteCpf = AtualizaCliente.builder().id(cliente.getId()).cpf("402.696.190-70").build();

    }

    private Cliente getCliente(Long id, String nome, String cpf, String telefone ){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        return cliente;
    }

    @Test
    public void deveTestarAtualizarCliente(){
        Optional<Cliente> expected = Optional.of(getCliente(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone()));
        Optional<Cliente> tested = util.getClienteAtualizado(Optional.of(cliente),atualizaCliente);
        Assertions.assertEquals(expected.get().getCpf(),tested.get().getCpf());
    }


    @Test
    public void deveTestarAtualizarClienteCpf(){
        Optional<Cliente> expected = Optional.of(getCliente(cliente.getId(), cliente.getNome(), atualizaClienteCpf.getCpf(), cliente.getTelefone()));
        Optional<Cliente> tested = util.getClienteAtualizado(Optional.of(cliente),atualizaClienteCpf);
        Assertions.assertEquals(expected.get().getCpf(),tested.get().getCpf());
    }

    @Test
    public void deveTestarAtualizarClienteNome(){
        Optional<Cliente> expected = Optional.of(getCliente(cliente.getId(), atualizaClienteNome.getNome(), cliente.getCpf(), cliente.getTelefone()));
        Optional<Cliente> tested = util.getClienteAtualizado(Optional.of(cliente),atualizaClienteNome);
        Assertions.assertNotNull(expected.get().getNome(),tested.get().getNome());
    }


    @Test
    public void deveTestarAtualizarClienteTelefone(){
        Optional<Cliente> expected = Optional.of(getCliente(cliente.getId(), cliente.getNome(), cliente.getCpf(), atualizaClienteTelefone.getTelefone()));
        Optional<Cliente> tested = util.getClienteAtualizado(Optional.of(cliente),atualizaClienteTelefone);
        Assertions.assertEquals(expected.get().getTelefone(),tested.get().getTelefone());
    }


    @Test
    public void deveTestarAtualizarClienteVazio(){
        Optional<Cliente> expected = Optional.empty();
        Optional<Cliente> tested = util.getClienteAtualizado(Optional.empty(),atualizaClienteTelefone);
        Assertions.assertEquals(expected.isPresent(),tested.isPresent());
    }
}
