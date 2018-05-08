package es.induserco.opilion.data.comun.contacto;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Entidad;

public class Ruta {
	
	private Long idRuta, idComercialDefecto;
	private String nombre, comercialDefecto;
	private Vector<Entidad> clientesEnRuta = new Vector<Entidad>();

	public void setIdRuta(Long idRuta) {
		this.idRuta = idRuta;
	}

	public Long getIdRuta() {
		return idRuta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public Ruta(Long idRuta, String nombre) {
		super();
		this.idRuta = idRuta;
		this.nombre = nombre;
	}

	public Ruta() {
		super();
	}

	public void setComercialDefecto(String comercialDefecto) {
		this.comercialDefecto = comercialDefecto;
	}

	public String getComercialDefecto() {
		return comercialDefecto;
	}

	public void setClientesEnRuta(Vector<Entidad> clientesEnRuta) {
		this.clientesEnRuta = clientesEnRuta;
	}

	public Vector<Entidad> getClientesEnRuta() {
		return clientesEnRuta;
	}
	
	public void addCliente(Entidad e){
		this.clientesEnRuta.add(e);
	}

	public void setIdComercialDefecto(Long idComercialDefecto) {
		this.idComercialDefecto = idComercialDefecto;
	}

	public Long getIdComercialDefecto() {
		return idComercialDefecto;
	}
}