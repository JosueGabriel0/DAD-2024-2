package upeu.edu.pe.mscliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.mscliente.entity.Cliente;
import upeu.edu.pe.mscliente.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> guardarClienteResponseEntity(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.guardarCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClienteResponseEntity(){
        return ResponseEntity.ok(clienteService.listarCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorIdResponseEntity(@PathVariable(required = true) Long id){
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarClienteResponseEntity(@PathVariable (required = true) Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return ResponseEntity.ok(clienteService.editarCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public String eliminarClienteResponseEntity(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return "Cliente eliminada";
    }
}
