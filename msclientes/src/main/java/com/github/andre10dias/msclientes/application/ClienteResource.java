package com.github.andre10dias.msclientes.application;

import com.github.andre10dias.msclientes.application.representation.ClienteSaveRequest;
import com.github.andre10dias.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping
    public String status() {
        log.info("Status do microserviço de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        clienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Cliente> dadosCliente(@RequestParam String cpf) {
        return clienteService.getByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
