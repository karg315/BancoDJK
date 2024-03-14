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

    @GetMapping("/cuentas-bancarias")
    public List<CuentaBancaria> obtenerCuentasBancarias(){return service.obtenerCuentasBancarias();}

    @GetMapping("/cuentas-bancarias/{id}")
    public ResponseEntity<CuentaBancaria> obtenerCuentaPorId(@PathVariable Integer id) {
        Optional<CuentaBancaria> cuentaOptional = service.obtenerCuentaBancaria(id);
        return cuentaOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("cuentas-bancarias")
    public ResponseEntity<CuentaBancaria> crearCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria) {
        CuentaBancaria nuevaCuenta = service.crearCuentaBancaria(cuentaBancaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }
}
