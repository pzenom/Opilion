package es.induserco.opilion.data.comun;

// TODO: Auto-generated Javadoc
/**
 * The Class Observacion.
 */
public class Observacion {
	
	/** The id observacion. */
	private Long idObservacion;
	
	/** The descripcion. */
	private String descripcion;
	
	/**
	 * Instantiates a new observacion.
	 */
	public Observacion() {
		super();
		this.idObservacion = null;
		this.descripcion = null;
	}
	
	/**
	 * Instantiates a new observacion.
	 *
	 * @param idObservacion the id observacion
	 * @param descripcion the descripcion
	 */
	public Observacion(Long idObservacion, String descripcion) {
		super();
		this.idObservacion = idObservacion;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id observacion.
	 *
	 * @return the id observacion
	 */
	public Long getIdObservacion() 					 { return idObservacion; }
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() 					 { return descripcion; }
	
	/**
	 * Sets the id observacion.
	 *
	 * @param idObservacion the new id observacion
	 */
	public void setIdObservacion(Long idObservacion) { this.idObservacion = idObservacion; }
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) 	 { this.descripcion = descripcion; }
		
}
