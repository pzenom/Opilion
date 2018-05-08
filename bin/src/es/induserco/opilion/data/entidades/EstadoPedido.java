package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class EstadoPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private char idEstadoPedido;
	private String descripcion;
	private String nombre;

	public EstadoPedido() {
		super();
	}	

	public EstadoPedido(char idEstadoPedido,String descripcion, String nombre) {
		super();
		this.setIdEstadoPedido(idEstadoPedido);
		this.setDescripcion(descripcion);
		this.setNombre(nombre);
	}

	public void setIdEstadoPedido(char idEstadoPedido) {
		this.idEstadoPedido = idEstadoPedido;
	}

	public char getIdEstadoPedido() {
		return idEstadoPedido;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}