package com.lucas.sistemabancario.service;

import com.lucas.sistemabancario.entity.Conta;
import com.lucas.sistemabancario.entity.Transacao;
import com.lucas.sistemabancario.entity.enums.SituacaoConta;
import com.lucas.sistemabancario.entity.enums.TipoTransacao;
import com.lucas.sistemabancario.exception.ContaIsNotActiveException;
import com.lucas.sistemabancario.exception.ContasIguaisException;
import com.lucas.sistemabancario.exception.SaldoIsNotEnoughException;
import com.lucas.sistemabancario.exception.ValorInvalidoException;
import com.lucas.sistemabancario.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        validarContaAtiva(conta);
        validarValor(valor);
        conta.creditar(valor);
        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor, LocalDateTime.now(), conta);
        transacaoRepository.save(transacao);
    }

    public List<Transacao> listarPorConta(Long contaId) {
        contaService.buscarPorId(contaId);
        return transacaoRepository.findByContaId(contaId);
    }

    @Transactional
    public void sacar(Long contaId, BigDecimal valor) {
        Conta conta = contaService.buscarPorId(contaId);
        validarContaAtiva(conta);
        validarValor(valor);
        validarSaldo(conta, valor);
        conta.debitar(valor);
        Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor, LocalDateTime.now(), conta);
        transacaoRepository.save(transacao);
    }

    @Transactional
    public void transferir(Long contaIdOrigem, Long contaIdDestino, BigDecimal valor) {
        validarContasDiferentes(contaIdOrigem, contaIdDestino);
        Conta contaOrigem = contaService.buscarPorId(contaIdOrigem);
        Conta contaDestino = contaService.buscarPorId(contaIdDestino);
        validarContaAtiva(contaOrigem);
        validarContaAtiva(contaDestino);
        validarValor(valor);
        validarSaldo(contaOrigem, valor);
        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);
        Transacao transacaoContaOrigem = new Transacao(TipoTransacao.TRANSFERENCIA_ENVIADA, valor, LocalDateTime.now(), contaOrigem);
        Transacao transacaoContaDestino = new Transacao(TipoTransacao.TRANSFERENCIA_RECEBIDA, valor, LocalDateTime.now(), contaDestino);
        transacaoRepository.save(transacaoContaOrigem);
        transacaoRepository.save(transacaoContaDestino);
    }

    private void validarContaAtiva(Conta conta) {
        if (conta.getSituacaoConta() != SituacaoConta.ATIVA) {
            throw new ContaIsNotActiveException("A conta informada não está ativa.");
        }
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        }
    }

    private void validarSaldo(Conta conta, BigDecimal valor) {
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new SaldoIsNotEnoughException("O valor informado é maior do que o saldo.");
        }
    }

    private void validarContasDiferentes(Long contaIdOrigem, Long contaIdDestino) {
        if (contaIdOrigem.equals(contaIdDestino)) {
            throw new ContasIguaisException("A conta de origem não pode ser igual à conta de destino.");
        }
    }
}
