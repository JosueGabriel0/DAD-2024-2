package com.example.mscatalogo.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String nivel;
    private String code;
    private String estado;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_modificacion;


    @PrePersist
    public void onCreate(){
            fecha_creacion = java.time.LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        fecha_modificacion = java.time.LocalDateTime.now();
    }

}