package upeu.edu.pe.mscliente.service;

import upeu.edu.pe.mscliente.entity.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente guardarCliente(Cliente cliente);

    public List<Cliente> listarCliente();

    public Cliente buscarClientePorId(Long id);

    public Cliente editarCliente(Cliente cliente);

    public void eliminarCliente(Long id);
}
