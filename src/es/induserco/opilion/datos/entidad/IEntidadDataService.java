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
import es.induserco.opilion.data.entidades.*;

public interface IEntidadDataService {

	Boolean addEntidad(Entidad entry) throws Exception;
	Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate) throws Exception;
	Entidad loadEntidad(Entidad entry)throws Exception;
	Vector getEntidades(String filtroConsulta)throws Exception;
	String getFechaRegistro()throws Exception;
	Vector getProvincias()throws Exception;
	Vector getFormasPago()throws Exception;
	Vector getListaDiasEntrega()throws Exception;
	Vector getListaRutas()throws Exception;
	Vector getListaSectores()throws Exception;
	Vector getListaBancos()throws Exception;
	Vector getListaTiposProveedores()throws Exception;
	Vector getListaReqHomologa()throws Exception;
	Vector getListaReqHomologa(Entidad entidad)throws Exception;
	Boolean deshabilitaEntidad(Entidad entidadFind)throws Exception;
	Vector<Entidad> getProveedores(Entidad entry) throws Exception;
	Vector<Entidad> getClientes(Entidad entry) throws Exception;
	String getNombreFormaPago(long idFormaDePago) throws Exception;
	void registrarSector(Sector sector) throws Exception;
	void registrarFormaPago(FormaPago formaPago) throws Exception;
	void registrarBanco(Banco banco) throws Exception;
	Vector<DatoBancario> getFormasPagoEntidad(Entidad e) throws Exception;
	Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDireccion) throws Exception;
	Vector<Order> getPedidosCliente(long idCliente) throws Exception;
	Entidad getEntidad(long id) throws Exception;
	Empresa getEmpresa(long idEmpresa) throws Exception;
	void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception;
	GestionRoles getRoles() throws Exception;
	Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception;
}