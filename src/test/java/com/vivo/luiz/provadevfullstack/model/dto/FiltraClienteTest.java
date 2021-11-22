package com.vivo.luiz.provadevfullstack.model.dto;

import com.vivo.luiz.provadevfullstack.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@SpringBootTest
public class FiltraClienteTest {
    @Test
    public void deveTestarGettersAndSetters(){
        assertPojoMethodsFor(FiltraCliente.class).testing(Method.GETTER,Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
