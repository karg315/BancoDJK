package co.edu.unisabana.bancoDjk.repository;

import co.edu.unisabana.bancoDjk.entity.Cliente;
import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaBancariaRepository extends JpaRepository <CuentaBancaria, Integer>{
    List<CuentaBancaria> obtenerCuentasPorCliente(Cliente cliente);
}
