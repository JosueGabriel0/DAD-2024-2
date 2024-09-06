package com.example.mscatalogo.service;

import com.example.mscatalogo.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listar();

    public Producto guardar(Producto producto);

    public Producto buscarPorId(Long id);

    public Producto editar(Producto producto);

    public void eliminar(Long id);
}
