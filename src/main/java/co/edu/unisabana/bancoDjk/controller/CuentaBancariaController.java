package co.edu.unisabana.bancoDjk.controller;

import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import co.edu.unisabana.bancoDjk.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CuentaBancariaController {

    private final CuentaBancariaService service;

    @Autowired
    public CuentaBancariaController(CuentaBancariaService service) {
        this.service = service;
    }

    @GetMapping("/cuentas")
    public List<CuentaBancaria> obtenerCuentasBancarias(){return service.obtenerCuentasBancarias();}

    @GetMapping("/cuenta/{id}")
    public ResponseEntity<CuentaBancaria> obtenerCuentaPorId(@PathVariable Integer id) {
        Optional<CuentaBancaria> cuentaOptional = service.obtenerCuentaBancaria(id);
        return cuentaOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cuentas")
    public ResponseEntity<CuentaBancaria> crearCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria) {
        CuentaBancaria nuevaCuenta = service.crearCuentaBancaria(cuentaBancaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }

    @PutMapping("/cuenta/{id}")
    public ResponseEntity<CuentaBancaria> actualizarCuentaBancaria(@PathVariable Integer idCuenta, @RequestBody CuentaBancaria cuentaActualizada) {
        CuentaBancaria cuenta = service.actualizarCuentaBancaria(idCuenta, cuentaActualizada);
        return ResponseEntity.ok(cuenta);
    }

    @DeleteMapping("/cuenta/{id}")
    public ResponseEntity<Void> eliminarCuentaBancaria(@PathVariable Integer idCuenta) {
        service.eliminarCuentaBancaria(idCuenta);
        return ResponseEntity.noContent().build();
    }
}
