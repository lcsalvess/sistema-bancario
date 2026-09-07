package com.lucas.sistemabancario.repository;

import com.lucas.sistemabancario.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
