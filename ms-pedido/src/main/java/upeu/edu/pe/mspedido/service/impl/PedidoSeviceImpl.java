package upeu.edu.pe.mspedido.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.mspedido.dto.Cliente;
import upeu.edu.pe.mspedido.dto.Producto;
import upeu.edu.pe.mspedido.entity.Pedido;
import upeu.edu.pe.mspedido.entity.PedidoDetalle;
import upeu.edu.pe.mspedido.feign.ClienteFeign;
import upeu.edu.pe.mspedido.feign.ProductoFeign;
import upeu.edu.pe.mspedido.repository.PedidoRepository;
import upeu.edu.pe.mspedido.service.PedidoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoSeviceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private ProductoFeign productoFeign;            

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedido(){
        List<Pedido> pedidos = pedidoRepository.findAll();

        // Recorremos cada pedido y asignamos el cliente y detalles
        pedidos.forEach(pedido -> {
            Cliente cliente = clienteFeign.listarClienteDtoPorId(pedido.getClienteId()).getBody();
            List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
                Producto producto = productoFeign.listarProductoDtoPorId(pedidoDetalle.getProductoId()).getBody();
                pedidoDetalle.setProducto(producto);
                return pedidoDetalle;
            }).collect(Collectors.toList());
            pedido.setDetalle(pedidoDetalles);
            pedido.setCliente(cliente);
        });

        return pedidos;
    }

    @Override
    public Pedido buscarPedidoPorId(Long id){
        Pedido pedido = pedidoRepository.findById(id).get();
        Cliente cliente = clienteFeign.listarClienteDtoPorId(pedido.getClienteId()).getBody();
        List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
            Producto producto = productoFeign.listarProductoDtoPorId(pedidoDetalle.getProductoId()).getBody();
            pedidoDetalle.setProducto(producto);
            return pedidoDetalle;
        }).collect(Collectors.toList());
        pedido.setDetalle(pedidoDetalles);
        pedido.setCliente(cliente);
        return pedido;
    }

    @Override
    public Pedido editarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id){
        pedidoRepository.deleteById(id);
    }

}
