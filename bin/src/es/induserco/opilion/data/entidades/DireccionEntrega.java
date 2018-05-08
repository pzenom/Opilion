package es.induserco.opilion.data.entidades;

import java.io.Serializable;

/**
 * Almacena informaci�n sobre una direcci�n de entrega
 * @author andres (05/05/2011)
 * @version 0.1
 */
public class DireccionEntrega implements Serializable{

	private static final long serialVersionUID = 1L;
	private String idDireccion;
	private String calle;
	
	private String localidad;	
	
	private double numeroBultos;
	
	public DireccionEntrega() {
		super();
	}	

	public DireccionEntrega(String idDireccion, String calle, String localidad, double numeroBultos) {
		super();
		this.setIdDireccion(idDireccion);
		this.setCalle(calle);
		this.setLocalidad(localidad);
		this.setNumeroBultos(numeroBultos);
	}

	public void setNumeroBultos(double numeroBultos) {
		this.numeroBultos = numeroBultos;
	}

	public double getNumeroBultos() {
		return numeroBultos;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalle() {
		return calle;
	}

	public void setIdDireccion(String idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getIdDireccion() {
		return idDireccion;
	}	
}