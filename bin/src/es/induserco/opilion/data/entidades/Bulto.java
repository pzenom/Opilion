package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import es.induserco.opilion.data.comun.Lote;

/**
 * @version 1.0
 * @author andres (20/05/2011)
 */
public class Bulto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codigoEntrada;
	private int numBulto, numBultosPalet;
	private String responsable;
	private Double pBruto, pNeto, peso;
	private List<Lote> lotes;
	private String descProducto, descripcionUbicacion;
	private long idHueco, idUbicacion, idPalet, idBulto, direccionEntrega, numeroPallets, numeroBultos;
	private double cantidad, humedad, costoUnitario;
	private String nombreCalle, horarioEntrega;
	private String descCategoria, descVehiculo, origen, albaran;
	private String fechaCaducidad, fechaLlegada;
	
	public String getCodigoEntrada() 				{ return codigoEntrada; }
	public int getNumBulto() 						{ return numBulto; }
	public Double getPeso() 						{ return peso; }
	public String getResponsable() 					{ return responsable; }
	public int getNumBultosPalet() 					{ return numBultosPalet; }
	public Double getPBruto() 						{ return pBruto; }
	public Double getPNeto() 						{ return pNeto; }
	public static long getSerialVersionUID() 		{ return serialVersionUID; }
	public List<Lote> getLotes() 					{ return lotes; }
	public long getIdHueco()						{ return idHueco; }
	public String getDescripcionUbicacion()			{ return descripcionUbicacion; }
	public long getDireccionEntrega()				{ return this.direccionEntrega; }
	public long getIdPalet()						{ return idPalet; }
	public double getCantidad()						{ return cantidad; }
	public long getIdUbicacion() { return this.idUbicacion; }
	public long getIdBulto() { return idBulto; }
	
	public void setCodigoEntrada(String codigoEntrada) { this.codigoEntrada = codigoEntrada; }
	public void setNumBulto(int numBulto) 			{ this.numBulto = numBulto; }
	public void setPeso(Double peso) 				{ this.peso = peso; }
	public void setResponsable(String responsable) 	{ this.responsable = responsable; }
	public void setNumBultosPalet(int numBultosPalet) { this.numBultosPalet = numBultosPalet; }
	public void setPBruto(Double bruto) 			{ this.pBruto = bruto; }
	public void setPNeto(Double neto) 				{ this.pNeto = neto; }
	public void setDireccionEntrega(long direccionEntrega) { this.direccionEntrega = direccionEntrega; }
	public void setLotes(List<Lote> lotes) 			{ this.lotes = lotes; }
	public void setDescripcionUbicacion(String descripcionUbicacion) { this.descripcionUbicacion = descripcionUbicacion; }
	public void setIdHueco(long idHueco)			{ this.idHueco = idHueco; }
	public void setIdPalet(long idPalet)			{ this.idPalet = idPalet; }
	public void setCantidad(double cantidad) { this.cantidad = cantidad; }
	public void setIdUbicacion(long idUbicacion) { this.idUbicacion = idUbicacion; }
	public void setIdBulto(long idBulto) { this.idBulto = idBulto; }
	
	public void setHorarioEntrega(String horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}
	public String getHorarioEntrega() {
		return horarioEntrega;
	}
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}
	public String getNombreCalle() {
		return nombreCalle;
	}
	
    public JRDataSource getLotesDS(){
        return new JRBeanCollectionDataSource(lotes);
    }
    
	public void setDescripcionProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	
	public String getDescProducto() {
		return descProducto;
	}
	
	public String getFechaLlegada(){ return this.fechaLlegada; }
	public String getFechaCaducidad(){ return this.fechaCaducidad; }
	public void setFechaCaducidad(String fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
	public void setFechaLlegada(String string) { this.fechaLlegada = string; }
	
	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}
	
	public double getHumedad() {
		return humedad;
	}
	
	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}
	
	public String getDescCategoria() {
		return descCategoria;
	}
	
	public void setDescVehiculo(String descVehiculo) {
		this.descVehiculo = descVehiculo;
	}
	
	public String getDescVehiculo() {
		return descVehiculo;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setAlbaran(String albaran) {
		this.albaran = albaran;
	}
	
	public String getAlbaran() {
		return albaran;
	}
	
	public void setNumeroBultos(long numeroBultos) {
		this.numeroBultos = numeroBultos;
	}
	
	public long getNumeroBultos() {
		return numeroBultos;
	}
	
	public void setNumeroPallets(long numeroPallets) {
		this.numeroPallets = numeroPallets;
	}
	
	public long getNumeroPallets() {
		return numeroPallets;
	}
	
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	
	public double getCostoUnitario() {
		return costoUnitario;
	}
}