package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Backup implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String ruta;
	private String fecha;
	private char auto;
	
	public Backup() {
		super();
	}

	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setFecha(String fecha) { this.fecha = fecha; }
	public void setRuta(String ruta) { this.ruta = ruta; }
	
	public String getNombre() {	return nombre; }
	public String getRuta() { return ruta; }
	public String getFecha() { return fecha; }

	public void setAuto(char auto) {
		this.auto = auto;
	}

	public char getAuto() {
		return auto;
	}
}