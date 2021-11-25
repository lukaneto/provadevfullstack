package com.vivo.luiz.provadevfullstack.service.util;

import com.vivo.luiz.provadevfullstack.exception.ValorEntradaInvalidaException;
import com.vivo.luiz.provadevfullstack.service.util.impl.ValidacaoClienteImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidacaoClienteImplTest {

    private ValidacaoClienteImpl validacaoCliente;

    @BeforeEach
    public void setUp(){
        validacaoCliente= new ValidacaoClienteImpl();
    }

    @Test
    public void deveTestarIsEntradaAtualizaClienteValidaCpfInvalido(){

        Assertions.assertThrows(ValorEntradaInvalidaException.class,
                ()->{validacaoCliente.isCpfClienteValido("1");
                });
    }


    @Test
    public void deveTestarIsEntradaAtualizaClienteValidaCpfValido(){
        Assertions.assertTrue( validacaoCliente.isCpfClienteValido("836.554.440-79"));
    }


    @Test
    public void deveTestarIsEntradaAtualizaClienteValidaCpfNullInvalido(){

        Assertions.assertThrows(ValorEntradaInvalidaException.class,
                ()->{
                    validacaoCliente.isCpfClienteValido(null);
                });
    }

}
