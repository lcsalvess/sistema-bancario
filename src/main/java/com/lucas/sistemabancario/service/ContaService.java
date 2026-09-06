package com.lucas.sistemabancario.service;

import com.lucas.sistemabancario.entity.Cliente;
import com.lucas.sistemabancario.entity.Conta;
import com.lucas.sistemabancario.entity.ContaCorrente;
import com.lucas.sistemabancario.entity.ContaPoupanca;
import com.lucas.sistemabancario.exception.ContaNotFoundException;
import com.lucas.sistemabancario.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final ClienteService clienteService;

    public ContaService(ContaRepository contaRepository, ClienteService clienteService) {
        this.contaRepository = contaRepository;
        this.clienteService = clienteService;
    }

    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id){
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));
    }

    private String gerarNumeroConta() {
        Long proximoNumero = contaRepository.gerarProximoNumeroConta();
        String numeroBase = String.format("%05d", proximoNumero);
        int digito = calcularDigitoVerificador(numeroBase);
        return numeroBase + digito;
    }

    private int calcularDigitoVerificador(String numeroBase) {
        int soma = 0;
        int[] pesos = {5, 4, 3, 2, 1};
        for (int i = 0; i <numeroBase.length(); i++) {
            int digito = Character.getNumericValue(numeroBase.charAt(i));
            soma += digito * pesos[i];
        }
        return soma % 10;
    }

    public ContaCorrente criarContaCorrente(Long clienteId){
        Cliente cliente = clienteService.buscarPorId(clienteId);
        String numeroConta = gerarNumeroConta();
        ContaCorrente contaCorrente = new ContaCorrente(cliente, numeroConta);
        return contaRepository.save(contaCorrente);
    }

    public ContaPoupanca criarContaPoupanca(Long clienteId){
        Cliente cliente = clienteService.buscarPorId(clienteId);
        String numeroConta = gerarNumeroConta();
        LocalDate dataUltimoRendimento = LocalDate.now();
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente, numeroConta, dataUltimoRendimento);
        return contaRepository.save(contaPoupanca);
    }
}
