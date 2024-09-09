package upeu.edu.pe.mspedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.mspedido.dto.Cliente;
import upeu.edu.pe.mspedido.dto.Producto;
import upeu.edu.pe.mspedido.entity.Pedido;
import upeu.edu.pe.mspedido.entity.PedidoDetalle;
import upeu.edu.pe.mspedido.feign.ClienteFeign;
import upeu.edu.pe.mspedido.feign.ProductoFeign;
import upeu.edu.pe.mspedido.repository.PedidoRepository;
import upeu.edu.pe.mspedido.service.PedidoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private ProductoFeign productoFeign;

    @PostMapping
    public ResponseEntity<?> guardarPedidoResponseEntity(@RequestBody Pedido pedido) {
        // Verificar si el cliente existe
        Cliente cliente = clienteFeign.listarClienteDtoPorId(pedido.getClienteId()).getBody();
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
        }

        // Verificar si cada producto en los detalles existe
        List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
            Producto producto = productoFeign.listarProductoDtoPorId(pedidoDetalle.getProductoId()).getBody();
            if (producto == null) {
                throw new RuntimeException("No existe el producto con su categoría");
            }
            pedidoDetalle.setProducto(producto);
            return pedidoDetalle;
        }).collect(Collectors.toList());

        // Asignar los detalles actualizados
        pedido.setDetalle(pedidoDetalles);

        // Asignar el cliente al pedido
        pedido.setCliente(cliente);

        // Guardar el pedido si todas las validaciones pasaron
        Pedido pedidoGuardado = pedidoService.guardarPedido(pedido);

        // Retornar respuesta exitosa
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
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
