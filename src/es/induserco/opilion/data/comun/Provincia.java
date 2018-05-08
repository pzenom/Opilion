package es.induserco.opilion.data.comun;

// TODO: Auto-generated Javadoc
/**
 * The Class Provincia.
 */
public class Provincia {
	
	/** The id provincia. */
	private Long idProvincia;
	
	/** The nombre. */
	private String nombre;
	
	
	/**
	 * Instantiates a new provincia.
	 *
	 * @param nombre the nombre
	 */
	public Provincia(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Sets the id provincia.
	 *
	 * @param idProvincia the new id provincia
	 */
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
	
	/**
	 * Gets the id provincia.
	 *
	 * @return the id provincia
	 */
	public Long getIdProvincia() {
		return idProvincia;
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
	 * Instantiates a new provincia.
	 *
	 * @param idProvincia the id provincia
	 * @param nombre the nombre
	 */
	public Provincia(Long idProvincia, String nombre) {
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}
	
	/**
	 * Instantiates a new provincia.
	 */
	public Provincia() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
