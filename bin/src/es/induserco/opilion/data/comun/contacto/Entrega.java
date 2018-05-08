package es.induserco.opilion.data.comun.contacto;

public class Entrega {
	
	private Long idEntrega;
	private String nombre;

	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}
	
	public Long getIdEntrega() {
		return idEntrega;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public Entrega(Long idEntrega, String nombre) {
		super();
		this.idEntrega = idEntrega;
		this.nombre = nombre;
	}

	public Entrega() {
		super();
	}
}