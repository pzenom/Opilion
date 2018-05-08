package es.induserco.opilion.data.comun;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoProducto.
 */
public class TipoProducto implements Serializable {
	
	/** The id tipo. */
	private Long idTipo;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Instantiates a new tipo producto.
	 */
	public TipoProducto() {
	}
	
	/**
	 * Sets the id tipo.
	 *
	 * @param idTipo the new id tipo
	 */
	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}
	
	/**
	 * Gets the id tipo.
	 *
	 * @return the id tipo
	 */
	public Long getIdTipo() {
		return idTipo;
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
}