package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class TipoVehiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idTipoVehiculo;
	private String descripcion;

	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}