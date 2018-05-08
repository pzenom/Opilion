package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import es.induserco.edifact.data.Order;

public class Comercial implements Serializable{

	private static final long serialVersionUID = 1L;
	private long idComercial;
	private Vector<Order> pedidos = new Vector<Order>();
	private Vector<String> codigosQR = new Vector<String>();
	private long lanzaderaNumero, totalLanzaderas;
	private String nombreImagenQR;
	
	public Vector<Order> getPedidos() {
		return pedidos;
	}
	public JRDataSource getPedidosDS() { return new JRBeanCollectionDataSource(pedidos); }
	public void setPedidos(Vector<Order> pedidos) {
		this.pedidos = pedidos;
	}
	public Vector<String> getCodigosQR() {
		return codigosQR;
	}
	public void setCodigosQR(Vector<String> codigosQR) {
		this.codigosQR = codigosQR;
	}
	public void setIdComercial(long idComercial) {
		this.idComercial = idComercial;
	}
	public long getIdComercial() {
		return idComercial;
	}
	public void setLanzaderaNumero(long lanzaderaNumero) {
		this.lanzaderaNumero = lanzaderaNumero;
	}
	public long getLanzaderaNumero() {
		return lanzaderaNumero;
	}
	public void setTotalLanzaderas(long totalLanzaderas) {
		this.totalLanzaderas = totalLanzaderas;
	}
	public long getTotalLanzaderas() {
		return totalLanzaderas;
	}
	public void setNombreImagenQR(String nombreImagenQR) {
		this.nombreImagenQR = nombreImagenQR;
	}
	public String getNombreImagenQR() {
		return nombreImagenQR;
	}
}