package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
