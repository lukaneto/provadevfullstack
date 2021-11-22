package com.vivo.luiz.provadevfullstack.service.impl;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.repository.ClienteRepository;
import com.vivo.luiz.provadevfullstack.service.specification.cliente.ClienteQuerySpecification;
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
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    private Cliente cliente;
    private ConsultaCliente consultaCliente;

    private FiltraCliente filtraClienteNome;
    private FiltraCliente filtraClienteCpf;
    private FiltraCliente filtraClienteTelefone;


    private FiltraCliente filtraClienteNomeCpf;

    private FiltraCliente filtraClienteCpfTelefone;

    private FiltraCliente filtraClienteNomeCpfTelefone;

    private FiltraCliente filtraClienteNomeTelefone;

    private Page<Cliente> page;
    private Page<Cliente> pageEmpty;

    private Page<ConsultaCliente> pageConsultaCliente;
    private Page<ConsultaCliente> pageConsultaClienteEmpty;

     private Pageable pageable;
    private CriaCliente criaCliente;

    private AtualizaCliente atualizaCliente;


    private AtualizaCliente atualizaClienteNome;

    private AtualizaCliente atualizaClienteCpf;

    private AtualizaCliente atualizaClienteTelefone;

    @BeforeEach
    public void setUp(){
        cliente = getCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");
        consultaCliente = getConsultaCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");

        filtraClienteNome = getFiltraCliente(cliente.getNome(), null,null);
        filtraClienteCpf = getFiltraCliente(null, cliente.getCpf(),null);
        filtraClienteTelefone =getFiltraCliente(null,null,cliente.getTelefone());
        filtraClienteNomeCpf = getFiltraCliente(cliente.getNome(), cliente.getCpf(), null);
        filtraClienteCpfTelefone =  getFiltraCliente(null, cliente.getCpf(), cliente.getTelefone());
        filtraClienteNomeCpfTelefone = getFiltraCliente(cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
        filtraClienteNomeTelefone = getFiltraCliente(cliente.getNome(), null, cliente.getTelefone());
        List<Cliente> lista = Arrays.asList(cliente);
        pageable = PageRequest.of(0,20);
        page = new PageImpl<>(lista, PageRequest.of(0,20),1);
        pageEmpty = new PageImpl<>(new ArrayList<>(), PageRequest.of(0,20),0);


        List<ConsultaCliente> listaConsultaCliente = Arrays.asList(consultaCliente);
        pageable = PageRequest.of(0,20);
        pageConsultaCliente = new PageImpl<>(listaConsultaCliente, PageRequest.of(0,20),1);
        pageConsultaClienteEmpty = new PageImpl<>(new ArrayList<>(), PageRequest.of(0,20),0);

        criaCliente =CriaCliente.builder().cpf(cliente.getCpf()).nome(cliente.getNome()).telefone(cliente.getTelefone()).build();
        atualizaCliente = AtualizaCliente.builder().id(cliente.getId()).cpf(cliente.getCpf()).nome(cliente.getNome()).telefone(cliente.getTelefone()).build();

        atualizaClienteNome = AtualizaCliente.builder().id(cliente.getId()).nome("teste").build();
        atualizaClienteTelefone = AtualizaCliente.builder().id(cliente.getId()).telefone("+55 (22) 2222-2222").build();
        atualizaClienteCpf = AtualizaCliente.builder().id(cliente.getId()).cpf("402.696.190-70").build();

    }

    private ConsultaCliente getConsultaCliente(Long id, String nome, String cpf, String telefone ){
        ConsultaCliente cliente = new ConsultaCliente();
        cliente.setId(id);
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        return cliente;
    }


    private Cliente getCliente(Long id, String nome, String cpf, String telefone ){
        Cliente cliente = new Cliente();
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
    public void deveTestarConsultaId(){
        Optional<ConsultaCliente> expected = Optional.of(consultaCliente);
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));
        Optional<ConsultaCliente> tested = service.consultarId(cliente.getId());
        Assertions.assertEquals(expected.get().getId(),tested.get().getId());
    }



    @Test
    public void deveTestarConsultaIdNotFound(){
        Optional<ConsultaCliente> expected = Optional.empty();
        Mockito.when(repository.findById(cliente.getId())).thenReturn(Optional.empty());
        Optional<ConsultaCliente> tested = service.consultarId(cliente.getId());
        Assertions.assertEquals(expected.isPresent(),tested.isPresent());
    }




    @Test
    public void deveTestarConsultaCpf(){
        Optional<ConsultaCliente> expected = Optional.of(consultaCliente);
        Mockito.when(repository.findByCpf(cliente.getCpf())).thenReturn(Optional.ofNullable(cliente));
        Optional<ConsultaCliente> tested = service.consultarCpf(cliente.getCpf());
        Assertions.assertEquals(expected.get().getCpf(),tested.get().getCpf());
    }



    @Test
    public void deveTestarConsultaCpfNotFound(){
        Optional<ConsultaCliente> expected = Optional.empty();
        Mockito.when(repository.findByCpf(cliente.getCpf())).thenReturn( Optional.empty());
        Optional<ConsultaCliente> tested = service.consultarCpf(cliente.getCpf());
        Assertions.assertEquals(expected.isPresent(),tested.isPresent());
    }


    @Test
    public void deveTestarFiltraClienteTodosCampos(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteNomeCpfTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }

    @Test
    public void deveTestarFiltraClienteNome(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteNome);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteCpf(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteCpf);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteTelefone(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteNomeECpf(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteNomeCpf);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteNomeETelefone(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteNomeTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClienteCpfETelefone(){
        Page<ConsultaCliente> expected = pageConsultaCliente;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(page);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteCpfTelefone);
        Assertions.assertEquals(expected.getContent().get(0).getId(),tested.getContent().get(0).getId());
    }


    @Test
    public void deveTestarFiltraClientePaginaVazia(){
        Page<ConsultaCliente> expected = pageConsultaClienteEmpty;
        Mockito.when(repository.findAll(any(ClienteQuerySpecification.class),any(Pageable.class))).thenReturn(pageEmpty);
        Page<ConsultaCliente> tested = service.filtraCliente(filtraClienteCpfTelefone);
        Assertions.assertEquals(expected.getContent(),tested.getContent());
    }


    @Test
    public void deveTestarSalvarCliente(){
        Optional<ConsultaCliente> expected = Optional.ofNullable(consultaCliente);
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);
        Optional<ConsultaCliente> tested = service.salvarNovo(criaCliente);
        Assertions.assertEquals(expected.get().getId(),tested.get().getId());
    }


    @Test
    public void deveTestarAtualizarCliente(){
        Optional<ConsultaCliente> expected = Optional.ofNullable(consultaCliente);
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);
        Optional<ConsultaCliente> tested = service.atualizar(atualizaCliente);
        Assertions.assertEquals(expected.get().getNome(),tested.get().getNome());
    }


    @Test
    public void deveTestarAtualizarClienteNome(){
        consultaCliente = getConsultaCliente(cliente.getId(),atualizaClienteNome.getNome(), cliente.getCpf(), cliente.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(consultaCliente);
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);
        Optional<ConsultaCliente> tested = service.atualizar(atualizaClienteNome);
        Assertions.assertEquals(expected.get().getNome(),tested.get().getNome());
    }


    @Test
    public void deveTestarAtualizarClienteCpf(){
        cliente = getCliente(cliente.getId(),cliente.getNome(), atualizaClienteCpf.getCpf(), cliente.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(consultaCliente);
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);
        Optional<ConsultaCliente> tested = service.atualizar(atualizaClienteCpf);
        Assertions.assertEquals(expected.get().getCpf(),tested.get().getCpf());
    }


    @Test
    public void deveTestarAtualizarClienteTelefone(){
        consultaCliente = getConsultaCliente(cliente.getId(),cliente.getNome(), cliente.getCpf(), atualizaClienteTelefone.getTelefone());
        Optional<ConsultaCliente> expected = Optional.ofNullable(consultaCliente);
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);
        Optional<ConsultaCliente> tested = service.atualizar(atualizaClienteTelefone);
        Assertions.assertEquals(expected.get().getTelefone(),tested.get().getTelefone());
    }


    @Test
    public void deveTestarAtualizarClienteNaoAtualizado(){
        Optional<ConsultaCliente> expected = Optional.empty();
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<ConsultaCliente> tested = service.atualizar(atualizaClienteTelefone);
        Assertions.assertEquals(expected.isPresent(),tested.isPresent());
    }
}
