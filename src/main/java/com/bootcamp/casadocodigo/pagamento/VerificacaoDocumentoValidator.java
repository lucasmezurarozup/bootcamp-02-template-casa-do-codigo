package com.bootcamp.casadocodigo.pagamento;

import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificacaoDocumentoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;
        if(!novaCompraRequest.validaDocumento()) {
            errors.rejectValue("documento", null, "o documento deve estar preenchido corretamente.");
            return;
        }

    }
}
