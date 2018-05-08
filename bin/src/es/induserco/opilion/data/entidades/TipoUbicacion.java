package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoUbicacion.
 */
public class TipoUbicacion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id tipo ubicacion. */
	private Long idTipoUbicacion;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Instantiates a new tipo ubicacion.
	 */
	public TipoUbicacion() {

	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Sets the id tipo ubicacion.
	 *
	 * @param idTipoUbicacion the new id tipo ubicacion
	 */
	public void setIdTipoUbicacion(Long idTipoUbicacion) {
		this.idTipoUbicacion = idTipoUbicacion;
	}
	
	/**
	 * Gets the id tipo ubicacion.
	 *
	 * @return the id tipo ubicacion
	 */
	public Long getIdTipoUbicacion() {
		return idTipoUbicacion;
	}
	
}
