package com.lucas.sistemabancario.service;

import com.lucas.sistemabancario.entity.Cliente;
import com.lucas.sistemabancario.entity.Endereco;
import com.lucas.sistemabancario.repository.ClienteRepository;
import com.lucas.sistemabancario.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        Endereco endereco = enderecoRepository.save(cliente.getEndereco());
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
}
