package com.lucas.sistemabancario.entity;

import com.lucas.sistemabancario.entity.enums.TipoTransacao;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipoTransacao;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;
    @Column(nullable = false)
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Conta conta;

    public Transacao() {}

    public Transacao(TipoTransacao tipoTransacao, BigDecimal valor, LocalDateTime dataHora, Conta conta) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dataHora = dataHora;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Conta getConta() {
        return conta;
    }
}
