package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

/**
 * @author andres (15/07/2011)
 * @version 1.0
 */
public class RegistroEnvasado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long idGestion; 
	private String proceso;
	private Long idProducto, idAgrupacion;
	private Producto producto;
	private String loteAsignado;
	private String fechaRegistro;
	private Date fechaLlegada;
	private Date fechaCaducidad;
	private int cantidad;
	private double elaborado, elaboradoAgrupar;
	private String estado;
	private String operario;
	private String observaciones;
	private List<LineaProducto> materiaPrima;
	private List<LineaProducto> envases, envasesAgrupar;
	private List<LineaProducto> eans13;
	private List<RegistroActividad> horarioTrabajado;
	private double precioCoste;
	private String eanProducto;
	private long tipoEan;

	public Long getIdGestion() { return idGestion; }
	
	public double getPrecioCoste() { return this.precioCoste; }
	public String getObservaciones() { return observaciones; }
	public String getProceso() { return proceso; }
	public Long getIdProducto() { return idProducto; }
	public Producto getProducto() { return producto; }
	public String getLote() { return loteAsignado; }
	public Date getFecha() { return fechaLlegada; }
	public Date getFechaLlegada() { return fechaLlegada; }
	public Date getFechaCaducidad() { return fechaCaducidad; }
	public int getCantidad() { return cantidad; }
	public double getElaborado() { return elaborado; }
	public String getLoteAsignado() { return loteAsignado; }	
	public String getEstado() { return estado; }
	public String getOperario() { return operario; }
	public List<LineaProducto> getMateriaPrima() { return materiaPrima; }
	public List<LineaProducto> getEnvases() { return envases; }
	public List<LineaProducto> getEnvasesAgrupar() { return envasesAgrupar; }
	public List<LineaProducto> getEans13() { return eans13; }
	public List<RegistroActividad> getHorarioTrabajado() { return horarioTrabajado; }

	public void setIdGestion(Long idGestion) 				{ this.idGestion = idGestion; }
	public void setProceso(String proceso) 					{ this.proceso = proceso; }
	public void setIdProducto(Long idProducto) 				{ this.idProducto = idProducto; }
	public void setProducto(Producto producto) 				{ this.producto = producto; }
	public void setObservaciones(String observaciones)		{ this.observaciones = observaciones; }
	public void setLote(String lote) 						{ this.loteAsignado = lote; }
	public void setPrecioCoste(double precioCoste)			{ this.precioCoste = precioCoste; }
	public void setFecha(Date fecha) 						{ this.fechaLlegada = fecha; }
	public void setFechaLlegada(Date fechaLlegada) 			{ this.fechaLlegada = fechaLlegada; }
	public void setFechaCaducidad(Date fechaCaducidad) 		{ this.fechaCaducidad = fechaCaducidad; }
	public void setCantidad(int cantidad) 					{ this.cantidad = cantidad; }
	public void setLoteAsignado(String loteAsignado) 		{ this.loteAsignado = loteAsignado; }
	public void setEstado(String estado) 					{ this.estado = estado; }
	public void setOperario(String operario) 				{ this.operario = operario; }
	public void setMateriaPrima(List<LineaProducto> materiaPrima) { this.materiaPrima = materiaPrima; }
	public void setEnvases(List<LineaProducto> envases) 	{ this.envases = envases; }
	public void setEnvasesAgrupar(List<LineaProducto> envasesAgrupar) 	{ this.envasesAgrupar = envasesAgrupar; }
	public void setEans13(List<LineaProducto> eans13) 		{ this.eans13 = eans13; }
	public void setHorarioTrabajado(List<RegistroActividad> horarioTrabajado) { this.horarioTrabajado = horarioTrabajado; }
	public void setElaborado(double elaborado) 				{ this.elaborado = elaborado; }
	public void setEanProducto(String eanProducto) 			{ this.eanProducto = eanProducto; }
	public String getEanProducto()							{ return eanProducto; }

	public void setTipoEan(long tipoEan) {
		this.tipoEan = tipoEan;
	}

	public long getTipoEan() {
		return tipoEan;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setIdAgrupacion(Long idAgrupacion) {
		this.idAgrupacion = idAgrupacion;
	}

	public Long getIdAgrupacion() {
		return idAgrupacion;
	}

	public void setElaboradoAgrupar(double elaboradoAgrupar) {
		this.elaboradoAgrupar = elaboradoAgrupar;
	}

	public double getElaboradoAgrupar() {
		return elaboradoAgrupar;
	}		
}