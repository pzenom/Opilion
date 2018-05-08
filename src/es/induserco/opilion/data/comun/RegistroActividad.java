package es.induserco.opilion.data.comun;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RegistroActividad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long idRegistro;
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private String Responsable;
	private String Observaciones;
	
	/**
	 * Sets the id registro.
	 *
	 * @param idRegistro the new id registro
	 */
	public void setIdRegistro(Long idRegistro) { this.idRegistro = idRegistro; }
	
	/**
	 * Sets the hora inicio.
	 *
	 * @param horaInicio the new hora inicio
	 */
	public void setHoraInicio(Timestamp horaInicio) { this.horaInicio = horaInicio; }
	
	/**
	 * Sets the hora fin.
	 *
	 * @param horaFin the new hora fin
	 */
	public void setHoraFin(Timestamp horaFin) { this.horaFin = horaFin; }
	
	/**
	 * Sets the responsable.
	 *
	 * @param responsable the new responsable
	 */
	public void setResponsable(String responsable) { Responsable = responsable; }
	
	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(String observaciones) { Observaciones = observaciones; }
	
	/**
	 * Gets the id registro.
	 *
	 * @return the id registro
	 */
	public Long getIdRegistro() { return idRegistro; }
	
	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public Timestamp getHoraInicio() { return horaInicio; }
	
	/**
	 * Gets the hora fin.
	 *
	 * @return the hora fin
	 */
	public Timestamp getHoraFin() { return horaFin; }
	
	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public String getResponsable() { return Responsable; }
	
	/**
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() { return Observaciones; }	
		
}
