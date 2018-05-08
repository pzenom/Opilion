package es.induserco.opilion.data.comun;

//@Entity
//@Table(name="ROL")
public class Rol {	
	//@Id @GeneratedValue
	private Long idRol;
	private String nombre;
	
	private String descripcion;
	//@ManyToOne
	private Usuario usuario;

	public Rol() {
		super();
		this.idRol = null;
		this.nombre = null;
		this.descripcion = null;
	}

	public Rol(Long idRol, String nombre, String descripcion) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}