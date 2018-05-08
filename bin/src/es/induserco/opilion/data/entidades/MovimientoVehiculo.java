package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class MovimientoVehiculo.
 */
public class MovimientoVehiculo implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id orden. */
	private Long idOrden; 
	
	/** The id transportista. */
	private Long idTransportista;	
	
	/** The id vehiculo. */
	private Long idVehiculo;	
	
	/** The id estado. */
	private Long idEstado;	
	
	/** The id operario. */
	private String idOperario;	
	
	/** The codigo orden. */
	private String codigoOrden;
	
	/** The codigo salida. */
	private String codigoSalida;
	
	/** The fecha. */
	private Date fecha;	
	
	/** The kminicio. */
	private Double kminicio;
	
	/** The salida. */
	private String salida;	
	
	/** The llegada. */
	private String llegada;
	
	/** The notas iniciales. */
	private String notasIniciales;
	
	/** The notas finales. */
	private String notasFinales;
	
	/** The kmfinal. */
	private Double kmfinal;	
	
	/** The horafin. */
	private String horafin;
	
	/**
	 * Instantiates a new movimiento vehiculo.
	 */
	public MovimientoVehiculo() {

	}
	
	/**
	 * Gets the id orden.
	 *
	 * @return the id orden
	 */
	public Long getIdOrden() {
		return idOrden;
	}
	
	/**
	 * Sets the id orden.
	 *
	 * @param idOrden the new id orden
	 */
	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}
	
	/**
	 * Gets the codigo orden.
	 *
	 * @return the codigo orden
	 */
	public String getCodigoOrden() {
		return codigoOrden;
	}
	
	/**
	 * Sets the codigo orden.
	 *
	 * @param codigoOrden the new codigo orden
	 */
	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}
	
	/**
	 * Gets the codigo salida.
	 *
	 * @return the codigo salida
	 */
	public String getCodigoSalida() {
		return codigoSalida;
	}
	
	/**
	 * Sets the codigo salida.
	 *
	 * @param codigoSalida the new codigo salida
	 */
	public void setCodigoSalida(String codigoSalida) {
		this.codigoSalida = codigoSalida;
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
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Gets the id operario.
	 *
	 * @return the id operario
	 */
	public String getIdOperario() {
		return idOperario;
	}
	
	/**
	 * Sets the id operario.
	 *
	 * @param idOperario the new id operario
	 */
	public void setIdOperario(String idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * Gets the salida.
	 *
	 * @return the salida
	 */
	public String getSalida() {
		return salida;
	}
	
	/**
	 * Sets the salida.
	 *
	 * @param salida the new salida
	 */
	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	/**
	 * Gets the llegada.
	 *
	 * @return the llegada
	 */
	public String getLlegada() {
		return llegada;
	}
	
	/**
	 * Sets the llegada.
	 *
	 * @param llegada the new llegada
	 */
	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}
	
	/**
	 * Gets the id transportista.
	 *
	 * @return the id transportista
	 */
	public Long getIdTransportista() {
		return idTransportista;
	}
	
	/**
	 * Sets the id transportista.
	 *
	 * @param idTransportista the new id transportista
	 */
	public void setIdTransportista(Long idTransportista) {
		this.idTransportista = idTransportista;
	}
	
	/**
	 * Gets the id vehiculo.
	 *
	 * @return the id vehiculo
	 */
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	
	/**
	 * Sets the id vehiculo.
	 *
	 * @param idVehiculo the new id vehiculo
	 */
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	
	/**
	 * Gets the id estado.
	 *
	 * @return the id estado
	 */
	public Long getIdEstado() {
		return idEstado;
	}
	
	/**
	 * Sets the id estado.
	 *
	 * @param idEstado the new id estado
	 */
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
	/**
	 * Gets the notas iniciales.
	 *
	 * @return the notas iniciales
	 */
	public String getNotasIniciales() {
		return notasIniciales;
	}
	
	/**
	 * Sets the notas iniciales.
	 *
	 * @param notasIniciales the new notas iniciales
	 */
	public void setNotasIniciales(String notasIniciales) {
		this.notasIniciales = notasIniciales;
	}
	
	/**
	 * Gets the notas finales.
	 *
	 * @return the notas finales
	 */
	public String getNotasFinales() {
		return notasFinales;
	}
	
	/**
	 * Sets the notas finales.
	 *
	 * @param notasFinales the new notas finales
	 */
	public void setNotasFinales(String notasFinales) {
		this.notasFinales = notasFinales;
	}

	/**
	 * Gets the horafin.
	 *
	 * @return the horafin
	 */
	public String getHorafin() {
		return horafin;
	}
	
	/**
	 * Sets the horafin.
	 *
	 * @param horafin the new horafin
	 */
	public void setHorafin(String horafin) {
		this.horafin = horafin;
	}
	
	/**
	 * Sets the kminicio.
	 *
	 * @param kminicio the kminicio to set
	 */
	public void setKminicio(Double kminicio) {
		this.kminicio = kminicio;
	}
	
	/**
	 * Gets the kminicio.
	 *
	 * @return the kminicio
	 */
	public Double getKminicio() {
		return kminicio;
	}
	
	/**
	 * Sets the kmfinal.
	 *
	 * @param kmfinal the kmfinal to set
	 */
	public void setKmfinal(Double kmfinal) {
		this.kmfinal = kmfinal;
	}
	
	/**
	 * Gets the kmfinal.
	 *
	 * @return the kmfinal
	 */
	public Double getKmfinal() {
		return kmfinal;
	}	

	
	
	
}
