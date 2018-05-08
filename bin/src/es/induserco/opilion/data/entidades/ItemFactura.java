package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class ItemFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Factura factura;
	private long idItem, idProducto, numLinea;
	private String codigoItem, lote;
	private Double cantidad;
	private Double precioUnitBruto;
	private Double precioUnitNeto;
	private Double precioTotal, precioKilo;
	private Double iva;
	private long agrupaciones;
	private double peso;
	private Producto producto;
	private String descripcion, albaran, fechaEntrega;
	
	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitBruto() {
		return precioUnitBruto;
	}

	public void setPrecioUnitBruto(Double precioUnitBruto) {
		this.precioUnitBruto = precioUnitBruto;
	}

	public Double getPrecioUnitNeto() {
		return precioUnitNeto;
	}

	public void setPrecioUnitNeto(Double precioUnitNeto) {
		this.precioUnitNeto = precioUnitNeto;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setAgrupaciones(long agrupaciones) {
		this.agrupaciones = agrupaciones;
	}

	public long getAgrupaciones() {
		return agrupaciones;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPeso() {
		return peso;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setNumLinea(long numLinea) {
		this.numLinea = numLinea;
	}

	public long getNumLinea() {
		return numLinea;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getLote() {
		return lote;
	}

	public void setPrecioKilo(Double precioKilo) {
		this.precioKilo = precioKilo;
	}

	public Double getPrecioKilo() {
		return precioKilo;
	}

	public void setAlbaran(String albaran) {
		this.albaran = albaran;
	}

	public String getAlbaran() {
		return albaran;
	}

	public void setFechaEntrega(String fecha) {
		this.fechaEntrega = fecha;
	}
	
	public String getFechaEntrega(){
		return fechaEntrega;
	}
}