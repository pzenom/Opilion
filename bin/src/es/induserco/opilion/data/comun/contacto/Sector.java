package es.induserco.opilion.data.comun.contacto;

public class Sector {

	private Long idSector;
	private String nombreSector;

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public Long getIdSector() {
		return idSector;
	}
	
	public void setNombreSector(String nombreSector) {
		this.nombreSector = nombreSector;
	}

	public String getNombreSector() {
		return nombreSector;
		
	}

	public Sector(Long idSector, String nombreSector) {
		super();
		this.idSector = idSector;
		this.nombreSector = nombreSector;
	}
	
	public Sector() {
		super();
	}
}