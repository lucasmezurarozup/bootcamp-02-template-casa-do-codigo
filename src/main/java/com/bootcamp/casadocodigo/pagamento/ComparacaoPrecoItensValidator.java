package com.bootcamp.casadocodigo.pagamento;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class ComparacaoPrecoItensValidator implements Validator {

    private EntityManager entityManager;

    public ComparacaoPrecoItensValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;

        if(novaCompraRequest.comparaPrecoItensComEntrada(entityManager)) {
            errors.rejectValue("pedido.total",null,"preço difere do valor real dos livros");
            return;
        }
    }
}
