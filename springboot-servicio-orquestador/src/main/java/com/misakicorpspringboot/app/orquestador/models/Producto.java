package com.misakicorpspringboot.app.orquestador.models;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Producto {

	private Long id;
	
	@NotEmpty(message="No puede estar vacio")
	@Size(min=4, max=22, message="Debe tener entre 4 y 20 caracteres")
	private String nombre;
	
	@NotNull(message="Debe ingresar un precio")
	@Positive(message="Debe tener un valor positivo")
	private Double precio;
	private Date createAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
