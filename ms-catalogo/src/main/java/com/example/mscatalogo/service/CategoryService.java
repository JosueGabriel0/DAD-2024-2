package com.example.mscatalogo.service;

import com.example.mscatalogo.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> listar();

    public Category guardar(Category Category);

    public Category buscarPorId(Integer id);

    public Category editar(Category Category);

    public void eliminar(Integer id);

}
