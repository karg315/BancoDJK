package co.edu.unisabana.bancoDjk.service;

import co.edu.unisabana.bancoDjk.entity.Cliente;
import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import co.edu.unisabana.bancoDjk.repository.ClienteRepository;
import co.edu.unisabana.bancoDjk.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, CuentaBancariaRepository cuentaBancariaRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    public List<Cliente> obtenerClientes() {return clienteRepository.findAll();}

    public Optional<Cliente> obtenerCliente(Integer idCliente) { return clienteRepository.findById(idCliente); }

    public BigDecimal obtenerSaldoTotalCliente(Integer idCliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.obtenerCuentasPorCliente(cliente);

            BigDecimal saldoTotal = cuentasBancarias.stream()
                    .map(CuentaBancaria::getSaldo)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return saldoTotal;
        } else {
            throw new NoSuchElementException("Cliente no encontrado");
        }
    }

    public Cliente crearCliente(Cliente cliente) { return clienteRepository.save(cliente);}

    public Cliente actualizarCliente(Integer idCliente, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + idCliente));

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setNumeroCelular(clienteActualizado.getNumeroCelular());
        cliente.setNumeroIdentificacion(clienteActualizado.getNumeroIdentificacion());
        cliente.setTipoDocumento(clienteActualizado.getTipoDocumento());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + idCliente));
        clienteRepository.delete(cliente);
    }
}
