package co.edu.unisabana.bancoDjk.service;

import co.edu.unisabana.bancoDjk.entity.CuentaBancaria;
import co.edu.unisabana.bancoDjk.entity.Transaccion;
import co.edu.unisabana.bancoDjk.repository.CuentaBancariaRepository;
import co.edu.unisabana.bancoDjk.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    private final CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    public TransaccionService(TransaccionRepository transaccionRepository, CuentaBancariaRepository cuentaBancariaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    public List<Transaccion> obtenerTransacciones() {return transaccionRepository.findAll();}

    public Optional<Transaccion> obtenerTransaccion(Integer idTransaccion) {
        return transaccionRepository.findById(idTransaccion);
    }

    public Transaccion crearTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Transactional
    public void realizarTransferencia(Transaccion transaccion) {
        CuentaBancaria cuentaOrigen = cuentaBancariaRepository.findById(transaccion.getIdCuentaOrigen())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));

        CuentaBancaria cuentaDestino = cuentaBancariaRepository.findById(transaccion.getIdCuentaDestino())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        BigDecimal monto = transaccion.getCantidadTransaccion();

        if (0 > cuentaOrigen.getSaldo().compareTo(monto)) {
            throw new IllegalArgumentException("Fondos insuficientes en la cuenta de origen");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

        cuentaBancariaRepository.save(cuentaOrigen);
        cuentaBancariaRepository.save(cuentaDestino);

        transaccion.setFecha(LocalDate.now());
        transaccionRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(Integer idTransaccion, Transaccion transaccionActualizada) {
        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(() -> new IllegalArgumentException("Transacción no encontrada con ID: " + idTransaccion));

        transaccion.setTipoTransaccion(transaccionActualizada.getTipoTransaccion());
        transaccion.setCantidadTransaccion(transaccionActualizada.getCantidadTransaccion());
        transaccion.setIdCuentaOrigen(transaccionActualizada.getIdCuentaOrigen());
        transaccion.setIdCuentaDestino(transaccionActualizada.getIdCuentaDestino());
        transaccion.setFecha(transaccionActualizada.getFecha());

        return transaccionRepository.save(transaccion);
    }

    public void eliminarTransaccion(Integer idTransaccion) {
        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(() -> new IllegalArgumentException("Transacción no encontrada con ID: " + idTransaccion));
        transaccionRepository.delete(transaccion);
    }
}
