package co.edu.unisabana.bancoDjk.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaccion")
@Data
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @Column(name = "tipo_transaccion")
    private String tipoTransaccion;

    @Column(name = "cantidad_transaccion", precision = 10, scale = 2)
    private BigDecimal cantidadTransaccion;

    @Column(name = "id_cuenta_origen")
    private Integer idCuentaOrigen;

    @Column(name = "id_cuenta_destino")
    private Integer idCuentaDestino;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_origen", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
    private CuentaBancaria cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_destino", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
    private CuentaBancaria cuentaDestino;

}
