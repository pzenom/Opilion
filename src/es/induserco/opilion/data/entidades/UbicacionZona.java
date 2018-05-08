package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class UbicacionZona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idZona;
	private String nombre;

	public UbicacionZona() {
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}