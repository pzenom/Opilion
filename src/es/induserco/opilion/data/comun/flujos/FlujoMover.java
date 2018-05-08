package es.induserco.opilion.data.comun.flujos;

import java.io.Serializable;

public class FlujoMover implements Serializable{

	private static final long serialVersionUID = 1L;
	private String origen;
	private String producto;
	private double cantidad;
	private String codigoEscape;
	private String destino;

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getOrigen() {
		return origen;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getProducto() {
		return producto;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCodigoEscape(String codigoEscape) {
		this.codigoEscape = codigoEscape;
	}
	public String getCodigoEscape() {
		return codigoEscape;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getDestino() {
		return destino;
	}
}