package com.misakicorpspringboot.app.orquestador.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.misakicorpspringboot.app.orquestador.models.Producto;

@FeignClient(name="servicio-productos", url="localhost:8001")
public interface IProductoFeign {
	
	@GetMapping("/productos")
	public List<Producto> listar();

	@GetMapping("/productos/{id}")
	public Producto detalle(@PathVariable Long id);

	@PostMapping("/productos")
	public Producto guardar(@RequestBody Producto producto);

	@PutMapping("/productos/{id}")
	public Producto actualizar(@RequestBody Producto producto, @PathVariable Long id);

	@DeleteMapping("/productos/{id}")
	public void eliminar(@PathVariable Long id);
}
