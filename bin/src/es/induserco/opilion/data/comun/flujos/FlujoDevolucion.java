package es.induserco.opilion.data.comun.flujos;

import java.io.Serializable;

public class FlujoDevolucion implements Serializable{

	private static final long serialVersionUID = 1L;
	private String lote;
	private double cantidad;
	private String codigoEscape;
	private String destino;
	private String devolverStock;

	public void setDevolverStock(String devolverStock) {
		this.devolverStock = devolverStock;
	}
	public String getDevolverStock() {
		return devolverStock;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getLote() {
		return lote;
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