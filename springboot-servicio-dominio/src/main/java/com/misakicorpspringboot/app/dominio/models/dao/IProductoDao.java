package com.misakicorpspringboot.app.dominio.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.misakicorpspringboot.app.dominio.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
