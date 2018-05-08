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

public class GestionEntidadesService implements IGestionEntidadesService{

	//@Override
	public Vector getEntidades(String filtroConsulta) throws Exception{
		return new GestionEntidadesBB().getEntidades(filtroConsulta);
	}	

	//@Override
	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate) throws Exception{
		return new GestionEntidadesBB().updateEntidad(entidadFind, entidadUpdate);
	}

	//@Override
	public Entidad loadEntidad(Entidad entry) throws Exception {
		return new GestionEntidadesBB().loadEntidad(entry);		
	}

	public String getFechaRegistro()throws Exception{
		return new GestionEntidadesBB().getFechaRegistro();
	}

	public Vector getProvincias()throws Exception{
		return new GestionEntidadesBB().getProvincias();
	}

	public Vector getFormasPago()throws Exception{
		return new GestionEntidadesBB().getFormasPago();
	}

	//@Override
	public Boolean addEntidad(Entidad entry) throws Exception {
		return new GestionEntidadesBB().addEntidad(entry);
	}

	public Vector getListaDiasEntrega()throws Exception{
		return new GestionEntidadesBB().getListaDiasEntrega();
	}	

	public Vector getListaRutas()throws Exception{
		return new GestionEntidadesBB().getListaRutas();
	}	

	public Vector getListaSectores()throws Exception{
		return new GestionEntidadesBB().getListaSectores();				
	}	

	public Vector getListaBancos()throws Exception{
		return new GestionEntidadesBB().getListaBancos();
	}	

	public Vector getListaTiposProveedores()throws Exception{
		return new GestionEntidadesBB().getListaTiposProveedores();
	}	

	public Vector getListaReqHomologa()throws Exception{
		return new GestionEntidadesBB().getListaReqHomologa();			
	}

	public Vector getListaReqHomologa(Entidad entidad)throws Exception{
		return new GestionEntidadesBB().getListaReqHomologa(entidad);			
	}	

	//@Override
	public Boolean deshabilitaEntidad(Entidad entidadFind) throws Exception{
		return new GestionEntidadesBB().deshabilitaEntidad(entidadFind);
	}

	//@Override
	public Vector<Entidad> getProveedores(Entidad entry) throws Exception{
		return new GestionEntidadesBB().getProveedores(entry);
	}

	//@Override
	public Vector<Entidad> getClientes(Entidad entry) throws Exception{
		return new GestionEntidadesBB().getClientes(entry);
	}

	//@Override
	public Entidad getEntidad(long id) throws Exception {
		return new GestionEntidadesBB().getEntidad(id);
	}

	//@Override
	public String getNombreFormaPago(long idFormaDePago) throws Exception {
		return new GestionEntidadesBB().getNombreFormaPago(idFormaDePago);
	}

	//@Override
	public void registrarBanco(Banco banco) throws Exception {
		new GestionEntidadesBB().registrarBanco(banco);
	}

	//@Override
	public void registrarFormaPago(FormaPago formaPago) throws Exception {
		new GestionEntidadesBB().registrarFormaPago(formaPago);
	}

	//@Override
	public void registrarSector(Sector sector) throws Exception {
		new GestionEntidadesBB().registrarSector(sector);
	}

	//@Override
	public Vector<DatoBancario> getFormasPagoEntidad(Entidad entidad)
			throws Exception {
		return new GestionEntidadesBB().getFormasPagoEntidad(entidad);
	}

	//@Override
	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception {
		return new GestionEntidadesBB().getDireccionesEntidad(entidad, tipoDireccion);
	}

	//@Override
	public Vector<Order> getPedidosCliente(long idUsuario) throws Exception {
		return new GestionEntidadesBB().getPedidosCliente(idUsuario);
	}

	//@Override
	public Empresa getEmpresa(long idEmpresa) throws Exception {
		return new GestionEntidadesBB().getEmpresa(idEmpresa);
	}

	//@Override
	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception {
		new GestionEntidadesBB().guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
	}

	//@Override
	public GestionRoles getRoles() throws Exception {
		return new GestionEntidadesBB().getRoles();
	}

	//@Override
	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception {
		return new GestionEntidadesBB().getFormasPagoDesde();
	}
}