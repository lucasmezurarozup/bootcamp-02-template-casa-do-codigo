package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
