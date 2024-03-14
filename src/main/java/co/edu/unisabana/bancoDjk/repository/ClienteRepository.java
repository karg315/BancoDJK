package co.edu.unisabana.bancoDjk.repository;

import co.edu.unisabana.bancoDjk.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
