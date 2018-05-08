package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Material.
 */
public class Material implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id material. */
	private Long idMaterial;
	
	/** The nombre. */
	private String nombre;
	
	
	/**
	 * Instantiates a new material.
	 */
	public Material() {
	}
	
	/**
	 * Gets the id material.
	 *
	 * @return the id material
	 */
	public Long getIdMaterial() {
		return idMaterial;
	}
	
	/**
	 * Sets the id material.
	 *
	 * @param idMaterial the new id material
	 */
	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
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
