package upeu.edu.pe.mspedido.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.mspedido.dto.Producto;

@FeignClient(name = "ms-catalogo-service", path = "/producto")
public interface ProductoFeign {

    @GetMapping("/{id}")
    public ResponseEntity<Producto> listarProductoDtoPorId(@PathVariable(required = true) Long id);
}
