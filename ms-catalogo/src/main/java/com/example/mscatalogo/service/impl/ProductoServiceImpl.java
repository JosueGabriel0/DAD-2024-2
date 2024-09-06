package com.example.mscatalogo.service.impl;

import com.example.mscatalogo.entity.Producto;
import com.example.mscatalogo.repository.ProductoRepository;
import com.example.mscatalogo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto Producto) {
        return productoRepository.save(Producto);
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Producto editar(Producto Producto) {
        return productoRepository.save(Producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
