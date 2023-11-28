package com.misakicorpspringboot.app.dominio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misakicorpspringboot.app.dominio.models.entity.Producto;
import com.misakicorpspringboot.app.dominio.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	public List<Producto> listar(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public Producto detalle(@PathVariable Long id) {
		return productoService.findById(id);
	}
	
	@PostMapping("/productos")
	public Producto guardar(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/productos/{id}")
	public Producto actualizar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoActual = productoService.findById(id);
		productoActual.setNombre(producto.getNombre());
		productoActual.setPrecio(producto.getPrecio());
		return productoService.save(productoActual);
	}
	
	@DeleteMapping("/productos/{id}")
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
