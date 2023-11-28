package com.misakicorpspringboot.app.orquestador.models.service;

import java.util.List;

import com.misakicorpspringboot.app.orquestador.models.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public Producto actualizar(Producto producto, Long id);
	public void deleteById(Long id);
}
