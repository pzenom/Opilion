package es.induserco.opilion.data.entidades.envasado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import es.induserco.opilion.data.entidades.Ubicacion;

public class LineaProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idLinea;
	private Long idAnterior, idAnteriorAgrupar;
	private String registroEntrada;
	private Double disponible;
	private Double teorica;
	private Double real;
	private Double mermas;
	private String lote;
	private Long idProveedor;
	private String proveedor;
	private Date fecha;
	private Date fechaCaducidad;	
	private String habilitado, usuarioResponsable;
	private String nombre;
	private String entrada;
	private String nombreCategoria;
	private ArrayList<Ubicacion> ubicaciones;
	private long idUbicacion;
	private String refUbicacion;
	private String refUbicacionNueva;
	private double cantidadUbicacion;
	private long idProducto, idCategoria;
	private long idPalet;
	
	public Long getIdLinea() 			{ return idLinea; }
	public String getRegistroEntrada() 	{ return registroEntrada; }
	public Double getDisponible() 		{ return disponible; }
	public Double getTeorica() 			{ return teorica; }
	public Double getReal() 			{ return real; }
	public Double getMermas() 			{ return mermas; }
	public String getLote() 			{ return lote; }
	public Long getIdProveedor() 		{ return idProveedor; }
	public String getProveedor() 		{ return proveedor; }
	public String getNombre() 			{ return nombre; }
	public String getHabilitado()		{ return habilitado; }
	public Date getFechaCaducidad()		{ return fechaCaducidad; }
	public Date getFecha()				{ return fecha; }
	public String getEntrada() 			{ return entrada; }
	public Long getIdAnterior()			{ return idAnterior; }
	public Long getIdAnteriorAgrupar()			{ return idAnteriorAgrupar; }
	
	public void setIdLinea(Long idLinea) { this.idLinea = idLinea; }
	public void setIdAnterior(Long idAnterior) { this.idAnterior = idAnterior; }
	public void setIdAnteriorAgrupar(Long idAnteriorAgrupar) { this.idAnteriorAgrupar = idAnteriorAgrupar; }
	public void setRegistroEntrada(String registroEntrada) { this.registroEntrada = registroEntrada; }
	public void setDisponible(Double disponible) { this.disponible = disponible; }
	public void setTeorica(Double teorica) { this.teorica = teorica; }
	public void setReal(Double real) { this.real = real; }
	public void setMermas(Double mermas) { this.mermas = mermas; }
	public void setLote(String lote) { this.lote = lote; }
	public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }
	public void setProveedor(String proveedor) { this.proveedor = proveedor; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setHabilitado(String habilitado) { this.habilitado = habilitado; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	public void setFechaCaducidad(Date fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
	public void setEntrada(String entrada) { this.entrada = entrada; }
	

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	public ArrayList<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	public void setIdUbicacion(long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public long getIdUbicacion() {
		return idUbicacion;
	}
	public void setRefUbicacion(String refUbicacion) {
		this.refUbicacion = refUbicacion;
	}
	public String getRefUbicacion() {
		return refUbicacion;
	}
	public void setCantidadUbicacion(double cantidadUbicacion) {
		this.cantidadUbicacion = cantidadUbicacion;
	}
	public double getCantidadUbicacion() {
		return cantidadUbicacion;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdPalet(long idPalet) {
		this.idPalet = idPalet;
	}
	public long getIdPalet() {
		return idPalet;
	}
	public void setRefUbicacionNueva(String refUbicacionNueva) {
		this.refUbicacionNueva = refUbicacionNueva;
	}
	public String getRefUbicacionNueva() {
		return refUbicacionNueva;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}
	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}
}