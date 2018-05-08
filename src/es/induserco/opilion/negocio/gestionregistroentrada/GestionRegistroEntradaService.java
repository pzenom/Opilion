package es.induserco.opilion.negocio.gestionregistroentrada;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Ciclo;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.TipoMantenimiento;
import es.induserco.opilion.data.entidades.TipoMaquina;
import es.induserco.opilion.data.entidades.MovimientoVehiculo;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class GestionRegistroEntradaService implements IGestionRegistroEntradaService{

	//@Override
	public String getCodigoOrden() throws Exception {
		return new GestionRegistroEntradaBB().getCodigoOrden();
	}	

	//@Override
	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		return new GestionRegistroEntradaBB().addROMovimientoVehiculo(entry);
	}	

	//@Override
	public Boolean addRegistroOrden(RegistroOrden entry) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroOrden(entry);
	}

	//@Override
	public Boolean addRegistroEntrada(RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroEntrada(entry,listindic,listestados);
	}

	//@Override
	public Boolean updateRegistroEntrada(RegistroEntrada entryf,RegistroEntrada entryu,List listindic,List listestados) throws Exception {
		return new GestionRegistroEntradaBB().updateRegistroEntrada(entryf,entryu,listindic,listestados);
	}

	//@Override
	public Boolean deleteRegistroEntrada(RegistroEntrada entryd) throws Exception {
		return new GestionRegistroEntradaBB().deleteRegistroEntrada(entryd);
	}

	//@Override
	public Vector getOrdenes() throws Exception {
		return new GestionRegistroEntradaBB().getOrdenes();
	}	

	//@Override	
	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {
		return new GestionRegistroEntradaBB().getOrdenes(orden, filtro);
	}	

	//@Override	
	public Vector getProveedores() throws Exception {
		return new GestionRegistroEntradaBB().getProveedores();
	}

	//@Override
	public Vector getTransportistas() throws Exception {
		return new GestionRegistroEntradaBB().getTransportistas();
	}

	//@Override
	public Vector getProductos(String filtro) throws Exception {
		return new GestionRegistroEntradaBB().getProductos(filtro);
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroEntradaBB().getProductos();
	}

	//@Override
	public Vector getCategorias(String codigoEntrada) throws Exception {
		return new GestionRegistroEntradaBB().getCategorias(codigoEntrada);
	}

	//@Override
	public Vector getVehiculos() throws Exception {
		return new GestionRegistroEntradaBB().getVehiculos();
	}

	//@Override
	public Vector getIncidencias() throws Exception {
		return new GestionRegistroEntradaBB().getIncidencias();
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroEntradaBB().getFechaRegistro();
	}

	//@Override
	public String getFechaCaducidad() throws Exception {
		return new GestionRegistroEntradaBB().getFechaCaducidad();
	}

	//@Override
	public Vector getRegistroEntradas(String codigoEntrada, Date fecha,Long idProducto) throws Exception {
		return new GestionRegistroEntradaBB().getRegistroEntradas(codigoEntrada,fecha,idProducto);
	}

	//@Override
	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception{
		return new GestionRegistroEntradaBB().loadRegistroEntrada(regEntrada);
	}	

	//@Override
	public Vector getFormatos() throws Exception {
		return new GestionRegistroEntradaBB().getFormatos();
	}

	//@Override
	public Vector getOperarios() throws Exception {
		return new GestionRegistroEntradaBB().getOperarios();
	}

	//@Override
	public Vector getEstadosFabas() throws Exception {
		return new GestionRegistroEntradaBB().getEstadosFabas();
	}

	//@Override
	public boolean addAnalisisCalidadRegistro(RegistroEntrada entry,
			Long varIGL, Long varSPL, Long varDPL, Long varDAL, Long varML,
			double calidad, String baremoCalidad) throws Exception {
		return new GestionRegistroEntradaBB().addAnalisisCalidadRegistro(entry,
				varIGL, varSPL, varDPL, varDAL, varML,
				calidad, baremoCalidad);
	}	

	//@Override
	public Vector getCosechas() throws Exception {
		return new GestionRegistroEntradaBB().getCosechas();
	}

	//@Override
	public Vector getEnvases() throws Exception {
		return new GestionRegistroEntradaBB().getEnvases();
	}

	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados);
	}

	//@Override
	public Vector getTipoUbicaciones() throws Exception {
		return new GestionRegistroEntradaBB().getTipoUbicaciones();
	}

	//@Override
	public Vector getIncidenciasFiltradas(int filtro) throws Exception {
		return new GestionRegistroEntradaBB().getIncidenciasFiltradas(filtro);
	}

	//@Override
	public Vector getCategoriasFiltradas(int filtro) throws Exception {
		return new GestionRegistroEntradaBB().getCategoriasFiltradas(filtro);
	}

	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry,List listindic,List listestados,String tipoProceso) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados,tipoProceso);
	}

	//@Override
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception {	
		return new GestionRegistroEntradaBB().addRegistrosSubEntradaProceso(regEntradaFind, mapaCategorias,proceso,cantidadProceso);
	}		

	//@Override
	public Double getSaldoRegistro(String codigoEntrada) throws Exception {	
		return new GestionRegistroEntradaBB().getSaldoRegistro(codigoEntrada);
	}

	//@Override
	public Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getRegistrosEntradaOrden(codigoOrden);
	}	

	//@Override
	public Vector getREENProducto(Long idProducto) throws Exception{	
		return new GestionRegistroEntradaBB().getREENProducto(idProducto);
	}		

	//@Override
	public Vector getREENProducto(String codigoEan) throws Exception{	
		return new GestionRegistroEntradaBB().getREENProducto(codigoEan);
	}	

	//@Override
	public Vector getREENDetalle(String codigoEntrada) throws Exception{	
		return new GestionRegistroEntradaBB().getREENDetalle(codigoEntrada);
	}	

	//@Override
	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception{	
		return new GestionRegistroEntradaBB().getEtiquetaRE(codigoEntrada);
	}	

	//@Override
	public Boolean addMQ(Maquina maquina) throws Exception{	
		return new GestionRegistroEntradaBB().addMQ(maquina);
	}	

	//@Override
	public Boolean addMT(Mantenimiento mant) throws Exception{	
		return new GestionRegistroEntradaBB().addMT(mant);
	}	

	//@Override
	public Vector<TipoMantenimiento> getTipoMant() throws Exception{	
		return new GestionRegistroEntradaBB().getTipoMant();
	}	

	//@Override
	public Vector<Maquina> getMaquinas(long idTipo,long idMaquina, String fecha) throws Exception{	
		return new GestionRegistroEntradaBB().getMaquinas(idTipo,idMaquina, fecha);
	}	

	//@Override
	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception{	
		return new GestionRegistroEntradaBB().getRegistrosMT(idTipoMant,idMaquina,fechaConsulta);
    }

	//@Override
	public Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception {
		return new GestionRegistroEntradaBB().getOrdenEntradaTemporal(registroEntrada);
	}

	//@Override
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroEntradaTmp(registroEntrada,listindic,listestados);
	}

	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		return new GestionRegistroEntradaBB().addRegistroEntradaTmp(registroEntrada);
	}

	//@Override
	public Vector<RegistroEntrada> getRegistrosEntradaTmp(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getRegistrosEntradaTmp(codigoOrden);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		return new GestionRegistroEntradaBB().getOrdenRegistroTmp();
	}

	//@Override
	public Boolean deleteTemporales(String idOperario) throws Exception {
		return new GestionRegistroEntradaBB().deleteTemporales(idOperario);
	}

	//@Override
	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		return new GestionRegistroEntradaBB().addRegistrosTemporales(idOperario);
	}

	//@Override
	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception {
		return new GestionRegistroEntradaBB().loadRegistroEntradaTmp(regEntrada);
	}

	//@Override
	public Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		return new GestionRegistroEntradaBB().loadEstadoFabasTmp(regEntrada);
	}

	//@Override
	public Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		return new GestionRegistroEntradaBB().loadIncidenciasTmp(regEntrada);
	}

	//@Override
	public Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		return new GestionRegistroEntradaBB().loadEstadoFabas(regEntrada);
	}

	//@Override
	public Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		return new GestionRegistroEntradaBB().loadIncidencias(regEntrada);
	}

	//@Override
	public Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception {
		return new GestionRegistroEntradaBB().updateRegistroEntradaTmp(regEntradaFind, regEntradaActualiza, listindic, listestados);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaElimina) throws Exception {
		return new GestionRegistroEntradaBB().deleteRegistroEntradaTmp(regEntradaFind, regEntradaElimina);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		return new GestionRegistroEntradaBB().deleteRegistroEntradaTmp(idOperario);
	}

	//@Override
	public Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception {
		return new GestionRegistroEntradaBB().deleteOrdenEntrada(codigoEntrada);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getOrdenRegistroTmp(codigoOrden);
	}

	//@Override
	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getFechaCaducidadTmp(codigoOrden);
	}

	//@Override
	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getFechaRegistroTmp(codigoOrden);
	}

	//@Override
	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getFechaCaducidad(codigoOrden);
	}

	//@Override
	public String getFechaRegistro(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getFechaRegistro(codigoOrden);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getOrdenEntrada(codigoOrden);
	}

	//@Override
	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return new GestionRegistroEntradaBB().getOrdenEntradaTmp(codigoOrden);
	}

	//@Override
	public Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception {
		return new GestionRegistroEntradaBB().getRegistroEntradas(entrada, filtro);
	}

	//@Override
	public RegistroEntrada getRegistroEntrada(String codigoEntrada) throws Exception {
		return new GestionRegistroEntradaBB().getRegistroEntrada(codigoEntrada);
	}

	//@Override
	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception {
		return new GestionRegistroEntradaBB().getRegistrosCalidad (codigoEntrada);
	}

	//@Override
	public Double addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		return new GestionRegistroEntradaBB().addAnalisisCalidadRegistro (calidad);
	}

	//@Override
	public Vector getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception {
		return new GestionRegistroEntradaBB().getBultosRegistroEntrada (entrada);
	}

	//@Override
	public Boolean updateBultoRE(Bulto bulto) throws Exception {
		return new GestionRegistroEntradaBB().updateBultoRE (bulto);
	}

	//@Override
	public Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception {
		return new GestionRegistroEntradaBB().addBultoRE(idEntrada,numBulto,peso);
	}	

	//@Override
	public Boolean addBultoRE(Bulto bulto) throws Exception {
		return new GestionRegistroEntradaBB().addBultoRE(bulto);
	}

	//@Override
	public Boolean inicializaBultosRE(Bulto bulto, double total) throws Exception {
		return new GestionRegistroEntradaBB().inicializaBultosRE(bulto, total);
	}

	//@Override
	public Vector<TipoMaquina> getTiposMaquinas() throws Exception {
		return new GestionRegistroEntradaBB().getTiposMaquinas();
	}
	
	//@Override
	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion, long idTipo, long idMaquina) throws Exception {
		return new GestionRegistroEntradaBB().getMantenimientos(idMantenimientoProgramacion, idTipo, idMaquina);
	}

	//@Override
	public Vector<Ciclo> getCiclos() throws Exception {
		return new GestionRegistroEntradaBB().getCiclos();
	}

	//@Override
	public boolean addMTProgramado(Mantenimiento entry) throws Exception {
		return new GestionRegistroEntradaBB().addMTProgramado(entry);
	}

	//@Override
	public void inseRegMT(Mantenimiento entry) throws Exception {
		new GestionRegistroEntradaBB().inseRegMT(entry);
	}

	//@Override
	public void devolucion(RegistroEntrada entry) throws Exception {
		new GestionRegistroEntradaBB().devolucion(entry);
	}

	//@Override
	public void checkMT() throws Exception {
		new GestionRegistroEntradaBB().checkMT();
	}

	//@Override
	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception {
		return new GestionRegistroEntradaBB().getDevoluciones(fecha);
	}

	//@Override
	public Vector<Mantenimiento> getMantenimientosProgramados(long tipo,
			long idMaquina, String fechaConsulta) throws Exception {
		return new GestionRegistroEntradaBB().
			getMantenimientosProgramados(tipo, idMaquina, fechaConsulta);
	}

	//@Override
	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception {
		return new GestionRegistroEntradaBB().updateRegistroOrden(entry);
	}

	//@Override
	public String generarCodigoEntrada() throws Exception {
		return new GestionRegistroEntradaBB().generarCodigoEntrada();
	}

	//@Override
	public void reaprovecharDevolucion(LineaProducto linea) throws Exception {
		new GestionRegistroEntradaBB().reaprovecharDevolucion(linea);
	}

	//@Override
	public Vector<RegistroEntrada> getDevolucionesLote(String lote)
			throws Exception {
		return new GestionRegistroEntradaBB().getDevolucionesLote(lote);
	}
}