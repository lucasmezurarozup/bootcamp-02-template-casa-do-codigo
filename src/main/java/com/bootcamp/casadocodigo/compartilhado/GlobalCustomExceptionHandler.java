package com.bootcamp.casadocodigo.compartilhado;

import com.bootcamp.casadocodigo.livro.LivroNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<Erro> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        new Erro(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> conflity(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 400);
        body.put("path", request.getRequestURI().toString());
        List<Erro> errors = new ArrayList<>();
        errors.add(new Erro(ex.getConstraintName(), "o campo "+ ex.getConstraintName()+ " com o valor fornecido já existe em nosso banco de dados!"));
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<?> livroNotExists(LivroNotFoundException livroNotFoundException, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 404);
        body.put("path", request.getRequestURI().toString());
        List<Erro> errors = new ArrayList<>();
        errors.add(new Erro("id", livroNotFoundException.getMessage()));
        body.put("errors", errors);
        return ResponseEntity.status(404).body(body);
    }

    @ExceptionHandler(PaisNaoRegistradoException.class)
    public ResponseEntity<?> livroNotExists(PaisNaoRegistradoException paisNaoRegistradoException, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 400);
        body.put("path", request.getRequestURI().toString());
        List<Erro> errors = new ArrayList<>();
        errors.add(new Erro("id", paisNaoRegistradoException.getMessage()));
        body.put("errors", errors);
        return ResponseEntity.status(400).body(body);
    }
}
