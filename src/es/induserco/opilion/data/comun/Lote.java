package es.induserco.opilion.data.comun;

public class Lote {

	private String lote;
	private String ubicacion;
	private double cantidad, cantidadPedida;
	private String error;
	private long idProducto;
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getLote() {
		return lote;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setCantidadPedida(double cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	public double getCantidadPedida() {
		return cantidadPedida;
	}
}