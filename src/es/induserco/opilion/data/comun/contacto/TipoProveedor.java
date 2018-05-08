package es.induserco.opilion.data.comun.contacto;

public class TipoProveedor {
	
	private Long idTipo;
	private String nombre;

	public TipoProveedor() {
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
