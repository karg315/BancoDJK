package co.edu.unisabana.bancoDjk.service;

import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import co.edu.unisabana.bancoDjk.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaBancariaService {

    private final CuentaBancariaRepository repository;

    @Autowired
    public CuentaBancariaService(CuentaBancariaRepository repository) {
        this.repository = repository;
    }

    public Optional<CuentaBancaria> obtenerCuentaBancaria(Integer idCuenta) {
        return repository.findById(idCuenta);
    }

    public List<CuentaBancaria> obtenerCuentasBancarias() {
        return repository.findAll();
    }

    public CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria) {
        return repository.save(cuentaBancaria);
    }
}
