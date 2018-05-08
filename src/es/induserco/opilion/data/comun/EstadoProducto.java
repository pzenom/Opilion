package es.induserco.opilion.data.comun;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoProducto.
 */
public class EstadoProducto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id estado. */
	private Long idEstado;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Sets the id estado.
	 *
	 * @param idEstado the new id estado
	 */
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
	/**
	 * Gets the id estado.
	 *
	 * @return the id estado
	 */
	public Long getIdEstado() {
		return idEstado;		
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
	 * Instantiates a new estado producto.
	 */
	public EstadoProducto() {
		// TODO Auto-generated constructor stub
	}
	
	
}
