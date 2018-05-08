package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RegistroEntrada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idEntrada, idOrden, idProveedor, idProducto, idCategoria, idClienteDevolucion;
	private String codigoEntrada, codigoOrden, strFecha, strfechaCaducidad, strfechaLlegada,
		lote, habilitado, origen, albaran, notasVehiculo, notasIncidencias, fechaCaducidad, fechaLlegada;
	private Date fecha;
	private Double saldo;
	private Long idVehiculo;
	private Long idIncidencias;
	private Long idFormatoEntrega;
	private Long idEstadoFabas;
	private String idOperario, nombreClienteDevolucion;
	private String descProveedor;
	private String descProducto;
	private String descCategoria;
	private String descCategoriaEntrada;
	private String descVehiculo;
	private Long idCosecha;
	private Long idCategoriaEntrada;
	private String idTipoRegistro;
	private Long idEnvase;
	private Double temperatura;
	private Double humedad;
	private Long idTransportista;
	private Long idTipoUbicacion;
	private Double cantidad;
	private Double costoUnitario;
	private Double costoTotal;
	private Long numeroPallets;
	private Long numeroBultos;
	private String descResponsable, accionCorrectora, incidencia;
	private Producto productoRegistroSalida;
	private List<Transporte> transportes = new ArrayList<Transporte>();
	private Entidad responsable;
	private String materia = "M";
	private String envase = "E";
	private List<Incidencia> incidencias;
	private Vector<EstadoFabas> estados;
	private boolean listoDistribuir;
	private List<Bulto> bultos;
	private boolean podemosBorrar, devolverStock, reaprovechar;;
	
	public RegistroEntrada(String codigoEntrada) {
		super();
		this.codigoEntrada = codigoEntrada;
	}

	public RegistroEntrada() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}

	public List<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

	public Entidad getEntidad() {
		return responsable;
	}

	public void setEntidad(Entidad Entidad) {
		this.responsable = Entidad;
	}

	public void setProductoRegistroSalida(Producto productoRegistroSalida) {
		this.productoRegistroSalida = productoRegistroSalida;
	}

	public Producto getProductoRegistroSalida() {
		return productoRegistroSalida;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getLote() {
		return lote;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getOrigen() {
		return origen;
	}

	public String getAlbaran() {
		return albaran;
	}

	public void setAlbaran(String albaran) {
		this.albaran = albaran;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getNotasVehiculo() {
		return notasVehiculo;
	}

	public void setNotasVehiculo(String notasvehiculo) {
		this.notasVehiculo = notasvehiculo;
	}

	public Long getIdIncidencias() {
		return idIncidencias;
	}

	public void setIdIncidencias(Long idIncidencias) {
		this.idIncidencias = idIncidencias;
	}

	public String getNotasIncidencias() {
		return notasIncidencias;
	}

	public void setNotasIncidencias(String notasIncidencias) {
		this.notasIncidencias = notasIncidencias;
	}

	public Entidad getResponsable() {
		return responsable;
	}

	public void setResponsable(Entidad responsable) {
		this.responsable = responsable;
	}

	public void setCodigoEntrada(String codigoEntrada) {
		this.codigoEntrada = codigoEntrada;
	}

	public String getCodigoEntrada() {
		return codigoEntrada;
	}

	public void setDescProveedor(String descProveedor) {
		this.descProveedor = descProveedor;
	}

	public String getDescProveedor() {
		return descProveedor;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescVehiculo(String descVehiculo) {
		this.descVehiculo = descVehiculo;
	}

	public String getDescVehiculo() {
		return descVehiculo;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public Long getIdFormatoEntrega() {
		return idFormatoEntrega;
	}

	public void setIdFormatoEntrega(Long idFormatoEntrega) {
		this.idFormatoEntrega = idFormatoEntrega;
	}

	public Long getNumeroPallets() {
		return numeroPallets;
	}

	public void setNumeroPallets(Long numeroPallets) {
		this.numeroPallets = numeroPallets;
	}

	public Long getNumeroBultos() {
		return numeroBultos;
	}

	public void setNumeroBultos(Long numeroBultos) {
		this.numeroBultos = numeroBultos;
	}

	public String getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(String idOperario) {
		this.idOperario = idOperario;
	}

	public Long getIdEstadoFabas() {
		return idEstadoFabas;
	}

	public void setIdEstadoFabas(Long idEstadoFabas) {
		this.idEstadoFabas = idEstadoFabas;
	}

	public void setStrfechaCaducidad(String strfechaCaducidad) {
		this.strfechaCaducidad = strfechaCaducidad;
	}

	public String getStrfechaCaducidad() {
		return strfechaCaducidad;
	}

	public void setStrfechaLlegada(String strfechaLlegada) {
		this.strfechaLlegada = strfechaLlegada;
	}

	public String getStrfechaLlegada() {
		return strfechaLlegada;
	}

	public void setIdCosecha(Long idCosecha) {
		this.idCosecha = idCosecha;
	}

	public Long getIdCosecha() {
		return idCosecha;
	}

	public void setIdCategoriaEntrada(Long idCategoriaEntrada) {
		this.idCategoriaEntrada = idCategoriaEntrada;
	}

	public Long getIdCategoriaEntrada() {
		return idCategoriaEntrada;
	}

	public void setIdTipoRegistro(String idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
	}

	public String getIdTipoRegistro() {
		return idTipoRegistro;
	}

	public void setIdEnvase(Long idEnvase) {
		this.idEnvase = idEnvase;
	}

	public Long getIdEnvase() {
		return idEnvase;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setHumedad(Double humedad) {
		this.humedad = humedad;
	}

	public Double getHumedad() {
		return humedad;
	}

	public void setIdTransportista(Long idTransportista) {
		this.idTransportista = idTransportista;
	}

	public Long getIdTransportista() {
		return idTransportista;
	}

	public void setIdTipoUbicacion(Long idTipoUbicacion) {
		this.idTipoUbicacion = idTipoUbicacion;
	}

	public Long getIdTipoUbicacion() {
		return idTipoUbicacion;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	public Long getIdOrden() {
		return idOrden;
	}

	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

	public String getCodigoOrden() {
		return codigoOrden;
	}

	public void setStrFecha(String strFecha) {
		this.strFecha = strFecha;
	}

	public String getStrFecha() {
		return strFecha;
	}

	public void setDescResponsable(String descResponsable) {
		this.descResponsable = descResponsable;
	}

	public String getDescResponsable() {
		return descResponsable;
	}

	public void setEnvase(String envase) {
		this.envase = envase;
	}

	public String getEnvase() {
		return envase;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getMateria() {
		return materia;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setDescCategoriaEntrada(String descCategoriaEntrada) {
		this.descCategoriaEntrada = descCategoriaEntrada;
	}

	public String getDescCategoriaEntrada() {
		return descCategoriaEntrada;
	}

	public void setEstados(Vector<EstadoFabas> estados) {
		this.estados = estados;
	}
	
	public Vector<EstadoFabas> getEstados() {
		return this.estados;
	}

	public void setListoDistribuir(boolean listoDistribuir) {
		this.listoDistribuir = listoDistribuir;
	}

	public boolean isListoDistribuir() {
		return listoDistribuir;
	}
	
    public JRDataSource getBultosDS(){
        return new JRBeanCollectionDataSource(bultos);
    }
    
    public List<Bulto> getBultos(){
    	return this.bultos;
    }
    
    public void setBultos(List<Bulto> bultos){
    	this.bultos = bultos;
    }

	public void setPodemosBorrar(boolean podemosBorrar) { this.podemosBorrar = podemosBorrar; }
	public boolean isPodemosBorrar() { return podemosBorrar; }

	public void setAccionCorrectora(String accionCorrectora) {
		this.accionCorrectora = accionCorrectora;
	}

	public String getAccionCorrectora() {
		return accionCorrectora;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setDevolverStock(boolean devolverStock) {
		this.devolverStock = devolverStock;
	}

	public boolean isDevolverStock() {
		return devolverStock;
	}

	public void setIdClienteDevolucion(Long idClienteDevolucion) {
		this.idClienteDevolucion = idClienteDevolucion;
	}

	public Long getIdClienteDevolucion() {
		return idClienteDevolucion;
	}

	public void setNombreClienteDevolucion(String nombreClienteDevolucion) {
		this.nombreClienteDevolucion = nombreClienteDevolucion;
	}

	public String getNombreClienteDevolucion() {
		return nombreClienteDevolucion;
	}

	public void setReaprovechar(boolean reaprovechar) {
		this.reaprovechar = reaprovechar;
	}

	public boolean isReaprovechar() {
		return reaprovechar;
	}

}