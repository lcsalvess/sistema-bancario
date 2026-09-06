package com.lucas.sistemabancario.entity;

import com.lucas.sistemabancario.entity.enums.SituacaoConta;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class ContaCorrente extends Conta{
    public ContaCorrente () {}

    public ContaCorrente(Cliente titular, String numeroConta, BigDecimal saldo, SituacaoConta situacaoConta) {
        super(titular, numeroConta, saldo, situacaoConta);
    }
}
