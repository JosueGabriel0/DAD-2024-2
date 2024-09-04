package upeu.edu.pe.mscliente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.mscliente.entity.Cliente;
import upeu.edu.pe.mscliente.repository.ClienteRepository;
import upeu.edu.pe.mscliente.service.ClienteService;

import java.util.List;

@Service
public class ClienteSeviceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id){
        return clienteRepository.findById(id).get();
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }

}
