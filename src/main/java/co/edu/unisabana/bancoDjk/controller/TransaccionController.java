package co.edu.unisabana.bancoDjk.controller;

import co.edu.unisabana.bancoDjk.entity.Transaccion;
import co.edu.unisabana.bancoDjk.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransaccionController {

    private final TransaccionService service;

    @Autowired
    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    @GetMapping("/transacciones")
    public ResponseEntity<List<Transaccion>> obtenerTransacciones() {
        List<Transaccion> transacciones = service.obtenerTransacciones();
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/transaccion/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccion(@PathVariable Integer id) {
        Optional<Transaccion> transaccionOptional = service.obtenerTransaccion(id);
        return transaccionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transacciones")
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion) {
        Transaccion nuevaTransaccion = service.crearTransaccion(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTransaccion);
    }
}
