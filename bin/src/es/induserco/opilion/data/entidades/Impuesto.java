package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Impuesto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idImpuesto;
	private String nombreImpuesto;
	private double porcentaje;

	public Impuesto() {
		super();
	}	

	public Impuesto(Long idImpuesto, String nombreImpuesto, double porcentaje) {
		super();
		this.idImpuesto = idImpuesto;
		this.nombreImpuesto = nombreImpuesto;
		this.porcentaje = porcentaje;
	}

	public Long getIdImpuesto() {
		return idImpuesto;
	}
	
	public void setIdImpuesto(long id) {
		this.idImpuesto = id;
	}

	public String getNombreImpuesto() {
		return nombreImpuesto;
	}

	public void setNombreImpuesto(String nombreImpuesto) {
		this.nombreImpuesto = nombreImpuesto;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
}