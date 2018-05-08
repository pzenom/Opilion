package es.induserco.opilion.datos.entrada;

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
import es.induserco.opilion.data.entidades.MovimientoVehiculo;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.TipoMantenimiento;
import es.induserco.opilion.data.entidades.TipoMaquina;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class RegistroEntradaDataService implements IRegistroEntradaDataService {

	//@Override
	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		return (new RegistroEntradaDAO()).addROMovimientoVehiculo(entry);
	}	

	//@Override
	public Boolean addRegistroOrden(RegistroOrden entry) throws Exception {
		return (new RegistroEntradaDAO()).addRegistroOrden(entry);
	}

	//@Override
	public Boolean addRegistroEntrada(RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return (new RegistroEntradaDAO()).addRegistroEntrada(entry,listindic,listestados);
	}

	//@Override
	public Boolean updateRegistroEntrada(RegistroEntrada entryf,RegistroEntrada entryu,List listindic,List listestados) throws Exception {
		return (new RegistroEntradaDAO()).updateRegistroEntrada(entryf,entryu,listindic,listestados);
	}

	//@Override
	public Boolean deleteRegistroEntrada(RegistroEntrada entryd) throws Exception {
		return (new RegistroEntradaDAO()).deleteRegistroEntrada(entryd);
	}

	//@Override
	public Vector getOrdenes() throws Exception {
		return (new RegistroEntradaDAO()).getOrdenes();
	}	

	//@Override
	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenes(orden, filtro);
	}	

	//@Override
	public Vector getProveedores() throws Exception {
		return (new RegistroEntradaDAO()).getProveedores();
	}

	//@Override
	public Vector getTransportistas() throws Exception {
		return (new RegistroEntradaDAO()).getTransportistas();
	}

	//@Override
	public Vector getProductos(String filtro) throws Exception {
		return (new RegistroEntradaDAO()).getProductos(filtro);
	}

	//@Override
	public Vector getProductos() throws Exception {
		return (new RegistroEntradaDAO()).getProductos();
	}

	//@Override
	public Vector getCategorias(String codigoEntrada) throws Exception {
		return (new RegistroEntradaDAO()).getCategorias(codigoEntrada);
	}

	//@Override
	public Vector getVehiculos() throws Exception {
		return (new RegistroEntradaDAO()).getVehiculos();
	}

	//@Override
	public Vector getIncidencias() throws Exception {
		return (new RegistroEntradaDAO()).getIncidencias();
	}

	//@Override
	public String getCodigoOrden() throws Exception {
		return (new RegistroEntradaDAO()).getCodigoOrden();
	}	

	//@Override
	public String getFechaRegistro() throws Exception {
		return (new RegistroEntradaDAO()).getFechaRegistro();
	}

	//@Override
	public String getFechaCaducidad() throws Exception {
		return (new RegistroEntradaDAO()).getFechaCaducidad();
	}

	//@Override
	public Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getRegistrosEntradaOrden(codigoOrden);
	}

	//@Override
	public Vector getRegistroEntradas(String codigoEntrada, Date fecha,
			Long idProducto) throws Exception {
		return (new RegistroEntradaDAO()).getRegistroEntradas(codigoEntrada,fecha,idProducto);
	}

	//@Override
	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception{
		return (new RegistroEntradaDAO()).loadRegistroEntrada(regEntrada);
	}	

	//@Override
	public Vector getFormatos() throws Exception {
		return (new RegistroEntradaDAO()).getFormatos();
	}

	//@Override
	public Vector getOperarios() throws Exception {
		return (new RegistroEntradaDAO()).getOperarios();
	}

	//@Override
	public Vector getEstadosFabas() throws Exception {
		return (new RegistroEntradaDAO()).getEstadosFabas();
	}

	//@Override
	public Boolean addAnalisisCalidadRegistro(RegistroEntrada entry,
			Long varIGL, Long varSPL, Long varDPL, Long varDAL, Long varML,
			double calidad, String baremoCalidad) throws Exception {
		return (new RegistroEntradaDAO()).addAnalisisCalidadRegistro(entry,varIGL,varSPL,varDPL,varDAL,varML,calidad,baremoCalidad);
	}	

	//@Override
	public Vector getCosechas() throws Exception {
		return (new RegistroEntradaDAO()).getCosechas();
	}

	//@Override
	public Vector getEnvases() throws Exception {
		return (new RegistroEntradaDAO()).getEnvases();
	}

	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return (new RegistroEntradaDAO()).
		addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados);
	}
	
	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry,List listindic,List listestados,String tipoProceso) throws Exception {
		return (new RegistroEntradaDAO()).
		addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados,tipoProceso);
	}

	//@Override
	public Vector getTipoUbicaciones() throws Exception {
		return (new RegistroEntradaDAO()).getTipoUbicaciones();
	}

	//@Override
	public Vector getIncidenciasFiltradas(int filtro) throws Exception {
		return (new RegistroEntradaDAO()).getIncidenciasFiltradas(filtro);
	}

	//@Override
	public Vector getCategoriasFiltradas(int filtro) throws Exception {
		return (new RegistroEntradaDAO()).getCategoriasFiltradas(filtro);
	}

	//@Override
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception {	
		return (new RegistroEntradaDAO()).addRegistrosSubEntradaProceso(regEntradaFind,mapaCategorias,proceso,cantidadProceso);
	}		

	//@Override
	public Double getSaldoRegistro(String codigoEntrada) throws Exception {
		return (new RegistroEntradaDAO()).getSaldoRegistro(codigoEntrada);
	}

	//@Override
	public Vector getREENProducto(Long idProducto) throws Exception{
		return (new RegistroEntradaDAO()).getREENProducto(idProducto);
	}

	//@Override
	public Vector getREENProducto(String codigoEan) throws Exception{
		return (new RegistroEntradaDAO()).getREENProducto(codigoEan);
	}
	
	//@Override
	public Vector getREENDetalle(String codigoEntrada) throws Exception{
		return (new RegistroEntradaDAO()).getREENDetalle(codigoEntrada);
	}

	//@Override
	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception{
		return (new RegistroEntradaDAO()).getEtiquetaRE(codigoEntrada);
	}

	//@Override
	public Boolean addMQ(Maquina maquina) throws Exception{
		return (new RegistroEntradaDAO()).addMQ(maquina);
	}	

	//@Override
	public Boolean addMT(Mantenimiento mant) throws Exception{
		return (new RegistroEntradaDAO()).addMT(mant);
	}	

	//@Override
	public Vector<TipoMantenimiento> getTipoMant()throws Exception{
		return (new RegistroEntradaDAO()).getTipoMant();
	}

	//@Override
	public Vector<Maquina> getMaquinas(long idTipo,long idMaquina, String fecha)throws Exception{
		return (new RegistroEntradaDAO()).getMaquinas(idTipo, idMaquina, fecha);
	}

	//@Override
	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception	
	{
		return (new RegistroEntradaDAO()).getRegistrosMT(idTipoMant,idMaquina,fechaConsulta);
	}

	//@Override
	public Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenEntradaTemporal(registroEntrada);
	}

	//@Override
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception {
		return (new RegistroEntradaDAO()).addRegistroEntradaTmp(registroEntrada,listindic,listestados);
	}

	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		return (new RegistroEntradaDAO()).addRegistroEntradaTmp(registroEntrada);
	}

	//@Override
	public Vector<RegistroEntrada> getRegistrosEntradaTmp(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getRegistrosEntradaTmp(codigoOrden);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		return (new RegistroEntradaDAO()).getOrdenRegistroTmp();
	}

	//@Override
	public Boolean deleteTemporales(String idOperario) throws Exception {
		return (new RegistroEntradaDAO()).deleteTemporales(idOperario);
	}

	//@Override
	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		return (new RegistroEntradaDAO()).addRegistrosTemporales(idOperario);
	}

	//@Override
	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception {
		return (new RegistroEntradaDAO()).loadRegistroEntradaTmp(regEntrada);
	}

	//@Override
	public Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		return (new RegistroEntradaDAO()).loadEstadoFabasTmp(regEntrada);
	}

	//@Override
	public Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		return (new RegistroEntradaDAO()).loadIncidenciasTmp(regEntrada);
	}

	//@Override
	public Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		return (new RegistroEntradaDAO()).loadEstadoFabas(regEntrada);
	}

	//@Override
	public Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		return (new RegistroEntradaDAO()).loadIncidencias(regEntrada);
	}

	//@Override
	public Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception {
		return (new RegistroEntradaDAO()).updateRegistroEntradaTmp(regEntradaFind,regEntradaActualiza, listindic, listestados);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaElimina) throws Exception {
		return (new RegistroEntradaDAO()).deleteRegistroEntradaTmp(regEntradaFind,regEntradaElimina);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		return (new RegistroEntradaDAO()).deleteRegistroEntradaTmp(idOperario);
	}

	//@Override
	public Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception {
		return (new RegistroEntradaDAO()).deleteOrdenEntrada(codigoEntrada);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenRegistroTmp(codigoOrden);
	}

	//@Override
	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getFechaCaducidadTmp(codigoOrden);
	}

	//@Override
	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getFechaRegistroTmp(codigoOrden);
	}

	//@Override
	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getFechaCaducidad(codigoOrden);
	}

	//@Override
	public String getFechaRegistro(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getFechaRegistro(codigoOrden);
	}

	//@Override
	public String getFechaCaducidad(String codigoOrden, Boolean temp)throws Exception {
		return (new RegistroEntradaDAO()).getFechaCaducidad(codigoOrden, temp);
	}

	//@Override
	public String getFechaRegistro(String codigoOrden, Boolean temp) throws Exception {
		return (new RegistroEntradaDAO()).getFechaRegistro(codigoOrden, temp);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenEntrada(codigoOrden);
	}

	//@Override
	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenEntradaTmp(codigoOrden);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden, Boolean temp) throws Exception {
		return (new RegistroEntradaDAO()).getOrdenEntrada(codigoOrden,temp);
	}

	//@Override
	public Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception {
		return (new RegistroEntradaDAO()).getRegistroEntradas(entrada, filtro);
	}

	//@Override
	public RegistroEntrada getRegistroEntrada(String codigoEntrada) throws Exception {
		return (new RegistroEntradaDAO()).getRegistroEntrada(codigoEntrada);
	}

	//@Override
	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception {
		return (new RegistroEntradaDAO()).getRegistrosCalidad(codigoEntrada);
	}

	//@Override
	public Boolean addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		return (new RegistroEntradaDAO()).addAnalisisCalidadRegistro(calidad);
	}

	//@Override
	public Vector getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception {
		return (new RegistroEntradaDAO()).getBultosRegistroEntrada(entrada);
	}

	//@Override
	public Boolean updateBultoRE(Bulto bulto) throws Exception {
		return (new RegistroEntradaDAO()).updateBultoRE(bulto);
	}

	//@Override
	public Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception {
		return (new RegistroEntradaDAO()).addBultoRE(idEntrada,numBulto,peso);
	}

	//@Override
	public Boolean addBultoRE(Bulto bulto) throws Exception {
		return (new RegistroEntradaDAO()).addBultoRE(bulto);
	}

	//@Override
	public Boolean inicializaBultosRE(Bulto bulto, double cantidadTotal) throws Exception {
		return (new RegistroEntradaDAO()).inicializaBultosRE(bulto, cantidadTotal);
	}

	//@Override
	public Vector<TipoMaquina> getTiposMaquinas() throws Exception {
		return (new RegistroEntradaDAO()).getTiposMaquinas();
	}

	//@Override
	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion, long idTipo, long idMaquina) throws Exception {
		return (new RegistroEntradaDAO()).getMantenimientos(idMantenimientoProgramacion, idTipo, idMaquina);
	}

	//@Override
	public Vector<Ciclo> getCiclos() throws Exception {
		return (new RegistroEntradaDAO()).getCiclos();
	}

	//@Override
	public boolean addMTProgramado(Mantenimiento mant) throws Exception {
		return (new RegistroEntradaDAO()).addMTProgramado(mant);
	}

	//@Override
	public void inseRegMT(Mantenimiento mant) throws Exception {
		(new RegistroEntradaDAO()).inseRegMT(mant);		
	}

	//@Override
	public void devolucion(RegistroEntrada entry) throws Exception {
		(new RegistroEntradaDAO()).devolucion(entry);		
	}

	//@Override
	public void checkMT() throws Exception {
		(new RegistroEntradaDAO()).checkMT();		
	}

	//@Override
	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception {
		return (new RegistroEntradaDAO()).getDevoluciones(fecha);		
	}

	//@Override
	public Vector<Mantenimiento> getMantenimientosProgramados(long idTipoMant, long idMaquina, String fechaConsulta) throws Exception {
		return (new RegistroEntradaDAO()).
			getMantenimientosProgramados(idTipoMant, idMaquina, fechaConsulta);		
	}

	//@Override
	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception {
		return (new RegistroEntradaDAO()).updateRegistroOrden(entry);		
	}

	//@Override
	public String generarCodigoEntrada() throws Exception {
		return (new RegistroEntradaDAO()).generarCodigoEntrada();		
	}

	//@Override
	public void reaprovecharDevolucion(LineaProducto linea) throws Exception {
		(new RegistroEntradaDAO()).reaprovecharDevolucion(linea);		
	}

	//@Override
	public Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception {
		return (new RegistroEntradaDAO()).getDevolucionesLote(lote);		
	}
}