package es.induserco.opilion.data.comun.contacto;

public class Requisito {
	
	private Long idRequisito;
	private String Nombre;

	public Requisito() {
	}

	public void setIdRequisito(Long idRequisito) {
		this.idRequisito = idRequisito;
	}

	public Long getIdRequisito() {
		return idRequisito;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}
}