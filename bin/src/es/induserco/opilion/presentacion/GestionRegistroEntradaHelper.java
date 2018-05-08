package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.Map;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Ciclo;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.MovimientoVehiculo;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.TipoMantenimiento;
import es.induserco.opilion.data.entidades.TipoMaquina;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService;

public class GestionRegistroEntradaHelper{

	public String getCodigoOrden() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getCodigoOrden();
	}

	public String getFechaRegistro() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaRegistro();
	}

	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addROMovimientoVehiculo(entry);
	}	

	public Boolean addRegistroOrden(RegistroOrden entry) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroOrden(entry);
	}

	public Boolean addRegistroEntrada(RegistroEntrada entry,List listindic, List listestados) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroEntrada(entry,listindic,listestados);
	}

	public Boolean updateRegistroEntrada(RegistroEntrada entryf,RegistroEntrada entryu, List listindic, List listestados) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).updateRegistroEntrada(entryf,entryu,listindic,listestados);
	}

	public Boolean deleteRegistroEntrada(RegistroEntrada entryf) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).
				getService("IGestionRegistroEntradaService")).deleteRegistroEntrada(entryf);
	}

	public Vector getOrdenes() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenes();
	}

	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenes(orden, filtro);
	}	

	public Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistrosEntradaOrden(codigoOrden);
	}

	public Vector getProveedores() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getProveedores();
	}

	public Vector getTransportistas() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getTransportistas();
	}

	public Vector getOperarios() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOperarios();
	}

	public Vector getProductos(String filtro) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getProductos(filtro);
	}

	public Vector getEstadosFabas() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getEstadosFabas();
	}

	public Vector getCategorias(String codigoEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)
				(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getCategorias(codigoEntrada);
	}

	public Vector getFormatos() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFormatos();
	}

	public Vector getVehiculos() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getVehiculos();
	}

	public Vector getIncidencias() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getIncidencias();
	}

	public Vector<RegistroEntrada> getRegistroEntradas(String codigoEntrada,Date fecha,Long idProducto) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistroEntradas(codigoEntrada,fecha,idProducto);
	}

	public boolean addAnalisisCalidadRegistro(RegistroEntrada entry, Long varIGL, 
			Long varSPL, Long varDPL, Long varDAL, Long varML, double calidad,
			String baremoCalidad) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addAnalisisCalidadRegistro(entry, varIGL,
				varSPL, varDPL, varDAL, varML, calidad,baremoCalidad);
	}

	public String getFechaCaducidad() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaCaducidad();
	}

	public Vector getCosechas() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getCosechas();
	}

	public Vector getProductos() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getProductos();
	}

	public Vector getEnvases() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getEnvases();
	}

	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry, List listindic, List listestados) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados);
	}

	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry, List listindic, List listestados, String tipoProceso) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados,tipoProceso);
	}

	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadRegistroEntrada(regEntrada);
	}	
	
	//sirve para agregar los SRE al GP
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception {	
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistrosSubEntradaProceso(regEntradaFind, mapaCategorias,proceso,cantidadProceso);
	}	

	public Vector getTipoUbicaciones() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getTipoUbicaciones();
	}

	public Vector getIncidenciasFiltradas(int filtro) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getIncidenciasFiltradas(filtro);
	}

	public Vector getCategoriasFiltradas(int filtro) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getCategoriasFiltradas(filtro);
	}

	public Double getSaldoRegistro(String codigoEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getSaldoRegistro(codigoEntrada);
	}

	public Vector getREENProducto(Long idProducto) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).
				getService("IGestionRegistroEntradaService")).getREENProducto(idProducto);
	}

	public Vector getREENProducto(String codigoEan) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getREENProducto(codigoEan);
	}

	public Vector getREENDetalle(String codigoEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).
				getService("IGestionRegistroEntradaService")).getREENDetalle(codigoEntrada);
	}

	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getEtiquetaRE(codigoEntrada);
	}	

	//Maquinarias
	public Boolean addMQ(Maquina maquina) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addMQ(maquina);
	}

	public Boolean addMT(Mantenimiento mant) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addMT(mant);
	}

	public Vector<TipoMantenimiento> getTipoMant() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getTipoMant();
	}

	public Vector<Maquina> getMaquinas(long idTipo, long idMaquina, String fecha) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).
				getService("IGestionRegistroEntradaService")).getMaquinas(idTipo, idMaquina, fecha);
	}

	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistrosMT(idTipoMant,idMaquina,fechaConsulta);
	}

	public Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenEntradaTemporal(registroEntrada);
	}

	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroEntradaTmp(registroEntrada,listindic,listestados);
	}

	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistroEntradaTmp(registroEntrada);
	}

	public Vector<RegistroEntrada> getRegistrosEntradaOrdenTmp(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistrosEntradaTmp(codigoOrden);
	}

	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenRegistroTmp();
	}

	public Boolean deleteTemporales(String idOperario) throws Exception {
		return ((IGestionRegistroEntradaService)
				(new ServiceLocator()).getService("IGestionRegistroEntradaService")).deleteTemporales(idOperario);
	}

	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		return ((IGestionRegistroEntradaService)
				(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addRegistrosTemporales(idOperario);
	}

	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadRegistroEntradaTmp(regEntrada);
	}

	public Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadEstadoFabasTmp(regEntrada);
	}

	public Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadIncidenciasTmp(regEntrada);
	}

	public Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadEstadoFabas(regEntrada);
	}

	public Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).loadIncidencias(regEntrada);
	}

	public Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind, RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).updateRegistroEntradaTmp(regEntradaFind, regEntradaActualiza, listindic, listestados);
	}

	public Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaElimina) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).deleteRegistroEntradaTmp(regEntradaFind, regEntradaElimina);
	}

	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).deleteRegistroEntradaTmp(idOperario);
	}

	public Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).deleteOrdenEntrada(codigoEntrada);
	}

	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenRegistroTmp(codigoOrden);
	}

	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaRegistroTmp(codigoOrden);
	}

	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaCaducidadTmp(codigoOrden);
	}

	public String getFechaRegistro(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaRegistro(codigoOrden);
	}

	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getFechaCaducidad(codigoOrden);
	}

	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenEntrada(codigoOrden);
	}

	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getOrdenEntradaTmp(codigoOrden);
	}

	//ENTRADAS: Dado una entrada (como filtro) se devuelve las entradas que se ajustan al filtro 
	public Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistroEntradas(entrada, filtro);
	}

	//ENTRADAS: Recibe una entrada y devuelve el registro asociado
	public RegistroEntrada getRegistroEntrada( String codigoEntrada ) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistroEntrada(codigoEntrada);
	}

	//CALIDAD: Recibe un c�digo de entrada y env�a los registros de calidad asociados
	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception { 
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getRegistrosCalidad(codigoEntrada);
	}
	
	//CALIDAD: A�ade un registro nuevo
	public Double addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addAnalisisCalidadRegistro(calidad);		
	}

	//BULTOS: Carga los bultos de un registro de entrada espec�fico
	public Vector getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).getBultosRegistroEntrada(entrada);
	}
	
	//BULTOS: Update de bulto
	public Boolean updateBultoRE(Bulto bulto) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).updateBultoRE(bulto);
	}

	public Boolean addBultoRE(String idEntrada,int numBulto,double peso) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addBultoRE(idEntrada,numBulto,peso);
	}

	public Boolean addBultoRE(Bulto bulto) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).getService("IGestionRegistroEntradaService")).addBultoRE(bulto);
	}

	public Boolean inicializaBultosRE(Bulto bulto, double total) throws Exception {
		return ((IGestionRegistroEntradaService)(new ServiceLocator()).
			getService("IGestionRegistroEntradaService")).inicializaBultosRE(bulto, total);
	}

	public Vector<TipoMaquina> getTiposMaquinas() throws Exception{
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).getTiposMaquinas();
	}

	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion, long idTipo, long idMaquina) throws Exception{
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).
			getService("IGestionRegistroEntradaService")).getMantenimientos(idMantenimientoProgramacion,idTipo, idMaquina);
	}

	public Vector<Ciclo> getCiclos()throws Exception {
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).getCiclos();
	}

	public boolean addMTProgramado(Mantenimiento entry) throws Exception {
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).addMTProgramado(entry);
	}

	public void inseRegMT(Mantenimiento entry) throws Exception{
		((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).inseRegMT(entry);
	}

	public void devolucion(RegistroEntrada entry) throws Exception{
		((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).devolucion(entry);
	}

	public void checkMT() throws Exception {
		((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).checkMT();
	}

	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception {
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).getDevoluciones(fecha);
	}

	public Vector<Mantenimiento> getMantenimientosProgramados(long tipo, long idMaquina, String fechaConsulta) throws Exception {
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).
				getService("IGestionRegistroEntradaService")).getMantenimientosProgramados(tipo, idMaquina, fechaConsulta);
	}
	
	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception{
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).updateRegistroOrden(entry);
	}

	public String generarCodigoEntrada()  throws Exception {
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).generarCodigoEntrada();
	}

	public void reaprovecharDevolucion(LineaProducto linea) throws Exception{
		((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).reaprovecharDevolucion(linea);
	}

	public Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception{
		return ((IGestionRegistroEntradaService) (new ServiceLocator()).getService("IGestionRegistroEntradaService")).getDevolucionesLote(lote);
	}
}