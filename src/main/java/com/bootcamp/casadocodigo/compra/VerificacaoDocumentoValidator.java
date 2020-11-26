package com.bootcamp.casadocodigo.compra;

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
