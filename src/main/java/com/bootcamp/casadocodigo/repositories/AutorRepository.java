package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
