package api.erp.solarerp.web.controller;

import api.erp.solarerp.domain.service.ClienteServiceImpl;
import api.erp.solarerp.web.dto.ClientePfDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteServiceImpl clienteServiceImpl;

    public ClienteController(ClienteServiceImpl clienteServiceImpl) {
        this.clienteServiceImpl = clienteServiceImpl;
    }

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<Void> cadastrarPessoaFisica(
            @Valid @RequestBody ClientePfDTO cliente,
            UriComponentsBuilder ucb
    ) {
        Long id = clienteServiceImpl.cadastrarClientePF(cliente);

        URI uri = ucb.path("/cliente/{id}")
                     .buildAndExpand(id)
                     .toUri();

        return ResponseEntity.created(uri).build();
    }
}
