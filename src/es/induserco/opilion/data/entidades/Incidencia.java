package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;

public class Incidencia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idIncidencia;
	private String nombre;
	private String loteProducto;
	private Date fecha;
	private double cantidad;
	private long idUbicacion;
	private String descripcion;
	private String descripcionUbicacion;
	private double valor;

	public Incidencia() { super(); }

	public Incidencia(Long idIncidencia,String nombre) {
		super();
		this.idIncidencia = idIncidencia;
		this.nombre = nombre;
	}

	public Long getIdIncidencia() 						{ return idIncidencia; }
	public String getNombre() 							{ return nombre; }

	public void setIdIncidencia(Long idIncidencia) 		{ this.idIncidencia = idIncidencia; }
	public void setNombre(String Incidencia) 			{ this.nombre = Incidencia; }

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setIdUbicacion(long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public long getIdUbicacion() {
		return idUbicacion;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setLoteProducto(String loteProducto) {
		this.loteProducto = loteProducto;
	}

	public String getLoteProducto() {
		return loteProducto;
	}

	public void setDescripcionUbicacion(String descripcionUbicacion) {
		this.descripcionUbicacion = descripcionUbicacion;
	}

	public String getDescripcionUbicacion() {
		return descripcionUbicacion;
	}

	public void setValor(double valor) { this.valor = valor; }
	public double getValor(){ return this.valor; }
}