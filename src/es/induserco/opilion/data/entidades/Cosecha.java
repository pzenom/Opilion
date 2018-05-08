package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Cosecha implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id cosecha. */
	private Long idCosecha;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Instantiates a new cosecha.
	 */
	public Cosecha() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the id cosecha.
	 *
	 * @return the id cosecha
	 */
	public Long getIdCosecha() {
		return idCosecha;
	}
	
	/**
	 * Sets the id cosecha.
	 *
	 * @param idCosecha the new id cosecha
	 */
	public void setIdCosecha(Long idCosecha) {
		this.idCosecha = idCosecha;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
