package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
//import javax.persistence.*;

//@Entity
//@Table(name="INCIDENCIAPRODUCTO")
/**
 * The Class IncidenciaProducto.
 */
public class IncidenciaProducto implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	//	@Id @GeneratedValue	
	/** The id incidencia. */
	private Long idIncidencia = null;
	
	/** The estado. */
	private String estado;
	
	/** The fecha. */
	private Date fecha;
	
	/** The descripcion. */
	private String descripcion;
	
	//1) Muchas incidencias estan asociadas a 1 producto
	//  @ManyToOne(optional=true)
	/** The producto. */
	private Producto producto;

	/**
	 * Sets the id incidencia.
	 *
	 * @param idIncidencia the new id incidencia
	 */
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	/**
	 * Gets the id incidencia.
	 *
	 * @return the id incidencia
	 */
	public Long getIdIncidencia() {
		return idIncidencia;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}
}
