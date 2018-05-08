package es.induserco.opilion.negocio;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.comun.Empresa;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.Banco;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.banca.FormaPago;
import es.induserco.opilion.data.comun.banca.FormaPagoDesde;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.data.entidades.Accion;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.GestionRoles;
import es.induserco.opilion.datos.entidad.IEntidadDataService;

public class EntidadDataHelper {

	public Vector getEntidades(String filtroConsulta)throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getEntidades(filtroConsulta);
	}

	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate)  throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).updateEntidad(entidadFind, entidadUpdate);
	}

	public Entidad loadEntidad(Entidad entry)  throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).loadEntidad(entry);
	}

	public String getFechaRegistro() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getFechaRegistro();
	}

	public Vector getProvincias() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getProvincias();
	}

	public Vector getFormasPago() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getFormasPago();
	}
	
	public Vector getListaDiasEntrega() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaDiasEntrega();
	}

	public Vector getListaRutas() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaRutas();
	}

	public Vector getListaSectores() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaSectores();
	}

	public Vector getListaBancos() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaBancos();
	}

	public Vector getListaTiposProveedores() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaTiposProveedores();
	}

	public Vector getListaReqHomologa() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaReqHomologa();
	}

	public Vector getListaReqHomologa(Entidad entidad) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getListaReqHomologa(entidad);
	}

	public Boolean deshabilitaEntidad(Entidad entidadFind) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).deshabilitaEntidad(entidadFind);
	}

	public Vector<Entidad> getProveedores(Entidad entry) throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getProveedores(entry);
	}	

	public Vector<Entidad> getClientes(Entidad entry) throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getClientes(entry);
	}

	public Entidad getEntidad(long id) throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getEntidad(id);
	}

	public String getNombreFormaPago(long idFormaDePago) throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).getNombreFormaPago(idFormaDePago);
	}

	public void registrarBanco(Banco banco) throws Exception {
		((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).registrarBanco(banco);
	}

	public void registrarFormaPago(FormaPago formaPago) throws Exception {
		((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).registrarFormaPago(formaPago);
	}

	public void registrarSector(Sector sector) throws Exception {
		((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).registrarSector(sector);
	}

	public Boolean addEntidad(Entidad entry) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).getService("IEntidadDataService")).addEntidad(entry);
	}

	public Vector<DatoBancario> getFormasPagoEntidad(Entidad entidad) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getFormasPagoEntidad(entidad);
	}

	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getDireccionesEntidad(entidad, tipoDireccion);
	}

	public Vector<Order> getPedidosCliente(long idUsuario) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getPedidosCliente(idUsuario);
	}

	public Empresa getEmpresa(long idEmpresa) throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getEmpresa(idEmpresa);
	}

	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception {
		((IEntidadDataService)(new ServiceLocator()).
		getService("IEntidadDataService")).guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
	}

	public GestionRoles getRoles() throws Exception {
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getRoles();
	}

	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception{
		return((IEntidadDataService)(new ServiceLocator()).
				getService("IEntidadDataService")).getFormasPagoDesde();
	}
}