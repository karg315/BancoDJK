package co.edu.unisabana.bancoDjk.service;

import co.edu.unisabana.bancoDjk.entity.Cliente;
import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import co.edu.unisabana.bancoDjk.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> obtenerClientes() {return repository.findAll();}

    public Optional<Cliente> obtenerCliente(Integer idCliente) { return repository.findById(idCliente); }

    public Cliente crearCliente(Cliente cliente) { return repository.save(cliente);};
}
