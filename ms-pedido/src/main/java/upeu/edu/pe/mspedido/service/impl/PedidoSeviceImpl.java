package upeu.edu.pe.mspedido.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.mspedido.entity.Pedido;
import upeu.edu.pe.mspedido.repository.PedidoRepository;
import upeu.edu.pe.mspedido.service.PedidoService;

import java.util.List;

@Service
public class PedidoSeviceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedido(){
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscarPedidoPorId(Long id){
        return pedidoRepository.findById(id).get();
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
