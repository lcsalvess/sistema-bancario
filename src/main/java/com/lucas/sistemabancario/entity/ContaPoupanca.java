package com.lucas.sistemabancario.entity;

import com.lucas.sistemabancario.entity.enums.SituacaoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ContaPoupanca extends Conta{
    @Column(nullable = false)
    private LocalDate dataUltimoRendimento;

    public ContaPoupanca() {}

    public ContaPoupanca(Cliente titular, String numeroConta, BigDecimal saldo, SituacaoConta situacaoConta, LocalDate dataUltimoRendimento) {
        super(titular, numeroConta, saldo, situacaoConta);
        this.dataUltimoRendimento = dataUltimoRendimento;
    }

    public LocalDate getDataUltimoRendimento() {
        return dataUltimoRendimento;
    }

    public void setDataUltimoRendimento(LocalDate dataUltimoRendimento) {
        this.dataUltimoRendimento = dataUltimoRendimento;
    }
}
