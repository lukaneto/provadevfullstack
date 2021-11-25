package com.vivo.luiz.provadevfullstack.model;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    @Column(unique=true)
    @CPF(message = "cpf deve ser valido")
    private String cpf;
    private String telefone;
}
