package es.induserco.opilion.presentacion;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.infraestructura.ServiceLocator;

import es.induserco.opilion.data.comun.Empresa;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.Banco;
import es.induserco.opilion.data.comun.banca.FormaPago;
import es.induserco.opilion.data.comun.banca.FormaPagoDesde;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.data.entidades.Accion;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.negocio.gestionentidades.IGestionEntidadesService;
import es.induserco.opilion.data.entidades.GestionRoles;

public class GestionEntidadesHelper {

	public Vector getEntidades(String filtroConsulta) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getEntidades(filtroConsulta);
	}

	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate)throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).updateEntidad(entidadFind, entidadUpdate);
	}

	public Entidad loadEntidad(Entidad clienteFind) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).loadEntidad(clienteFind);
	}

	public String getFechaRegistro() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getFechaRegistro();
	}

	public Vector getProvincias() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getProvincias();
	}

	public Boolean addEntidad(Entidad entry) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).addEntidad(entry);
	}

	public Vector getListaDiasEntrega() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaDiasEntrega();
	}

	public Vector getListaRutas() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaRutas();
	}

	public Vector getListaSectores() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaSectores();
	}

	public Vector getListaBancos() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaBancos();
	}

	public Vector getListaTiposProveedores() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaTiposProveedores();
	}

	public Boolean deshabilitaEntidad(Entidad entidadFind)throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).deshabilitaEntidad(entidadFind);
	}

	public Vector<Entidad> getProveedores(Entidad entry) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getProveedores(entry);
	}	

	public Vector<Entidad> getClientes(Entidad entry) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getClientes(entry);
	}

	public Entidad getEntidad(long id) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getEntidad(id);
	}

	public Vector getListaReqHomologa() throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaReqHomologa();
	}

	public Vector getListaReqHomologa(Entidad entidad) throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getListaReqHomologa(entidad);
	}

	public String getNombreFormaPago(long idFormaDePago) throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getNombreFormaPago(idFormaDePago);
	}

	public void registrarSector(Sector sector) throws Exception {
		((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).registrarSector(sector);
	}

	public void registrarFormaPago(FormaPago formaPago) throws Exception {
		((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).registrarFormaPago(formaPago);
	}

	public void registrarBanco(Banco banco)  throws Exception {
		((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).registrarBanco(banco);
	}

	public Vector<FormaPago> getFormasPago() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getFormasPago();
	}

	public Vector<DatoBancario> getFormasPagoEntidad(Entidad cliente) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getFormasPagoEntidad(cliente);
	}

	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getDireccionesEntidad(entidad, tipoDireccion);
	}

	public Vector<Order> getPedidosCliente(long idUsuario) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getPedidosCliente(idUsuario);
	}
	
	public Empresa getEmpresa(long idEmpresa) throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getEmpresa(idEmpresa);
	}

	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception{
		((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
	}

	public GestionRoles getRoles()throws Exception {
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getRoles();
	}

	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception{
		return ((IGestionEntidadesService)(new ServiceLocator()).
				getService("IGestionEntidadesService")).getFormasPagoDesde();
	}
}