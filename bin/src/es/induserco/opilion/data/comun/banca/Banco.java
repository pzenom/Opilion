package es.induserco.opilion.data.comun.banca;

// TODO: Auto-generated Javadoc
/**
 * The Class Banco.
 */
public class Banco {
	
	/** The id banco. */
	private Long idBanco;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Sets the id banco.
	 *
	 * @param idBanco the new id banco
	 */
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	
	/**
	 * Gets the id banco.
	 *
	 * @return the id banco
	 */
	public Long getIdBanco() {
		return idBanco;
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
	 * Instantiates a new banco.
	 *
	 * @param idBanco the id banco
	 * @param nombre the nombre
	 */
	public Banco(Long idBanco, String nombre) {
		super();
		this.idBanco = idBanco;
		this.nombre = nombre;
	}
	
	/**
	 * Instantiates a new banco.
	 */
	public Banco() {
		super();

	}
	
	}
	
	