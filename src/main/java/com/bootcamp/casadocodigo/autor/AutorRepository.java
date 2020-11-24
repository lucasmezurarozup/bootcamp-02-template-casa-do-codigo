package com.bootcamp.casadocodigo.autor;

import com.bootcamp.casadocodigo.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
