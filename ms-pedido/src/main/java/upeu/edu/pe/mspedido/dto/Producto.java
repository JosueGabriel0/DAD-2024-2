package upeu.edu.pe.mspedido.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class Producto {

    private long id;
    private String nombre;
    private Category category;
}
