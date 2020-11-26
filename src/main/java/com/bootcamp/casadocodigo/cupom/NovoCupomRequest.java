package com.bootcamp.casadocodigo.cupom;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoCupomRequest {

    @NotBlank(message = "código é um campo de preenchimento obrigatório.")
    private String codigo;

    @NotNull(message = "percentual de desconto é um campo de preenchimento obrigatório.")
    @Positive
    private BigDecimal percentualDesconto;

    @NotNull(message = "data de validade é um campo de preenchimento obrigatório.")
    @Future
    private LocalDate dataValidade;

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
    public NovoCupomRequest() {

    }

    public NovoCupomRequest(@NotBlank(message = "código é um campo de preenchimento obrigatório.") String codigo,
                            @NotNull(message = "percentual de desconto é um campo de preenchimento obrigatório.") @Positive BigDecimal percentualDesconto,
                            @NotNull(message = "data de validade é um campo de preenchimento obrigatório.") @Future LocalDate dataValidade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }
}
