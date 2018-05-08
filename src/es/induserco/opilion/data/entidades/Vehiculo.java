package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Vehiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long idVehiculo;
	private String matricula, nombreImagenQR, nombreImpresoraAsociada;
	private List<ProductoUbicado> carga;
	private long lanzaderaNumero, totalLanzaderas;
	private Vector<String> urlCodigosQR = new Vector<String>();

	public long getIdVehiculo() 						{ return idVehiculo; }
	public String getMatricula() 						{ return matricula; }
	public List<ProductoUbicado> getCarga() { return carga; }
	public JRDataSource getCargaDS() { return new JRBeanCollectionDataSource(carga); }
	
	public void setIdVehiculo(long idVehiculo) 			{ this.idVehiculo = idVehiculo; }
	public void setMatricula(String matricula) 			{ this.matricula = matricula; }
	public void setCarga(List<ProductoUbicado> carga) { this.carga = carga; }
	
	public void setNombreImagenQR(String nombreImagenQR) {
		this.nombreImagenQR = nombreImagenQR;
	}
	public String getNombreImagenQR() {
		return nombreImagenQR;
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
	public void setNombreImpresoraAsociada(String nombreImpresoraAsociada) {
		this.nombreImpresoraAsociada = nombreImpresoraAsociada;
	}
	public String getNombreImpresoraAsociada() {
		return nombreImpresoraAsociada;
	}
	public void setUrlCodigosQR(Vector<String> urlCodigosQR) {
		this.urlCodigosQR = urlCodigosQR;
	}
	public Vector<String> getUrlCodigosQR() {
		return urlCodigosQR;
	}
}