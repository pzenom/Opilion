package es.induserco.opilion.data.comun.contacto;

import java.io.Serializable;

import es.induserco.opilion.data.comun.Provincia;
import es.induserco.opilion.data.entidades.Entidad;

public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idDireccion;
	private Long idProvincia;
	private TipoCalle tipocalle;
	private String nombreCalle;
	private int numeroUbicacion;
	private String portal;
	private String piso;
	private String letra;
	private String puerta;
	private String escalera;
	private String localidad;
	private String municipio;
	private String codigoPostal;
	private Long idProvincia1;
	private String pais;
	private Provincia provinciaDireccion;
	private Entidad empresa;
	private String codigoEan;
	private String nombreProvincia;
	private String ubicacion;
	private String ruta;
	private long idRuta;
	private String horario;
	private String tipoDireccion;
	
	public Direccion() {
		this.idDireccion = null;
		this.tipocalle = null;
		this.nombreCalle = null;
		this.numeroUbicacion = 0;
		this.portal = null;
		this.piso = null;
		this.letra = null;
		this.puerta = null;
		this.escalera = null;
		this.localidad = null;
		this.municipio = null;
		this.idProvincia1 = null;
		this.pais = null;
	} 

	public Direccion(String nombreCalle, String localidad,String codigoPostal1) {
		super();
		this.nombreCalle = nombreCalle;
		this.localidad = localidad;
		//this.codigoPostal1 = codigoPostal1;
	}

	public Direccion(String nombreCalle, String localidad,
			String codigoPostal1, Provincia provinciaDireccion) {
		super();
		this.nombreCalle = nombreCalle;
		this.localidad = localidad;
		//this.codigoPostal1 = codigoPostal1;
		this.provinciaDireccion = provinciaDireccion;
	}
	
	public Direccion(Long idDireccion, TipoCalle tipocalle, String nombreCalle,
			int numero, String portal, String piso, String letra,
			String puerta, String escalera, String localidad, String municipio,
			String codigoPostal, Long idProvincia, String pais, String codigoEan) {
		super();
		this.idDireccion = idDireccion;
		this.tipocalle = tipocalle;
		this.nombreCalle = nombreCalle;
		this.numeroUbicacion = numero;
		this.portal = portal;
		this.piso = piso;
		this.letra = letra;
		this.puerta = puerta;
		this.escalera = escalera;
		this.localidad = localidad;
		this.municipio = municipio;
		//this.codigoPostal1 = codigoPostal;
		this.idProvincia1 = idProvincia;
		this.pais = pais;
		this.codigoEan = codigoEan;
	}
	
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public TipoCalle getTipocalle() {
		return tipocalle;
	}
	
	public void setTipocalle(TipoCalle tipocalle) {
		this.tipocalle = tipocalle;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}
	
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}
	
	public int getNumero() {
		return numeroUbicacion;
	}
	
	public void setNumero(int numero) {
		this.numeroUbicacion = numero;
	}
	
	public String getPortal() {
		return portal;
	}
	
	public void setPortal(String portal) {
		this.portal = portal;
	}
	
	public String getPiso() {
		return piso;
	}
	
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	public String getLetra() {
		return letra;
	}
	
	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	public String getPuerta() {
		return puerta;
	}
	
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}
	
	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Long getProvincia() {
		return idProvincia1;
	}

	public void setProvincia(Long idProvincia1) {
		this.idProvincia1 = idProvincia1;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setEntidad(Entidad Entidad) {
		this.empresa = Entidad;
	}

	public Entidad getEntidad() {
		return empresa;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setProvinciaDireccion(Provincia provinciaDireccion) {
		this.provinciaDireccion = provinciaDireccion;
	}

	public Provincia getProvinciaDireccion() {
		return provinciaDireccion;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getRuta() {
		return ruta;
	}
	public void setIdRuta(long idRuta) {
		this.idRuta = idRuta;
	}

	public long getIdRuta() {
		return idRuta;
	}
	
	public String getCodigoPostal() { return this.codigoPostal; }
	public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

	public void setHorario(String horario) { this.horario = horario; }
	public String getHorario() { return this.horario; }

	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public String getTipoDireccion() {
		return tipoDireccion;
	}

	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}

	public String getCodigoEan() {
		return codigoEan;
	}
}