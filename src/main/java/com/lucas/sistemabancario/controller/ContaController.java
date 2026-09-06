package com.lucas.sistemabancario.controller;

import com.lucas.sistemabancario.entity.Conta;
import com.lucas.sistemabancario.entity.ContaCorrente;
import com.lucas.sistemabancario.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<Conta> listar() {
        return contaService.listar();
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    @PostMapping("/corrente/{clienteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaCorrente criarContaCorrente(@PathVariable Long clienteId) {
        return contaService.criarContaCorrente(clienteId);
    }
}
