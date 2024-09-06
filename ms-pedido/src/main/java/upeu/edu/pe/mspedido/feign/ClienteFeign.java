package upeu.edu.pe.mspedido.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.mspedido.dto.Cliente;

@FeignClient(name = "ms-cliente-service", path = "/cliente")
public interface ClienteFeign {

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClienteDtoPorId(@PathVariable(required = true) Long id);
}
