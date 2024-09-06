package com.example.mscatalogo.service;

import com.example.mscatalogo.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> listar();

    public Category guardar(Category Category);

    public Category buscarPorId(Long id);

    public Category editar(Category Category);

    public void eliminar(Long id);

}
