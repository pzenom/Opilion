package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroUbicacion.
 */
public class RegistroUbicacion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id registro ubicacion. */
	private Long idRegistroUbicacion;	
	
	/** The id ubicacion. */
	private Integer idUbicacion;
	
	/** The id registro. */
	private Long idRegistro;
	
	/** The responsable. */
	private String responsable;
	
	/** The codigo entrada. */
	private String codigoEntrada;
	
	/**
	 * Instantiates a new registro ubicacion.
	 */
	public RegistroUbicacion() {
	}
	
	/**
	 * Sets the id ubicacion.
	 *
	 * @param idUbicacion the new id ubicacion
	 */
	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	
	/**
	 * Gets the id ubicacion.
	 *
	 * @return the id ubicacion
	 */
	public Integer getIdUbicacion() {
		return idUbicacion;
	}
	
	/**
	 * Sets the id registro ubicacion.
	 *
	 * @param idRegistroUbicacion the new id registro ubicacion
	 */
	public void setIdRegistroUbicacion(Long idRegistroUbicacion) {
		this.idRegistroUbicacion = idRegistroUbicacion;
	}
	
	/**
	 * Gets the id registro ubicacion.
	 *
	 * @return the id registro ubicacion
	 */
	public Long getIdRegistroUbicacion() {
		return idRegistroUbicacion;
	}

	/**
	 * Sets the responsable.
	 *
	 * @param responsable the new responsable
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}

	/**
	 * Sets the id registro.
	 *
	 * @param idRegistro the new id registro
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * Gets the id registro.
	 *
	 * @return the id registro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * Sets the codigo entrada.
	 *
	 * @param codigoEntrada the new codigo entrada
	 */
	public void setCodigoEntrada(String codigoEntrada) {
		this.codigoEntrada = codigoEntrada;
	}

	/**
	 * Gets the codigo entrada.
	 *
	 * @return the codigo entrada
	 */
	public String getCodigoEntrada() {
		return codigoEntrada;
	}

}
