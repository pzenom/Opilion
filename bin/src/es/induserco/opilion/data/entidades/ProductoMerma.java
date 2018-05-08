package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductoMerma.
 */
public class ProductoMerma implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id merma. */
	private Long idMerma;
	
	/** The id producto. */
	private Long idProducto;
	
	/** The id ubicacion. */
	private Long idUbicacion;
	
	/** The fecha registro. */
	private Date fechaRegistro;
	
	/** The cantidad. */
	private Double cantidad;
	
	/** The motivo. */
	private String motivo;
	
	/** The responsable. */
	private String responsable;
	
		
	/**
	 * Instantiates a new producto merma.
	 */
	public ProductoMerma() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id merma.
	 *
	 * @return the id merma
	 */
	public Long getIdMerma() {
		return idMerma;
	}

	/**
	 * Sets the id merma.
	 *
	 * @param idMerma the new id merma
	 */
	public void setIdMerma(Long idMerma) {
		this.idMerma = idMerma;
	}

	/**
	 * Gets the id ubicacion.
	 *
	 * @return the id ubicacion
	 */
	public Long getIdUbicacion() {
		return idUbicacion;
	}

	/**
	 * Sets the id ubicacion.
	 *
	 * @param idUbicacion the new id ubicacion
	 */
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
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
	 * Sets the responsable.
	 *
	 * @param responsable the new responsable
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	/**
	 * Sets the id producto.
	 *
	 * @param idProducto the new id producto
	 */
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Gets the id producto.
	 *
	 * @return the id producto
	 */
	public Long getIdProducto() {
		return idProducto;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the fecha registro.
	 *
	 * @param fechaRegistro the new fecha registro
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo the new motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	
	
	
	
}
