package com.bootcamp.casadocodigo.cupom;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "cupons",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"codigo"}, name = "codigoCupom")
        }
)
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal percentualDesconto;

    @NotNull
    @Future
    private LocalDate dataValidade;


    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    @Deprecated
    public Cupom() {

    }

    public Cupom(
                 @NotNull String codigo,
                 @NotNull @Positive BigDecimal percentualDesconto,
                 @NotNull @Future LocalDate dataValidade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }
}
