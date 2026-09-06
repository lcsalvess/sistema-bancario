package com.lucas.sistemabancario.repository;

import com.lucas.sistemabancario.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query(value = "SELECT nextval('numero_conta_sequence')", nativeQuery = true)
    Long gerarProximoNumeroConta();
}
