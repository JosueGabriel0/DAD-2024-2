package upeu.edu.pe.mspedido.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private int productoId;

    public PedidoDetalle(){
        this.cantidad = (double) 0;
        this.precio = (double) 0;
    }

}
