package upeu.edu.pe.mspedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import upeu.edu.pe.mspedido.dto.Producto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data

public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double cantidad;
    private Double precio;
    private long productoId;

    @Transient
    private Producto producto;

    public PedidoDetalle(){
        this.cantidad = (double) 0;
        this.precio = (double) 0;
    }

}
