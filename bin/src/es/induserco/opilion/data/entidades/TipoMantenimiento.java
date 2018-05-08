package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoMantenimiento.
 */
public class TipoMantenimiento implements Serializable{

	/** The id tipo mant. */
	private Long idTipoMant;
	
	/** The nombre. */
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/**
	 * Instantiates a new tipo mantenimiento.
	 *
	 */
	public TipoMantenimiento() {
		super();
	}
	
	/**
	 * Gets the id tipo mant.
	 *
	 * @return the idTipoMant
	 */
	public Long getIdTipoMant() {
		return idTipoMant;
	}
	
	/**
	 * Sets the id tipo mant.
	 *
	 * @param idTipoMant the idTipoMant to set
	 */
	public void setIdTipoMant(Long idTipoMant) {
		this.idTipoMant = idTipoMant;
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
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
}
