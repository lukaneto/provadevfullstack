package com.vivo.luiz.provadevfullstack.model.dto;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@SpringBootTest
public class ConsultaClienteTest {

    @Test
    public void deveTestarGettersAndSetters(){
        assertPojoMethodsFor(ConsultaCliente.class).testing(Method.GETTER,Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
