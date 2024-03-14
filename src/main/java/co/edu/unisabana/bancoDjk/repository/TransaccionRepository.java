package co.edu.unisabana.bancoDjk.repository;

import co.edu.unisabana.bancoDjk.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
}
