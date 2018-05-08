package es.induserco.opilion.data.entidades;

import java.io.Serializable;

/**
 * @author andres (02/06/2011)
 * @version 1.0
 */
public class Zona implements Serializable{

	private static final long serialVersionUID = 1L;
	private long idZona;
	private long idZonaAlmacen;
	private long idAlmacen;
	private String rutaPlano;
	
	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}
	public long getIdZona() {
		return idZona;
	}
	public void setRutaPlano(String rutaPlano) {
		this.rutaPlano = rutaPlano;
	}
	public String getRutaPlano() {
		return rutaPlano;
	}
	public void setIdAlmacen(long idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public long getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdZonaAlmacen(long idZonaAlmacen) {
		this.idZonaAlmacen = idZonaAlmacen;
	}
	public long getIdZonaAlmacen() {
		return idZonaAlmacen;
	}
}