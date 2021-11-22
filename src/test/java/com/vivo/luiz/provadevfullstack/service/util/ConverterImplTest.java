package com.vivo.luiz.provadevfullstack.service.util;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.service.util.impl.ConverterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ConverterImplTest {

    private Converter converter;

    private Cliente cliente;
    private ConsultaCliente consultaCliente;

    private Page<Cliente> page;
    private Page<Cliente> pageEmpty;

    private Page<ConsultaCliente> pageConsultaCliente;
    private Page<ConsultaCliente> pageConsultaClienteEmpty;

    private Pageable pageable;

    @BeforeEach
    public void setUp(){

        cliente = getCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");

        consultaCliente = getConsultaCliente(1l,"nome","402.696.190-70","+55 (11) 11111-1111");

        List<Cliente> lista = Arrays.asList(cliente);
        pageable = PageRequest.of(0,20);
        page = new PageImpl<>(lista, PageRequest.of(0,20),1);
        pageEmpty = new PageImpl<>(new ArrayList<>(), PageRequest.of(0,20),0);


        List<ConsultaCliente> listaConsultaCliente = Arrays.asList(consultaCliente);
        pageable = PageRequest.of(0,20);
        pageConsultaCliente = new PageImpl<>(listaConsultaCliente, PageRequest.of(0,20),1);
        pageConsultaClienteEmpty = new PageImpl<>(new ArrayList<>(), PageRequest.of(0,20),0);


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



    @Test
    public void deveTestarConverterEntidadeParaDto(){
        converter = new ConverterImpl<ConsultaCliente, Cliente>();
        ConsultaCliente consultaCliente1 = (ConsultaCliente) converter.entidadeParaDto(cliente,ConsultaCliente.class);
        Assertions.assertEquals(true, consultaCliente1 instanceof ConsultaCliente );
        Assertions.assertEquals(cliente.getCpf(), consultaCliente1.getCpf() );
        Assertions.assertEquals(cliente.getNome(), consultaCliente1.getNome() );
        Assertions.assertEquals(cliente.getTelefone(), consultaCliente1.getTelefone() );

    }


    @Test
    public void deveTestarConverterDtoParaEntidade(){
        converter = new ConverterImpl<ConsultaCliente, Cliente>();
        Cliente cliente1 = (Cliente) converter.dtoParaEntidade(consultaCliente,Cliente.class);
        Assertions.assertEquals(true, cliente1 instanceof Cliente );
        Assertions.assertEquals(consultaCliente.getCpf(), cliente1.getCpf() );
        Assertions.assertEquals(consultaCliente.getNome(), cliente1.getNome() );
        Assertions.assertEquals(consultaCliente.getTelefone(), cliente1.getTelefone() );

    }

    @Test
    public void deveTestarConverterPaginaEntidadeParaDtoPagina(){
        converter = new ConverterImpl<ConsultaCliente, Cliente>();
        Page<ConsultaCliente> consultaClientePage= converter.pageEntidadeParaDto(page,ConsultaCliente.class);
        Assertions.assertEquals(page.getTotalElements(), consultaClientePage.getTotalElements());
        Assertions.assertEquals(page.getContent().get(0).getCpf(), consultaClientePage.getContent().get(0).getCpf());

    }

}
