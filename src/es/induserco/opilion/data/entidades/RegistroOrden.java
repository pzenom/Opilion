package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroOrden implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id orden. */
	private Long idOrden; 
	
	/** The codigo orden. */
	private String codigoOrden;
	
	/** The id operario. */
	private String idOperario;	
	
	/** The fecha. */
	private Date fecha;
	
	/** The id tipo registro. */
	private String idTipoRegistro;	
	
	/** The id proveedor. */
	private Long idProveedor;	
	
	/** The id transportista. */
	private Long idTransportista;	
	
	/** The lote. */
	private String lote;
	
	/** The origen. */
	private String origen;
	
	/** The albaran. */
	private String albaran;	
	
	/** The habilitado. */
	private String habilitado;
	
	/** The id vehiculo. */
	private Long idVehiculo;
	
	/** The notas vehiculo. */
	private String notasVehiculo;
	
	/** The desc proveedor. */
	private String descProveedor; 
	
	/** The desc vehiculo. */
	private String descVehiculo;
	
	/** The proveedor. */
	private Entidad proveedor;
	
	/** The vehiculo. */
	private Vehiculo vehiculo;

	// 2) lista de transportes utilizados para el registro de entrada
	//@ManyToMany
	/** The transportes. */
	private List<Transporte> transportes = new ArrayList<Transporte>();

	// 3)referencia de entidad RegistroSalida
	//@ManyToOne
	/** The responsable. */
	private Entidad responsable;

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
	 * Gets the transportes.
	 *
	 * @return the transportes
	 */
	public List<Transporte> getTransportes() {
		return transportes;
	}

	/**
	 * Sets the transportes.
	 *
	 * @param transportes the new transportes
	 */
	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

	/**
	 * Gets the entidad.
	 *
	 * @return the entidad
	 */
	public Entidad getEntidad() {
		return responsable;
	}

	/**
	 * Sets the entidad.
	 *
	 * @param Entidad the new entidad
	 */
	public void setEntidad(Entidad Entidad) {
		this.responsable = Entidad;
	}

	/**
	 * Sets the lote.
	 *
	 * @param lote the new lote
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * Gets the lote.
	 *
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * Sets the id proveedor.
	 *
	 * @param idProveedor the new id proveedor
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * Gets the id proveedor.
	 *
	 * @return the id proveedor
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen the new origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Gets the albaran.
	 *
	 * @return the albaran
	 */
	public String getAlbaran() {
		return albaran;
	}

	/**
	 * Sets the albaran.
	 *
	 * @param albaran the new albaran
	 */
	public void setAlbaran(String albaran) {
		this.albaran = albaran;
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
	 * Gets the notas vehiculo.
	 *
	 * @return the notas vehiculo
	 */
	public String getNotasVehiculo() {
		return notasVehiculo;
	}

	/**
	 * Sets the notas vehiculo.
	 *
	 * @param notasvehiculo the new notas vehiculo
	 */
	public void setNotasVehiculo(String notasvehiculo) {
		this.notasVehiculo = notasvehiculo;
	}

	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public Entidad getResponsable() {
		return responsable;
	}

	/**
	 * Sets the responsable.
	 *
	 * @param responsable the new responsable
	 */
	public void setResponsable(Entidad responsable) {
		this.responsable = responsable;
	}

	/**
	 * Sets the desc proveedor.
	 *
	 * @param descProveedor the new desc proveedor
	 */
	public void setDescProveedor(String descProveedor) {
		this.descProveedor = descProveedor;
	}

	/**
	 * Gets the desc proveedor.
	 *
	 * @return the desc proveedor
	 */
	public String getDescProveedor() {
		return descProveedor;
	}

	/**
	 * Sets the desc vehiculo.
	 *
	 * @param descVehiculo the new desc vehiculo
	 */
	public void setDescVehiculo(String descVehiculo) {
		this.descVehiculo = descVehiculo;
	}

	/**
	 * Gets the desc vehiculo.
	 *
	 * @return the desc vehiculo
	 */
	public String getDescVehiculo() {
		return descVehiculo;
	}

	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado the new habilitado
	 */
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public String getHabilitado() {
		return habilitado;
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
	 * Sets the id tipo registro.
	 *
	 * @param idTipoRegistro the new id tipo registro
	 */
	public void setIdTipoRegistro(String idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
	}

	/**
	 * Gets the id tipo registro.
	 *
	 * @return the id tipo registro
	 */
	public String getIdTipoRegistro() {
		return idTipoRegistro;
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
	 * Gets the id transportista.
	 *
	 * @return the id transportista
	 */
	public Long getIdTransportista() {
		return idTransportista;
	}

	/**
	 * Sets the id orden.
	 *
	 * @param idOrden the idOrden to set
	 */
	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	/**
	 * Gets the id orden.
	 *
	 * @return the idOrden
	 */
	public Long getIdOrden() {
		return idOrden;
	}

	/**
	 * Sets the codigo orden.
	 *
	 * @param codigoOrden the codigoOrden to set
	 */
	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

	/**
	 * Gets the codigo orden.
	 *
	 * @return the codigoOrden
	 */
	public String getCodigoOrden() {
		return codigoOrden;
	}

	/**
	 * Sets the proveedor.
	 *
	 * @param proveedor the new proveedor
	 */
	public void setProveedor(Entidad proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Gets the proveedor.
	 *
	 * @return the proveedor
	 */
	public Entidad getProveedor() {
		return proveedor;
	}

	/**
	 * Sets the vehiculo.
	 *
	 * @param vehiculo the new vehiculo
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * Gets the vehiculo.
	 *
	 * @return the vehiculo
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}



	
}
