package es.induserco.opilion.negocio.gestionentidades;

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
import es.induserco.opilion.negocio.EntidadDataHelper;

public class GestionEntidadesBB implements IGestionEntidadesService{

	public Vector getEntidades(String filtroConsulta) throws Exception{	
		//Recuperamos la lista de entidades
		EntidadDataHelper edh = new EntidadDataHelper();
		Vector entidades = edh.getEntidades(filtroConsulta);
		//si hay que aplicar alguna regla del negocio aqui!!!
		return entidades;
	}

	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate) throws Exception {
		return new EntidadDataHelper().updateEntidad(entidadFind, entidadUpdate);
	}

	public Entidad loadEntidad(Entidad entry) throws Exception {
		return new EntidadDataHelper().loadEntidad(entry);
	}	

	public String getFechaRegistro() throws Exception{
		return new EntidadDataHelper().getFechaRegistro();
	}

	public Vector getProvincias() throws Exception{
		return new EntidadDataHelper().getProvincias();
	}

	public Vector getFormasPago() throws Exception{
		return new EntidadDataHelper().getFormasPago();
	}	

	public Vector getListaDiasEntrega() throws Exception{
		return new EntidadDataHelper().getListaDiasEntrega();
	}

	public Vector getListaRutas() throws Exception{
		return new EntidadDataHelper().getListaRutas();
	}

	public Vector getListaSectores() throws Exception{
		return new EntidadDataHelper().getListaSectores();
	}

	public Vector getListaBancos() throws Exception{
		return new EntidadDataHelper().getListaBancos();
	}

	public Vector getListaTiposProveedores() throws Exception{
		return new EntidadDataHelper().getListaTiposProveedores();
	}

	public Vector getListaReqHomologa() throws Exception {
		return new EntidadDataHelper().getListaReqHomologa();
	}

	public Vector getListaReqHomologa(Entidad entidad) throws Exception {
		return new EntidadDataHelper().getListaReqHomologa(entidad);
	}

	public Boolean deshabilitaEntidad(Entidad entidadFind) throws Exception {
		return new EntidadDataHelper().deshabilitaEntidad(entidadFind);		
	}

	//@Override
	public Vector<Entidad> getProveedores(Entidad entry) throws Exception{
		return new EntidadDataHelper().getProveedores(entry);
	}	

	//@Override
	public Vector<Entidad> getClientes(Entidad entry) throws Exception{
		return new EntidadDataHelper().getClientes(entry);
	}

	//@Override
	public Entidad getEntidad(long id) throws Exception {
		Entidad entry = new EntidadDataHelper().getEntidad(id);			
		return entry;
	}

	//@Override
	public String getNombreFormaPago(long idFormaDePago) throws Exception {
		return new EntidadDataHelper().getNombreFormaPago(idFormaDePago);
	}

	//@Override
	public void registrarBanco(Banco banco) throws Exception {
		new EntidadDataHelper().registrarBanco(banco);
	}

	//@Override
	public void registrarFormaPago(FormaPago formaPago) throws Exception {
		new EntidadDataHelper().registrarFormaPago(formaPago);
	}

	//@Override
	public void registrarSector(Sector sector) throws Exception {
		new EntidadDataHelper().registrarSector(sector);
	}

	//@Override
	public Boolean addEntidad(Entidad entry) throws Exception {
		return new EntidadDataHelper().addEntidad(entry);	
	}

	//@Override
	public Vector<DatoBancario> getFormasPagoEntidad(Entidad entidad) throws Exception{
		return new EntidadDataHelper().getFormasPagoEntidad(entidad);	
	}

	//@Override
	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception {
		return new EntidadDataHelper().getDireccionesEntidad(entidad, tipoDireccion);
	}

	//@Override
	public Vector<Order> getPedidosCliente(long idUsuario) throws Exception {
		return new EntidadDataHelper().getPedidosCliente(idUsuario);	
	}

	//@Override
	public Empresa getEmpresa(long idEmpresa) throws Exception {
		return new EntidadDataHelper().getEmpresa(idEmpresa);
	}

	//@Override
	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception {
		new EntidadDataHelper().guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
	}

	//@Override
	public GestionRoles getRoles() throws Exception {
		return new EntidadDataHelper().getRoles();
	}

	//@Override
	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception {
		return new EntidadDataHelper().getFormasPagoDesde();
	}
}