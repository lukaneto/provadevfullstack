package com.vivo.luiz.provadevfullstack.model.dto;


import br.com.caelum.stella.bean.validation.CPF;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriaCliente {
    @ApiModelProperty(name = "nome",value = "teste",dataType = "java.land.String",example = "teste")
    @NotNull
    private String nome;
    @ApiModelProperty(name = "cpf",value = "000.000.00-00",dataType = "java.land.String",example = "000.000.00-00")
    @NotNull
    @CPF(message = "cpf deve ser valido")
    private String cpf;
    @ApiModelProperty(name = "telefone",value = "(00) 0000-0000",dataType = "java.land.String",example = "(00) 0000-0000")
    @NotNull
    private String telefone;
}
