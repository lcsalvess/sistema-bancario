package com.lucas.sistemabancario.service;

import com.lucas.sistemabancario.entity.Conta;
import com.lucas.sistemabancario.entity.Transacao;
import com.lucas.sistemabancario.entity.enums.SituacaoConta;
import com.lucas.sistemabancario.entity.enums.TipoTransacao;
import com.lucas.sistemabancario.exception.ContaIsNotActiveException;
import com.lucas.sistemabancario.exception.ValorInvalidoException;
import com.lucas.sistemabancario.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaService contaService;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaService contaService) {
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
    }

    @Transactional
    public void depositar(Long contaId, BigDecimal valor) {
        Conta conta = contaService.buscarPorId(contaId);
        if (conta.getSituacaoConta() != SituacaoConta.ATIVA) {
            throw new ContaIsNotActiveException("A conta informada não está ativa.");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        }
        conta.creditar(valor);
        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor, LocalDateTime.now(), conta);
        transacaoRepository.save(transacao);
    }
}
