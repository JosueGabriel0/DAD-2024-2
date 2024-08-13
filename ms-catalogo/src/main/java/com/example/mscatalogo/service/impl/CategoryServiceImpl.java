package com.example.mscatalogo.service.impl;

import com.example.mscatalogo.entity.Category;
import com.example.mscatalogo.repository.CategoryRepository;
import com.example.mscatalogo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository CategoryRepository;

    @Override
    public List<Category> listar() {
        return CategoryRepository.findAll();
    }

    @Override
    public Category guardar(Category Category) {
        return CategoryRepository.save(Category);
    }

    @Override
    public Category buscarPorId(Integer id) {
        return CategoryRepository.findById(id).get();
    }

    @Override
    public Category editar(Category Category) {
        return CategoryRepository.save(Category);
    }

    @Override
    public void eliminar(Integer id) {
        CategoryRepository.deleteById(id);
    }
}