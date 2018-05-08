package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Maquina implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long idMaquina;
	private String nombre;
	private List listMant;
	private Date fecha;
	private String descripcion;
	private long idTipo;
	private String descripcionTipo;
	private String fechaCadena;
	
	/**
	 * Instantiates a new maquina.
	 */
	public Maquina() {
		super();
	}
	
	/**
	 * Gets the id maquina.
	 *
	 * @return the idMaquina
	 */
	public Long getIdMaquina() {
		return idMaquina;
	}
	
	/**
	 * Sets the id maquina.
	 *
	 * @param idMaquina the idMaquina to set
	 */
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * Sets the descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Sets the list mant.
	 *
	 * @param listMant the listMant to set
	 */
	public void setListMant(List listMant) {
		this.listMant = listMant;
	}
	
	/**
	 * Gets the list mant.
	 *
	 * @return the listMant
	 */
	public List getListMant() {
		return listMant;
	}

	public long getIdTipo() { return this.idTipo; }
	public void setIdTipo(long idTipo) { this.idTipo = idTipo; }

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setFechaCadena(String fechaCadena) {
		this.fechaCadena = fechaCadena;
	}

	public String getFechaCadena() {
		return fechaCadena;
	}
}
