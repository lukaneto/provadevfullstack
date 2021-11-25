package com.vivo.luiz.provadevfullstack.controller;

import com.vivo.luiz.provadevfullstack.model.dto.AtualizaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.ConsultaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.CriaCliente;
import com.vivo.luiz.provadevfullstack.model.dto.FiltraCliente;
import com.vivo.luiz.provadevfullstack.model.Cliente;
import com.vivo.luiz.provadevfullstack.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(name = "/cliente/")
@RequiredArgsConstructor
@Api("/cliente/")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    @GetMapping(value = "v1/{id}")
    @ApiOperation(value = "consulta id", response = Cliente.class)
    public ResponseEntity<ConsultaCliente> consultaId(@PathVariable Long id){
        Optional<ConsultaCliente> clienteOptional = clienteService.consultarId(id);
        return clienteOptional.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ConsultaCliente(), HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = "v1/cpf/{cpf}")
    @ApiOperation(value = "consulta cpf", response = Cliente.class)
    public ResponseEntity<ConsultaCliente> consultaCpf(@PathVariable String cpf){
        Optional<ConsultaCliente> clienteOptional = clienteService.consultarCpf(cpf);
        return clienteOptional.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ConsultaCliente(), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "v1/filtra")
    @ApiOperation(value = "filtra clientes",  response = Cliente.class, responseContainer = "Page")
    public ResponseEntity< Page<ConsultaCliente>> filtra( @ModelAttribute @Valid FiltraCliente  filtraCliente){
        Page<ConsultaCliente> page = clienteService.filtraCliente(filtraCliente);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @PostMapping (value = "v1/salvar")
    @ApiOperation(value = "salvar clientes", response = Cliente.class)
    public ResponseEntity<ConsultaCliente> salva( @RequestBody @Valid CriaCliente cliente){
        Optional<ConsultaCliente> clienteOptional = clienteService.salvarNovo(cliente);
        return clienteOptional.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(new ConsultaCliente(), HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping (value = "v1/atualizar")
    @ApiOperation(value = "atualizar clientes", response = Cliente.class)
    public ResponseEntity<ConsultaCliente> atualizar( @RequestBody  @Valid AtualizaCliente cliente){
        Optional<ConsultaCliente> clienteOptional = clienteService.atualizar(cliente);
        return clienteOptional.map(value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(new ConsultaCliente(), HttpStatus.NOT_FOUND));
    }
}
