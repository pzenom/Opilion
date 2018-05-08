package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Albaran implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAlbaran;
	private String codigoOrden, fecha;
	private String codigoAlbaran;
	private Double pesoNetoTotal;
	private Double pesoBrutoTotal;
	private Double numeroAgrupacionesTotal;
	private Double cantidadTotal;
	private Double precioUnitarioTotal;
	private Double importeTotal;
	private String usuarioResponsable;
	private String habilitado;
	private String destino;
	private String tipoAlbaran;
	private String facturado;
	private Entidad comercial;
	private Entidad cliente;
	private TipoVehiculo tipoVehiculo;
	private RegistroSalida lineas;
	private String descripcionFormaPago, descripcionEstado, observaciones,
			temperaturaTransporte, portes, horario, fechaEntrega, fechaVencimiento;
	private long idFormaPago, direccionEntrega, idTelefono, idDireccionCliente,
			idTransportista, idTemperaturaTransporte, idPortesTransporte;
	private char estado;
	private long idCliente;
	private String nombreCliente, descripcionNombreEntrega, nombreCalle,
			horarioEntrega, telefonoCliente, direccionCliente, cadenaCarrefour;
	private String nifCliente, transportista, eanCliente, numeroTelefono,
			nombreTransportista, notasTelefono, ivaAplicable, lopd;
	//private List<RegistroSalida> registrosSalida;
	private List<RegistroSalida> graneles, itemsAgrupaciones;
	private int numeroBultos;
	private int zonasImprimir = 0;//flag que determina si tenemos que imprimir la zona de productos, graneles, o ambos
	
	public Long getIdAlbaran() {
		return idAlbaran;
	}

	public void setIdAlbaran(Long idAlbaran) {
		this.idAlbaran = idAlbaran;
	}

	public String getCodigoOrden() {
		return codigoOrden;
	}

	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigoAlbaran() {
		return codigoAlbaran;
	}

	public void setCodigoAlbaran(String codigoAlbaran) {
		this.codigoAlbaran = codigoAlbaran;
	}

	public Double getPesoNetoTotal() {
		return pesoNetoTotal;
	}

	public void setPesoNetoTotal(Double pesoNetoTotal) {
		this.pesoNetoTotal = pesoNetoTotal;
	}

	public Double getPesoBrutoTotal() {
		return pesoBrutoTotal;
	}

	public void setPesoBrutoTotal(Double pesoBrutoTotal) {
		this.pesoBrutoTotal = pesoBrutoTotal;
	}

	public Double getNumeroAgrupacionesTotal() {
		return numeroAgrupacionesTotal;
	}

	public void setNumeroAgrupacionesTotal(Double numeroAgrupacionesTotal) {
		this.numeroAgrupacionesTotal = numeroAgrupacionesTotal;
	}

	public Double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public Double getPrecioUnitarioTotal() {
		return precioUnitarioTotal;
	}

	public void setPrecioUnitarioTotal(Double precioUnitarioTotal) {
		this.precioUnitarioTotal = precioUnitarioTotal;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}

	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public Entidad getComercial() {
		return comercial;
	}

	public void setComercial(Entidad comercial) {
		this.comercial = comercial;
	}

	public Entidad getCliente() {
		return cliente;
	}

	public void setCliente(Entidad cliente) {
		this.cliente = cliente;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDestino() {
		return destino;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoAlbaran(String tipoAlbaran) {
		this.tipoAlbaran = tipoAlbaran;
	}

	public String getTipoAlbaran() {
		return tipoAlbaran;
	}

	public void setFacturado(String facturado) {
		this.facturado = facturado;
	}

	public String getFacturado() {
		return facturado;
	}

	public void setLineas(RegistroSalida lineas) {
		this.lineas = lineas;
	}

	public RegistroSalida getLineas() {
		return lineas;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public char getEstado() {
		return estado;
	}

	public void setIdFormaPago(long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public long getIdFormaPago() {
		return idFormaPago;
	}

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNotasTelefono(String notasTelefono) {
		this.notasTelefono = notasTelefono;
	}

	public String getNotasTelefono() {
		return notasTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setEanCliente(String eanCliente) {
		this.eanCliente = eanCliente;
	}

	public String getEanCliente() {
		return eanCliente;
	}

	/*public void setRegistrosSalida(List<RegistroSalida> regs) {
		this.registrosSalida = regs;
	}

	public List<RegistroSalida> getRegistrosSalida() {
		return this.registrosSalida;
	}

	public JRDataSource getRegistrosSalidaDS() {
		return new JRBeanCollectionDataSource(registrosSalida);
	}*/
	
	public void setGraneles(List<RegistroSalida> regs) {
		this.graneles = regs;
	}

	public List<RegistroSalida> getGraneles() {
		return this.graneles;
	}

	public JRDataSource getGranelesDS() {
		return new JRBeanCollectionDataSource(graneles);
	}
	
	public void setItemsAgrupaciones(List<RegistroSalida> regs) {
		this.itemsAgrupaciones = regs;
	}

	public List<RegistroSalida> getItemsAgrupaciones() {
		return this.itemsAgrupaciones;
	}

	public JRDataSource getItemsAgrupacionesDS() {
		return new JRBeanCollectionDataSource(itemsAgrupaciones);
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDireccionEntrega(long direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public long getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}

	public void setHorarioEntrega(String horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}

	public String getHorarioEntrega() {
		return horarioEntrega;
	}

	public void setIvaAplicable(String ivaAplicable) {
		this.ivaAplicable = ivaAplicable;
	}

	public String getIvaAplicable() {
		return ivaAplicable;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setIdTelefono(long idTelefono) {
		this.idTelefono = idTelefono;
	}

	public long getIdTelefono() {
		return idTelefono;
	}

	public void setIdDireccionCliente(long idDireccionCliente) {
		this.idDireccionCliente = idDireccionCliente;
	}

	public long getIdDireccionCliente() {
		return idDireccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}

	public String getTransportista() {
		return transportista;
	}

	public void setIdTransportista(long idTransportista) {
		this.idTransportista = idTransportista;
	}

	public long getIdTransportista() {
		return idTransportista;
	}

	public void setNombreTransportista(String nombreTransportista) {
		this.nombreTransportista = nombreTransportista;
	}

	public String getNombreTransportista() {
		return nombreTransportista;
	}

	public void setLopd(String lopd) {
		this.lopd = lopd;
	}

	public String getLopd() {
		return lopd;
	}

	public void setCadenaCarrefour(String cadenaCarrefour) {
		this.cadenaCarrefour = cadenaCarrefour;
	}

	public String getCadenaCarrefour() {
		return cadenaCarrefour;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setTemperaturaTransporte(String temperaturaTransporte) {
		this.temperaturaTransporte = temperaturaTransporte;
	}

	public String getTemperaturaTransporte() {
		return temperaturaTransporte;
	}

	public void setPortes(String portes) {
		this.portes = portes;
	}

	public String getPortes() {
		return portes;
	}

	public void setIdTemperaturaTransporte(long idTemperaturaTransporte) {
		this.idTemperaturaTransporte = idTemperaturaTransporte;
	}

	public long getIdTemperaturaTransporte() {
		return idTemperaturaTransporte;
	}

	public void setIdPortesTransporte(long idPortesTransporte) {
		this.idPortesTransporte = idPortesTransporte;
	}

	public long getIdPortesTransporte() {
		return idPortesTransporte;
	}

	public void setDescripcionNombreEntrega(String descripcionNombreEntrega) {
		this.descripcionNombreEntrega = descripcionNombreEntrega;
	}

	public String getDescripcionNombreEntrega() {
		return descripcionNombreEntrega;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getHorario() {
		return horario;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setNumeroBultos(int numeroBultos) {
		this.numeroBultos = numeroBultos;
	}

	public int getNumeroBultos() {
		return numeroBultos;
	}

	public void setZonasImprimir(int zonasImprimir) {
		this.zonasImprimir = zonasImprimir;
	}

	public int getZonasImprimir() {
		return zonasImprimir;
	}
}