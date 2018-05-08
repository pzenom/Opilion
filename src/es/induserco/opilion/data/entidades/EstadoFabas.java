package es.induserco.opilion.data.entidades;

import java.io.Serializable;

//@Entity
//@Table(name="INCIDENCIA")
public class EstadoFabas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idEstadoFabas;
	private String descripcion;

	public EstadoFabas() {
		super();
	}	

	public EstadoFabas(Long idEstadoFabas,String descripcion) {
		super();
		this.setIdEstadoFabas(idEstadoFabas);
		this.setDescripcion(descripcion);
	}

	public void setIdEstadoFabas(Long idEstadoFabas) {
		this.idEstadoFabas = idEstadoFabas;
	}

	public Long getIdEstadoFabas() {
		return idEstadoFabas;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}