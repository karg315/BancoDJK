package co.edu.unisabana.bancoDjk.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero_celular")
    private Integer numeroCelular;

    @Column(name = "numero_identificacion")
    private Integer numeroIdentificacion;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @OneToMany(mappedBy = "cliente  ")
    private List<CuentaBancaria> listaCuentasBancarias;

}
