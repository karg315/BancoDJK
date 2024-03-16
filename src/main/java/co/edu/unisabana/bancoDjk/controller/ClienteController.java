package co.edu.unisabana.bancoDjk.controller;

import co.edu.unisabana.bancoDjk.entity.Cliente;
import co.edu.unisabana.bancoDjk.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){return service.obtenerClientes();}

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id) {
        Optional<Cliente> clienteOptional = service.obtenerCliente(id);
        return clienteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{id}/saldo")
    public ResponseEntity<BigDecimal> obtenerSaldoTotalCliente(@PathVariable Integer id) {
        BigDecimal saldoTotal = service.obtenerSaldoTotalCliente(id);
        return ResponseEntity.ok(saldoTotal);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = service.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer clienteId, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = service.actualizarCliente(clienteId, clienteActualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer idCliente) {
        service.eliminarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}
