package co.edu.unisabana.bancoDjk.service;

import co.edu.unisabana.bancoDjk.entity.Transaccion;
import co.edu.unisabana.bancoDjk.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository repository;

    @Autowired
    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    public List<Transaccion> obtenerTransacciones() {return repository.findAll();}

    public Optional<Transaccion> obtenerTransaccion(Integer idTransaccion) {
        return repository.findById(idTransaccion);
    }

    public Transaccion crearTransaccion(Transaccion transaccion) {
        return repository.save(transaccion);
    }
}
