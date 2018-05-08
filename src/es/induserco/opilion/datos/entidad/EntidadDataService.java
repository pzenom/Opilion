package es.induserco.opilion.datos.entidad;

import java.util.Vector;

import es.induserco.edifact.data.Order;
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

public class EntidadDataService implements IEntidadDataService {

	//@Override
	public Boolean addEntidad(Entidad entry) throws Exception {
		return (new EntidadDAO()).addEntidad(entry);
	}

	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate)throws Exception{
		return (new EntidadDAO()).updateEntidad(entidadFind, entidadUpdate);
	}

	//@Override
	public Entidad loadEntidad(Entidad entry) throws Exception {
		return (new EntidadDAO()).loadEntidad(entry);
	}
	
	public Vector getEntidades(String filtroConsulta)throws Exception{
		return (new EntidadDAO()).getEntidades(filtroConsulta);
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return (new EntidadDAO()).getFechaRegistro();
	}

	//@Override
	public Vector getFormasPago() throws Exception {
		return (new EntidadDAO()).getFormasPago();
	}

	//@Override
	public Vector getProvincias() throws Exception {
		return (new EntidadDAO()).getProvincias();
	}

	//@Override
	public Vector getListaDiasEntrega() throws Exception {
		return (new EntidadDAO()).getListaDiasEntrega();
	}

	//@Override
	public Vector getListaRutas() throws Exception {
		return (new EntidadDAO()).getListaRutas();
	}

	//@Override
	public Vector getListaSectores() throws Exception {
		return (new EntidadDAO()).getListaSectores();
	}

	//@Override
	public Vector getListaBancos() throws Exception {
		return (new EntidadDAO()).getListaBancos();
	}

	//@Override
	public Vector getListaTiposProveedores() throws Exception {
		return (new EntidadDAO()).getListaTiposProveedores();
	}

	//@Override
	public Vector getListaReqHomologa() throws Exception {
		return (new EntidadDAO()).getListaReqHomologa();
	}

	//@Override
	public Vector getListaReqHomologa(Entidad entidad) throws Exception {
		return (new EntidadDAO()).getListaReqHomologa(entidad);
	}	

	//@Override
	public Boolean deshabilitaEntidad(Entidad entidadFind)
			throws Exception {
		return (new EntidadDAO()).deshabilitaEntidad(entidadFind);
	}

	//@Override
	public Vector<Entidad> getProveedores(Entidad entry) throws Exception{
		return (new EntidadDAO()).getProveedores(entry);
	}

	//@Override
	public Vector<Entidad> getClientes(Entidad entry) throws Exception{
		return (new EntidadDAO()).getClientes(entry);
	}

	//@Override
	public Entidad getEntidad(long id) throws Exception {
		return (new EntidadDAO()).getEntidad(id);
	}

	//@Override
	public String getNombreFormaPago(long idFormaDePago) throws Exception {
		return (new EntidadDAO()).getNombreFormaPago(idFormaDePago);
	}

	//@Override
	public void registrarBanco(Banco banco) throws Exception {
		(new EntidadDAO()).registrarBanco(banco);
	}

	//@Override
	public void registrarFormaPago(FormaPago formaPago) throws Exception {
		(new EntidadDAO()).registrarFormaPago(formaPago);
	}

	//@Override
	public void registrarSector(Sector sector) throws Exception {
		(new EntidadDAO()).registrarSector(sector);
	}

	//@Override
	public Vector<DatoBancario> getFormasPagoEntidad(Entidad entidad)
			throws Exception {
		return (new EntidadDAO()).getFormasPagoEntidad(entidad);
	}

	//@Override
	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception {
		return new EntidadDAO().getDireccionesEntidad(entidad, tipoDireccion);
	}

	//@Override
	public Vector<Order> getPedidosCliente(long idCliente) throws Exception {
		return new EntidadDAO().getPedidosCliente(idCliente);
	}

	//@Override
	public Empresa getEmpresa(long idEmpresa) throws Exception {
		return new EntidadDAO().getEmpresa(idEmpresa);
	}

	//@Override
	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception {
		new EntidadDAO().guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
	}

	//@Override
	public GestionRoles getRoles() throws Exception {
		return new EntidadDAO().getRoles();
	}

	//@Override
	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception {
		return new EntidadDAO().getFormasPagoDesde();
	}
}