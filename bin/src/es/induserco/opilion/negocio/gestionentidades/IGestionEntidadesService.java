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

public interface IGestionEntidadesService {

	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate)throws Exception;
	public Entidad loadEntidad(Entidad clienteFind) throws Exception;
	public Boolean addEntidad(Entidad entry) throws Exception;
	public Vector getEntidades(String filtroConsulta) throws Exception;
	public String getFechaRegistro()throws Exception;
	public Vector getProvincias()throws Exception;
	public Vector getListaDiasEntrega()throws Exception;
	public Vector getListaRutas()throws Exception;
	public Vector getListaSectores()throws Exception;
	public Vector getListaBancos()throws Exception;
	public Vector getListaTiposProveedores()throws Exception;
	public Boolean deshabilitaEntidad(Entidad entidadFind)throws Exception;
	public Vector<Entidad> getProveedores(Entidad entry) throws Exception;
	public Vector<Entidad> getClientes(Entidad entry) throws Exception;
	public Vector getListaReqHomologa()throws Exception;
	public Vector getListaReqHomologa(Entidad entidad) throws Exception;
	public String getNombreFormaPago(long idFormaDePago) throws Exception;
	public void registrarSector(Sector sector) throws Exception;
	public void registrarFormaPago(FormaPago formaPago) throws Exception;
	public void registrarBanco(Banco banco) throws Exception;
	public Vector<FormaPago> getFormasPago()throws Exception;
	public Vector<DatoBancario> getFormasPagoEntidad(Entidad cliente) throws Exception;
	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception;
	public Vector<Order> getPedidosCliente(long idUsuario) throws Exception;
	Entidad getEntidad(long id) throws Exception;
	public Empresa getEmpresa(long idEmpresa) throws Exception;
	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception;
	public GestionRoles getRoles() throws Exception;
	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception;
}