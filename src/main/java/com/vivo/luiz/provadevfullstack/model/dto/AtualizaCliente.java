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
public class AtualizaCliente {
    @ApiModelProperty(name = "id",value = "1",dataType = "java.lang.Long",example = "1")
    @NotNull(message = "O id é obrigatorio para atualizar")
    private Long id;
    @ApiModelProperty(name = "nome",value = "teste",dataType = "java.lang.String",example = "teste")
    private String nome;
    @CPF(message = "cpf deve ser valido")
    @ApiModelProperty(name = "cpf",value = "000.000.00-00",dataType = "java.lang.String",example = "000.000.00-00")
    private String cpf;
    @ApiModelProperty(name = "telefone",value = "(00) 0000-0000",dataType = "java.lang.String",example = "(00) 0000-0000")
    private String telefone;
}
