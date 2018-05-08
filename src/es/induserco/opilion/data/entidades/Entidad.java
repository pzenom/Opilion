package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.TipoProveedor;
//import javax.persistence.*;
import es.induserco.opilion.data.comun.contacto.Telefono;

public class Entidad extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;//Para una empresa es el nombre comercial
	private String apellidos;// Para una empresa es la razón social
	private String nif;// Para una empresa es el CIF
	private Date fecha;
	private double dsctoMercancia, dsctoValor, limiteCredito;
	private String nombreContacto, cargoContacto,
		extensionContacto, emailContacto, dptoContacto, responsable, habilitado,
		horario, foto, web, ean, exportableAutoventa, estado, observaciones;
	private Vector<Direccion> direcciones = new Vector<Direccion>(), direccionesEntrega = new Vector<Direccion>(), 
		direccionesFacturacion = new Vector<Direccion>();
	private Vector<Telefono> telefonos = new Vector<Telefono>();
	private Vector<Email> emails = new Vector<Email>();
	private List<Telefono> telefonosEntidad;
	private Vector<DatoBancario> formasPagoEntidad;
	private Entidad entidadContacto;
	private List<Entidad> contactos = new ArrayList<Entidad>();
	private List<Entidad> sucursales;
	private char autonomoEmpresa;
	private double recargoEquivalencia;
	private Vector<Rol> rolesEntidad;
	private Vector<TipoProveedor> tiposProveedor;
	private long idSectorCliente;
	private boolean deudas;
	private long idTipo, idSector, idRuta, idComercial;//Utilizados para la búsqueda de entidades
	
	public Entidad() {
		super();
	}

	public Entidad(String nombre, String apellido1, String apellido2,
			String nif, String email, String foto, String web,
			String telefono1,DatoBancario datosBancarios) {
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.foto = foto;
		this.web = web;
	}	

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getNombre() 							{ return nombre; }
	public String getRazonSocial()						{ return nombre + apellidos; }
	public String getNif() 								{ return nif; }
	public String getFoto() 							{ return foto; }
	public String getWeb() 								{ return web; }
	public List<Entidad> getContactos() 				{ return contactos; }
	public List<Entidad> getSucursales() 				{ return sucursales; }
	public Entidad getEntidadContacto() 				{ return entidadContacto; }
	public double getRecargoEquivalencia() 				{ return recargoEquivalencia; }
	
	public void setNombre(String nombre) 				{ this.nombre = nombre; }
	public void setNif(String nif) 						{ this.nif = nif; }
	public void setFoto(String foto) 					{ this.foto = foto; }
	public void setWeb(String web) 						{ this.web = web; }
	public void setContactos(List<Entidad> contactos) 	{ this.contactos = contactos; }
	public void setSucursales(List<Entidad> sucursales) { this.sucursales = sucursales; }
	public void setEntidadContacto(Entidad entidadContacto) { this.entidadContacto = entidadContacto; }
	public void setRecargoEquivalencia(double recargoEquivalencia) { this.recargoEquivalencia = recargoEquivalencia; }

	public void setTelefonosEntidad(List<Telefono> telefonosEntidad) {
		this.telefonosEntidad = telefonosEntidad;
	}

	public List<Telefono> getTelefonosEntidad() {
		return telefonosEntidad;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getHorario() {
		return horario;
	}

	public void setDsctoMercancia(double dsctoMercancia) {
		this.dsctoMercancia = dsctoMercancia;
	}

	public double getDsctoMercancia() {
		return dsctoMercancia;
	}

	public void setDsctoValor(double dsctoValor) {
		this.dsctoValor = dsctoValor;
	}

	public double getDsctoValor() {
		return dsctoValor;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setExtensionContacto(String extensionContacto) {
		this.extensionContacto = extensionContacto;
	}

	public String getExtensionContacto() {
		return extensionContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setDptoContacto(String dptoContacto) {
		this.dptoContacto = dptoContacto;
	}

	public String getDptoContacto() {
		return dptoContacto;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getEan() {
		return ean;
	}
	
	public void setTelefonos(Vector<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public Vector<Telefono> getTelefonos() {
		return telefonos;
	}
	
	public void setEmails(Vector<Email> emails) {
		this.emails = emails;
	}

	public Vector<Email> getEmails() {
		return emails;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setDirecciones(Vector<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Vector<Direccion> getDirecciones() {
		return this.direcciones;
	}


	public void setAutonomoEmpresa(char autonomoEmpresa) {
		this.autonomoEmpresa = autonomoEmpresa;
	}

	public char getAutonomoEmpresa() {
		return autonomoEmpresa;
	}

	public void setFormasPagoEntidad(Vector<DatoBancario> formasPagoEntidad) {
		this.formasPagoEntidad = formasPagoEntidad;
	}

	public Vector<DatoBancario> getFormasPagoEntidad() {
		return formasPagoEntidad;
	}

	public void setRolesEntidad(Vector<Rol> rolesEntidad) {
		this.rolesEntidad = rolesEntidad;
	}

	public Vector<Rol> getRolesEntidad() {
		return rolesEntidad;
	}

	public void setTiposProveedor(Vector<TipoProveedor> tiposProveedor) {
		this.tiposProveedor = tiposProveedor;
	}

	public Vector<TipoProveedor> getTiposProveedor() {
		return tiposProveedor;
	}

	public void setIdSectorCliente(long idSectorCliente) {
		this.idSectorCliente = idSectorCliente;
	}

	public long getIdSectorCliente() {
		return idSectorCliente;
	}

	public void setIdSector(long idSector) {
		this.idSector = idSector;
	}

	public long getIdSector() {
		return idSector;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setDeudas(boolean deudas) {
		this.deudas = deudas;
	}

	public boolean getDeudas() {
		return deudas;
	}

	public void setDireccionesEntrega(Vector<Direccion> direccionesEntrega) {
		this.direccionesEntrega = direccionesEntrega;
	}

	public Vector<Direccion> getDireccionesEntrega() {
		return direccionesEntrega;
	}

	public void setDireccionesFacturacion(Vector<Direccion> direccionesFacturacion) {
		this.direccionesFacturacion = direccionesFacturacion;
	}

	public Vector<Direccion> getDireccionesFacturacion() {
		return direccionesFacturacion;
	}

	public void setExportableAutoventa(String exportableAutoventa) {
		this.exportableAutoventa = exportableAutoventa;
	}

	public String getExportableAutoventa() {
		return exportableAutoventa;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setIdRuta(long idRuta) {
		this.idRuta = idRuta;
	}

	public long getIdRuta() {
		return idRuta;
	}

	public void setIdComercial(long idComercial) {
		this.idComercial = idComercial;
	}

	public long getIdComercial() {
		return idComercial;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}
}