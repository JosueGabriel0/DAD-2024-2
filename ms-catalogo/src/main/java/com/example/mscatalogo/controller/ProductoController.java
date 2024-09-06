package com.example.mscatalogo.controller;

import com.example.mscatalogo.entity.Producto;
import com.example.mscatalogo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto Producto) {
        return ResponseEntity.ok(productoService.guardar(Producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable(required = true) Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable(required = true) Long id,
                                           @RequestBody Producto Producto) {
        Producto.setId(id);
        return ResponseEntity.ok(productoService.editar(Producto));

    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Long id) {
        productoService.eliminar(id);
        return "Eliminacion correcta";
    }

}
