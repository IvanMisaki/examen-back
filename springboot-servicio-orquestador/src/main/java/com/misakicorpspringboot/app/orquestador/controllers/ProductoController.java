package com.misakicorpspringboot.app.orquestador.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misakicorpspringboot.app.orquestador.models.Producto;
import com.misakicorpspringboot.app.orquestador.models.service.IProductoService;

import feign.FeignException.InternalServerError;
import jakarta.validation.Valid;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	public List<Producto> listar(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		}catch(InternalServerError e) {
			response.put("estado", "error");
			response.put("mensaje", e.getMessage());
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto==null) {
			response.put("estado", "no data");
			response.put("mensaje", "El producto ID: "+id.toString()+" no existe en la base de datos");
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("estado", "success");
		response.put("mensaje", "consulta exitosa");
		response.put("data", producto);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> guardar(@Valid @RequestBody Producto producto, BindingResult result) {
		Producto productoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("estado", "error");
			response.put("lstErrores", errors);
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			productoNew = productoService.save(producto);
		}catch(InternalServerError e) {
			response.put("estado", "error");
			response.put("mensaje", e.getMessage());
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "success");
		response.put("mensaje", "el producto '"+producto.getNombre()+"' se registró con exito");
		response.put("data", productoNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
		Producto productoActual = productoService.findById(id);
		Producto productoUpd = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("estado", "error");
			response.put("lstErrores", errors);
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(productoActual==null) {
			response.put("estado", "no data");
			response.put("mensaje", "El producto ID: "+id.toString()+" no existe en la base de datos");
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			productoActual.setNombre(producto.getNombre());
			productoActual.setPrecio(producto.getPrecio());
			productoUpd = productoService.save(productoActual);
		}catch(InternalServerError e) {
			response.put("estado", "error");
			response.put("mensaje", e.getMessage());
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "success");
		response.put("mensaje", "el producto '"+producto.getNombre()+"' se actualizó con exito");
		response.put("data", productoUpd);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Producto productoPorEliminar = productoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if(productoPorEliminar==null) {
			response.put("estado", "no data");
			response.put("mensaje", "El producto ID: "+id.toString()+" no existe en la base de datos");
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			productoService.deleteById(id);
		}catch(InternalServerError e) {
			response.put("estado", "error");
			response.put("mensaje", e.getMessage());
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "success");
		response.put("mensaje", "el producto '"+productoPorEliminar.getNombre()+"' se eliminó con exito");
		response.put("data", productoPorEliminar);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}
