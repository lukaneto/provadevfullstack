package com.vivo.luiz.provadevfullstack.controller;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteService service;

    private ConsultaCliente cliente;

    private FiltraCliente filtraClienteNome;
    private FiltraCliente filtraClienteCpf;
    private FiltraCliente filtraClienteTelefone;


    private FiltraCliente filtraClienteNomeCpf;

    private FiltraCliente filtraClienteCpfTelefone;

    private FiltraCliente filtraClienteNomeCpfTelefone;

    private FiltraCliente filtraClienteNomeTelefone;

    private Page<ConsultaCliente> page;
    private Page<ConsultaCliente> pageEmpty;


    private CriaCliente criaCliente;

    private AtualizaCliente atualizaCliente;


    private AtualizaCliente atualizaClienteNome;

    private AtualizaCliente atualizaClienteCpf;

    private AtualizaCliente atualizaClienteTelefone;

    @BeforeEach
    public void setUp(){
        cliente = getCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");

        filtraClienteNome = getFiltraCliente(cliente.getNome(), null,null);
        filtraClienteCpf = getFiltraCliente(null, cliente.getCpf(),null);
        filtraClienteTelefone =getFiltraCliente(null,null,cliente.getTelefone());
        filtraClienteNomeCpf = getFiltraCliente(cliente.getNome(), cliente.getCpf(), null);
        filtraClienteCpfTelefone =  getFiltraCliente(null, cliente.getCpf(), cliente.getTelefone());
        filtraClienteNomeCpfTelefone = getFiltraCliente(cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
        filtraClienteNomeTelefone = getFiltraCliente(cliente.getNome(), null, cliente.getTelefone());
        List<ConsultaCliente> lista = Arrays.asList(cliente);
        page = new PageImpl<>(lista, PageRequest.of(0,20),1);
        pageEmpty = new PageImpl<>(new ArrayList<>(), PageRequest.of(0,20),0);

        criaCliente =CriaCliente.builder().cpf(cliente.getCpf()).nome(cliente.getNome()).telefone(cliente.getTelefone()).build();
        atualizaCliente = AtualizaCliente.builder().id(cliente.getId()).cpf(cliente.getCpf()).nome(cliente.getNome()).telefone(cliente.getTelefone()).build();

        atualizaClienteNome = AtualizaCliente.builder().nome("teste").build();
        atualizaClienteTelefone = AtualizaCliente.builder().telefone("+55 (22) 2222-2222").build();
        atualizaClienteCpf = AtualizaCliente.builder().telefone("402.696.190-70").build();

    }

    private ConsultaCliente getCliente(Long id, String nome, String cpf, String telefone ){
        ConsultaCliente cliente = new ConsultaCliente();
        cliente.setId(id);
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        return cliente;
    }

    private FiltraCliente getFiltraCliente(String nome, String cpf,String telefone){
        FiltraCliente filtraCliente =  new FiltraCliente();
        filtraCliente.setCpf(cpf);
        filtraCliente.setNome(nome);
        filtraCliente.setTelefone(telefone);
        return filtraCliente;
    }


    @Test
    public void deveTestarConsultaIdOk(){
        Optional<ConsultaCliente> expected = Optional.of(cliente);
        Mockito.when(service.consultarId(anyLong())).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.consultaId(cliente.getId());
        Assertions.assertEquals(expected.get().getId(),tested.getBody().getId());
    }



    @Test
    public void deveTestarConsultaIdNotFound(){
        Optional<ConsultaCliente> expected = Optional.empty();
        Mockito.when(service.consultarId(cliente.getId())).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.consultaId(cliente.getId());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),tested.getStatusCode().value());
    }


    @Test
    public void deveTestarConsultaCpfOk(){
        Optional<ConsultaCliente> expected = Optional.of(cliente);
        Mockito.when(service.consultarCpf(cliente.getCpf())).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.consultaCpf(cliente.getCpf());
        Assertions.assertEquals(expected.get().getCpf(),tested.getBody().getCpf());
    }



    @Test
    public void deveTestarConsultaCpfNotFound(){
        Optional<ConsultaCliente> expected = Optional.empty();
        Mockito.when(service.consultarCpf(cliente.getCpf())).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.consultaCpf(cliente.getCpf());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),tested.getStatusCode().value());
    }


    @Test
    public void deveTestarFiltraClienteTodosCampos(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteNomeCpfTelefone)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteNomeCpfTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }

    @Test
    public void deveTestarFiltraClienteNome(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteNome)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteNome);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteCpf(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteCpf)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteCpf);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteTelefone(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteTelefone)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteNomeECpf(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteNomeCpf)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteNomeCpf);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteNomeETelefone(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteNomeTelefone)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteNomeTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteCpfETelefone(){
        Page<ConsultaCliente> expected = page;
        Mockito.when(service.filtraCliente(filtraClienteCpfTelefone)).thenReturn(page);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteCpfTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getBody().getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClientePaginaVazia(){
        Page<ConsultaCliente> expected = pageEmpty;
        Mockito.when(service.filtraCliente(any(FiltraCliente.class))).thenReturn(pageEmpty);
        ResponseEntity<Page<ConsultaCliente>> tested = controller.filtra(filtraClienteCpfTelefone);
        Assertions.assertEquals(expected.getContent(),tested.getBody().getContent());
    }


    @Test
    public void deveTestarSalvarCliente(){
        Optional<ConsultaCliente> expected = Optional.ofNullable(cliente);
        Mockito.when(service.salvarNovo(any(CriaCliente.class))).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.salva(criaCliente);
        Assertions.assertEquals(expected.get().getId(),tested.getBody().getId());
    }


    @Test
    public void deveTestarAtualizarCliente(){
        Optional<ConsultaCliente> expected = Optional.ofNullable(cliente);
        Mockito.when(service.atualizar(any(AtualizaCliente.class))).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.atualizar(atualizaCliente);
        Assertions.assertEquals(expected.get().getNome(),tested.getBody().getNome());
    }
    @Test
    public void deveTestarAtualizarClienteNome(){
        cliente = getCliente(cliente.getId(),atualizaClienteNome.getNome(), cliente.getCpf(), cliente.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(cliente);
        Mockito.when(service.atualizar(any(AtualizaCliente.class))).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.atualizar(atualizaClienteNome);
        Assertions.assertEquals(expected.get().getNome(),tested.getBody().getNome());
    }


    @Test
    public void deveTestarAtualizarClienteCpf(){
        cliente = getCliente(cliente.getId(),cliente.getNome(), atualizaClienteCpf.getCpf(), cliente.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(cliente);
        Mockito.when(service.atualizar(any(AtualizaCliente.class))).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.atualizar(atualizaClienteCpf);
        Assertions.assertEquals(expected.get().getCpf(),tested.getBody().getCpf());
    }


    @Test
    public void deveTestarAtualizarClienteTelefone(){
        cliente = getCliente(cliente.getId(),cliente.getNome(), cliente.getCpf(), atualizaClienteTelefone.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(cliente);
        Mockito.when(service.atualizar(any(AtualizaCliente.class))).thenReturn(expected);
        ResponseEntity<ConsultaCliente> tested = controller.atualizar(atualizaClienteTelefone);
        Assertions.assertEquals(expected.get().getTelefone(),tested.getBody().getTelefone());
    }
}
