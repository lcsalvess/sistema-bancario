package com.lucas.sistemabancario.repository;

import com.lucas.sistemabancario.entity.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
    boolean existsByTitularId(Long titularId);
}
