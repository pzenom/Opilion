package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class PortesTransporte implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idPorte;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdPorte(Long idPorte) {
		this.idPorte = idPorte;
	}

	public Long getIdPorte() {
		return idPorte;
	}
}