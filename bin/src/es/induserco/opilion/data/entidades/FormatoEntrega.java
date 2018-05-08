package es.induserco.opilion.data.entidades;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
//@Entity
//@Table(name="INCIDENCIA")
/**
 * The Class FormatoEntrega.
 */
public class FormatoEntrega implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	//	@Id @GeneratedValue	
	/** The id formato entrega. */
	private Long idFormatoEntrega;
	
	/** The descripcion. */
	private String descripcion;
		
	
	/**
	 * Instantiates a new formato entrega.
	 */
	public FormatoEntrega() {
		super();
	}	

	/**
	 * Instantiates a new formato entrega.
	 *
	 * @param idFormatoEntrega the id formato entrega
	 * @param descripcion the descripcion
	 */
	public FormatoEntrega(Long idFormatoEntrega,String descripcion) {
		super();
		this.idFormatoEntrega = idFormatoEntrega;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id formato entrega.
	 *
	 * @return the id formato entrega
	 */
	public Long getidFormatoEntrega() 						{ return idFormatoEntrega; }
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() 							{ return descripcion; }
	
	/**
	 * Sets the id formato entrega.
	 *
	 * @param idFormatoEntrega the new id formato entrega
	 */
	public void setidFormatoEntrega(Long idFormatoEntrega) 			{ this.idFormatoEntrega = idFormatoEntrega; }
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) 			{ this.descripcion = descripcion; }
	
}




