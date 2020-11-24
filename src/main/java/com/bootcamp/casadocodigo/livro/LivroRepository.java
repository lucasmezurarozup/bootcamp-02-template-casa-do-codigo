package com.bootcamp.casadocodigo.livro;

import com.bootcamp.casadocodigo.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
