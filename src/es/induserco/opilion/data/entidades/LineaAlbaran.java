package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Vector;

/**
 * Almacena información sobre una linea de un albarán.
 * @author andres (05/05/2011)
 * @version 0.1
 */
public class LineaAlbaran implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double cantidad, kilos, precioUnitario, precioTotal;
	private long numeroLinea, idProducto, idDireccion;
	private String eanProducto, registro, idPedido;
	private Vector<DireccionEntrega> direcciones;
	
	public LineaAlbaran() {
		super();
	}

	public LineaAlbaran(String idPedido, String eanProducto, long numeroLinea, Vector<DireccionEntrega> direcciones) {
		super();
		this.setIdPedido(idPedido);
		this.setNumeroLinea(numeroLinea);
		this.setEanProducto(eanProducto);
		this.setDirecciones(direcciones);
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setNumeroLinea(long numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public long getNumeroLinea() {
		return numeroLinea;
	}

	public void setEanProducto(String eanProducto) {
		this.eanProducto = eanProducto;
	}

	public String getEanProducto() {
		return eanProducto;
	}

	public void setDirecciones(Vector<DireccionEntrega> direcciones) {
		this.direcciones = direcciones;
	}

	public Vector<DireccionEntrega> getDirecciones() {
		return direcciones;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setKilos(double kilos) {
		this.kilos = kilos;
	}

	public double getKilos() {
		return kilos;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setIdDireccion(long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public long getIdDireccion() {
		return idDireccion;
	}
}