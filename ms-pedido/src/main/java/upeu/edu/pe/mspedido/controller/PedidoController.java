package upeu.edu.pe.mspedido.controller;

import feign.FeignException;
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
        try {
            // Verificar si el cliente existe
            ResponseEntity<Cliente> clienteResponse = clienteFeign.listarClienteDtoPorId(pedido.getClienteId());
            if (clienteResponse.getStatusCode() == HttpStatus.NOT_FOUND || clienteResponse.getBody() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
            }
            Cliente cliente = clienteResponse.getBody();

            // Verificar si cada producto en los detalles existe antes de procesarlos
            for (PedidoDetalle pedidoDetalle : pedido.getDetalle()) {
                ResponseEntity<Producto> productoResponse = productoFeign.listarProductoDtoPorId(pedidoDetalle.getProductoId());
                if (productoResponse.getStatusCode() == HttpStatus.NOT_FOUND || productoResponse.getBody() == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
                }
                Producto producto = productoResponse.getBody();
                // Asignar el producto al detalle
                pedidoDetalle.setProducto(producto);
            }

            // Asignar los detalles actualizados
            pedido.setDetalle(pedido.getDetalle());

            // Asignar el cliente al pedido
            pedido.setCliente(cliente);

            // Guardar el pedido si todas las validaciones pasaron
            Pedido pedidoGuardado = pedidoService.guardarPedido(pedido);

            // Retornar respuesta exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);

        } catch (FeignException e) {
            // Imprimir los detalles del error que Feign está arrojando
            String errorMensaje = "Error al comunicarse con otro servicio: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMensaje);

        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
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
