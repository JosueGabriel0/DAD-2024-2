package upeu.edu.pe.mspedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.mspedido.entity.Pedido;
import upeu.edu.pe.mspedido.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> guardarPedidoResponseEntity(@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.guardarPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidoResponseEntity(){
        return ResponseEntity.ok(pedidoService.listarPedido());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorIdResponseEntity(@PathVariable(required = true) Long id){
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> editarPedidoResponseEntity(@PathVariable (required = true) Long id, @RequestBody Pedido pedido){
        pedido.setId(id);
        return ResponseEntity.ok(pedidoService.editarPedido(pedido));
    }

    @DeleteMapping("/{id}")
    public String eliminarPedidoResponseEntity(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
        return "Pedido eliminada";
    }
}
