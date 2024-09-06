package upeu.edu.pe.mspedido.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    private long id;
    private String nombre;
    private String descripcion;
    private String nivel;
    private String code;
    private String estado;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_modificacion;
}
