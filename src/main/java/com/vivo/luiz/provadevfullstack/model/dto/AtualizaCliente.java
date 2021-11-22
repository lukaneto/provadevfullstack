package com.vivo.luiz.provadevfullstack.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaCliente {
    @ApiModelProperty(name = "id",value = "1",dataType = "java.land.Long",example = "1")
    @NotNull
    private Long id;
    @ApiModelProperty(name = "nome",value = "teste",dataType = "java.land.String",example = "teste")
    private String nome;
    @ApiModelProperty(name = "cpf",value = "000.000.00-00",dataType = "java.land.String",example = "000.000.00-00")
    private String cpf;
    @ApiModelProperty(name = "telefone",value = "(00) 0000-0000",dataType = "java.land.String",example = "(00) 0000-0000")
    private String telefone;
}
