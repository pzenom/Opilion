package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
//import javax.persistence.Embeddable;

//@Embeddable
/**
 * The Class Formato.
 */
public class Formato implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id formato. */
	private Long idFormato;
	
	/** The nombre. */
	private String nombre;
	
	/** The tamano. */
	private String tamano;
	
	/**
	 * Instantiates a new formato.
	 */
	public Formato() {
		super();
		this.idFormato = null;
		this.nombre = null;
		this.tamano = null;
	}
	
	/**
	 * Instantiates a new formato.
	 *
	 * @param idFormato the id formato
	 * @param nombre the nombre
	 * @param tamano the tamano
	 */
	public Formato(Long idFormato, String nombre, String tamano) {
		super();
		this.idFormato = idFormato;
		this.nombre = nombre;
		this.tamano = tamano;
	}

	/**
	 * Gets the id formato.
	 *
	 * @return the id formato
	 */
	public Long getIdFormato() {
		return idFormato;
	}

	/**
	 * Sets the id formato.
	 *
	 * @param idFormato the new id formato
	 */
	public void setIdFormato(Long idFormato) {
		this.idFormato = idFormato;
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

	/**
	 * Gets the tamano.
	 *
	 * @return the tamano
	 */
	public String getTamano() {
		return tamano;
	}

	/**
	 * Sets the tamano.
	 *
	 * @param tamano the new tamano
	 */
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}
	
	
}