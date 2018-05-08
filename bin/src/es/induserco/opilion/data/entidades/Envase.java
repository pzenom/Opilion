package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Envase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idEnvase;
	private Long idMaterial;
	private String nombre;
	private String descripcion;
	private Double peso;
	private String dimensiones;
	private Double stock;
	private double cantidad;
	
	public Envase() {}

	public String getNombre() { return nombre; }
	
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setIdMaterial(Long idMaterial) { this.idMaterial = idMaterial; }
	public void setIdEnvase(Long idEnvase) { this.idEnvase = idEnvase; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }
	public void setPeso(Double peso) { this.peso = peso; }
	public void setStock(Double stock) { this.stock = stock; }
	public void setCantidad(double cantidad) { this.cantidad = cantidad; }
	
	public Long getIdMaterial() { return idMaterial; }
	public Long getIdEnvase() { return idEnvase; }
	public String getDescripcion() { return descripcion; }
	public String getDimensiones() { return dimensiones; }
	public Double getPeso() { return peso; }
	public Double getStock() { return stock; }
	public double getCantidad(){ return this.cantidad; }
}