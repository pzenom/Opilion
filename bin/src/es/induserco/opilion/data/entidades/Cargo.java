package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Cargo.
 */
public class Cargo implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id cargo. */
	private Long idCargo;
	
	/** The nombre. */
	private String nombre;
	
	/** The valor. */
	private Double valor;
	
	/** The iva cargo. */
	private Double ivaCargo;
	
	/** The total cargo. */
	private Double totalCargo;
	
	
	
	/**
	 * Instantiates a new cargo.
	 *
	 * @param nombre the nombre
	 * @param valor the valor
	 */
	
	public Cargo(String nombre, Double valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}


	/**
	 * Gets the id cargo.
	 *
	 * @return the idCargo
	 */
	public Long getIdCargo() {
		return idCargo;
	}
	
	/**
	 * Sets the id cargo.
	 *
	 * @param idCargo the idCargo to set
	 */
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}
	
	/**
	 * Sets the valor.
	 *
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	/**
	 * Sets the iva cargo.
	 *
	 * @param ivaCargo the new iva cargo
	 */
	public void setIvaCargo(Double ivaCargo) {
		this.ivaCargo = ivaCargo;
	}
	
	/**
	 * Gets the iva cargo.
	 *
	 * @return the iva cargo
	 */
	public Double getIvaCargo() {
		return ivaCargo;
	}
	
	/**
	 * Sets the total cargo.
	 *
	 * @param totalCargo the new total cargo
	 */
	public void setTotalCargo(Double totalCargo) {
		this.totalCargo = totalCargo;
	}
	
	/**
	 * Gets the total cargo.
	 *
	 * @return the total cargo
	 */
	public Double getTotalCargo() {
		return totalCargo;
	}
	
	
	
	
}
