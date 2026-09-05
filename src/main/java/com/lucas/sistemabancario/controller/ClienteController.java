package com.lucas.sistemabancario.controller;

import com.lucas.sistemabancario.entity.Cliente;
import com.lucas.sistemabancario.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }
}
