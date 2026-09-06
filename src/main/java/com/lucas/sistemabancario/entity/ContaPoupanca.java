package com.lucas.sistemabancario.entity;

import com.lucas.sistemabancario.entity.enums.SituacaoConta;
import com.lucas.sistemabancario.entity.enums.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ContaPoupanca extends Conta{
    @Column(nullable = false)
    private LocalDate dataUltimoRendimento;

    public ContaPoupanca() {}

    public ContaPoupanca(Cliente titular, String numeroConta, LocalDate dataUltimoRendimento) {
        super(titular, numeroConta, TipoConta.POUPANCA);
        this.dataUltimoRendimento = dataUltimoRendimento;
    }

    public LocalDate getDataUltimoRendimento() {
        return dataUltimoRendimento;
    }

    public void setDataUltimoRendimento(LocalDate dataUltimoRendimento) {
        this.dataUltimoRendimento = dataUltimoRendimento;
    }
}
