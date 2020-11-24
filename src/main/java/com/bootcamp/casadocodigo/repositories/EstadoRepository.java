package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
