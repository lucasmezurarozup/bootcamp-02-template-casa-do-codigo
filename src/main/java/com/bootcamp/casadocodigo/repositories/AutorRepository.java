package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
