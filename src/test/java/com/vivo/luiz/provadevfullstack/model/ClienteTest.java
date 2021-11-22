package com.vivo.luiz.provadevfullstack.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import pl.pojo.tester.api.assertion.Method;


@SpringBootTest
public class ClienteTest {
    @Test
    public void deveTestarGettersAndSetters(){
        assertPojoMethodsFor(Cliente.class).testing(Method.GETTER,Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
