package com.misakicorpspringboot.app.orquestador.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misakicorpspringboot.app.orquestador.feign.IProductoFeign;
import com.misakicorpspringboot.app.orquestador.models.Producto;

@Service
public class ProductoServiceImp implements IProductoService {

	@Autowired
	private IProductoFeign productoFeign;
	
	@Override
	public List<Producto> findAll() {
		return productoFeign.listar();
	}

	@Override
	public Producto findById(Long id) {
		return productoFeign.detalle(id);
	}

	@Override
	public Producto save(Producto producto) {
		return productoFeign.guardar(producto);
	}

	@Override
	public Producto actualizar(Producto producto, Long id) {
		return productoFeign.actualizar(producto, id);
	}

	@Override
	public void deleteById(Long id) {
		productoFeign.eliminar(id);
	}

}
