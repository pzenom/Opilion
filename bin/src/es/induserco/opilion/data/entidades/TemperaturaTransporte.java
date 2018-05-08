package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class TemperaturaTransporte implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idTemperatura;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdTemperatura(Long idTemperatura) {
		this.idTemperatura = idTemperatura;
	}

	public Long getIdTemperatura() {
		return idTemperatura;
	}
}