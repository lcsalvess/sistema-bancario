package com.lucas.sistemabancario.controller;

import com.lucas.sistemabancario.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/deposito/{contaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void depositar(@PathVariable Long contaId, @RequestParam BigDecimal valor) {
        transacaoService.depositar(contaId, valor);
    }
}
