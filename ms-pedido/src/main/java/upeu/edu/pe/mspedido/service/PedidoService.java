package upeu.edu.pe.mspedido.service;

import upeu.edu.pe.mspedido.entity.Pedido;

import java.util.List;

public interface PedidoService {

    public Pedido guardarPedido(Pedido pedido);

    public List<Pedido> listarPedido();

    public Pedido buscarPedidoPorId(Long id);

    public Pedido editarPedido(Pedido pedido);

    public void eliminarPedido(Long id);
}
