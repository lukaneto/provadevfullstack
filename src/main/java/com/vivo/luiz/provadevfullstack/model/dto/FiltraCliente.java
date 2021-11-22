package com.vivo.luiz.provadevfullstack.model.dto;

import br.com.caelum.stella.bean.validation.CPF;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltraCliente {
    @ApiModelProperty(name = "nome",value = "teste",dataType = "java.land.String",example = "teste")
    private String nome;
    @ApiModelProperty(name = "cpf",value = "000.000.00-00",dataType = "java.land.String",example = "000.000.00-00")
    @CPF(message="cpf inválido")
    private String cpf;
    @ApiModelProperty(name = "telefone",value = "(00) 0000-0000",dataType = "java.land.String",example = "(00) 0000-0000")
    private String telefone;
    @ApiModelProperty(name = "pagina",value = "0",dataType = "java.land.Integer",example = "0")
    private Integer pagina;
    @ApiModelProperty(name = "tamanho",value = "20",dataType = "java.land.Integer",example = "20")
    private Integer tamanho;
}
