package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Ciclo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idCiclo;
	private String descripcion;
	
	public Ciclo() {
		super();
	}
	
	public Long getIdCiclo() { return idCiclo; }
	public String getDescripcion() { return descripcion; }
	
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public void setIdCiclo(Long idCiclo) { this.idCiclo = idCiclo; }
}
