package es.induserco.opilion.negocio;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import es.induserco.opilion.datos.entrada.IRegistroEntradaDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class RegistroEntradaDataHelper {

	public String getCodigoOrden() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getCodigoOrden();		 
	
	}	

	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addROMovimientoVehiculo(entry);		 
	
	}	

	public Boolean addRegistroOrden (RegistroOrden entry) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroOrden(entry);		 
	
	}

	public Boolean addRegistroEntrada (RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroEntrada(entry,listindic,listestados);		 
	
	}

	public Boolean updateRegistroEntrada (RegistroEntrada entryf,RegistroEntrada entryu,List listindic,List listestados) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).updateRegistroEntrada(entryf,entryu,listindic,listestados);		 
	}
	
	/**
	 * Delete registro entrada.
	 *
	 * @param entryf the entryf
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deleteRegistroEntrada (RegistroEntrada entryd) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).deleteRegistroEntrada(entryd);		 
	
	}

	/**
	 * Gets the registros entrada orden.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the registros entrada orden
	 * @throws Exception the exception
	 */
	public Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistrosEntradaOrden(codigoOrden);
	}	

	/**
	 * Load registro entrada.
	 *
	 * @param regEntrada the reg entrada
	 * @return the registro entrada
	 * @throws Exception the exception
	 */
	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).loadRegistroEntrada(regEntrada);
	}	
	
	public Vector getOrdenes()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).getOrdenes();
	}	

	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {	
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).getOrdenes(orden, filtro);
	}	
	
	/**
	 * Gets the proveedores.
	 *
	 * @return the proveedores
	 * @throws Exception the exception
	 */
	public Vector getProveedores()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).getProveedores();
	}

	/**
	 * Gets the transportistas.
	 *
	 * @return the transportistas
	 * @throws Exception the exception
	 */
	public Vector getTransportistas()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).getTransportistas();
	}
	
	/**
	 * Gets the operarios.
	 *
	 * @return the operarios
	 * @throws Exception the exception
	 */
	public Vector getOperarios()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOperarios();
	}
	
	/**
	 * Gets the formatos.
	 *
	 * @return the formatos
	 * @throws Exception the exception
	 */
	public Vector getFormatos()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFormatos();
	}
	
	/**
	 * Gets the productos.
	 *
	 * @param filtro the filtro
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos(String filtro)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getProductos(filtro);
	}
	
	/**
	 * Gets the estados fabas.
	 *
	 * @return the estados fabas
	 * @throws Exception the exception
	 */
	public Vector getEstadosFabas()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getEstadosFabas();	
		
	}
	
	/**
	 * Gets the categorias.
	 *
	 * @return the categorias
	 * @throws Exception the exception
	 */
	public Vector getCategorias(String codigoEntrada)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getCategorias(codigoEntrada);
	}
	
	/**
	 * Gets the incidencias.
	 *
	 * @return the incidencias
	 * @throws Exception the exception
	 */
	public Vector getIncidencias()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getIncidencias();
	}
	
	/**
	 * Gets the vehiculos.
	 *
	 * @return the vehiculos
	 * @throws Exception the exception
	 */
	public Vector getVehiculos()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getVehiculos();
	}
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaRegistro();
	}
	
	/**
	 * Gets the registro entradas.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @param fecha the fecha
	 * @param idProducto the id producto
	 * @return the registro entradas
	 * @throws Exception the exception
	 */
	public Vector getRegistroEntradas(String codigoEntrada, Date fecha,
			Long idProducto)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistroEntradas(codigoEntrada,fecha,idProducto);
	}

	public boolean addAnalisisCalidadRegistro(RegistroEntrada entry,
			Long varIGL, Long varSPL, Long varDPL, Long varDAL, Long varML,
			double calidad, String baremoCalidad)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addAnalisisCalidadRegistro(entry,
				varIGL, varSPL, varDPL, varDAL, varML,
				calidad, baremoCalidad);
	}

	public String getFechaCaducidad()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaCaducidad();
	}

	/**
	 * Gets the cosechas.
	 *
	 * @return the cosechas
	 * @throws Exception the exception
	 */
	public Vector getCosechas()throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getCosechas();
	}

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getProductos();
	}

	/**
	 * Gets the envases.
	 *
	 * @return the envases
	 * @throws Exception the exception
	 */
	public Vector getEnvases() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getEnvases();
	}

	/**
	 * Adds the registro sub entrada.
	 *
	 * @param regEntradaFind the reg entrada find
	 * @param entry the entry
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry, List listindic,List listestados) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados);		 
	
	}

	/**
	 * Gets the tipo ubicaciones.
	 *
	 * @return the tipo ubicaciones
	 * @throws Exception the exception
	 */
	public Vector getTipoUbicaciones() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getTipoUbicaciones();
	}

	/**
	 * Gets the incidencias filtradas.
	 *
	 * @param filtro the filtro
	 * @return the incidencias filtradas
	 * @throws Exception the exception
	 */
	public Vector getIncidenciasFiltradas(int filtro)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getIncidenciasFiltradas(filtro);
	}

	/**
	 * Gets the categorias filtradas.
	 *
	 * @param filtro the filtro
	 * @return the categorias filtradas
	 * @throws Exception the exception
	 */
	public Vector getCategoriasFiltradas(int filtro)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getCategoriasFiltradas(filtro);
				
		
	}

	/**
	 * Adds the registro sub entrada.
	 *
	 * @param regEntradaFind the reg entrada find
	 * @param entry the entry
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @param tipoProceso the tipo proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,RegistroEntrada entry, List listindic,List listestados,String tipoProceso) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados,tipoProceso);		 
	
	}
	
	/**
	 * Adds the registros sub entrada proceso.
	 *
	 * @param regEntradaFind the reg entrada find
	 * @param mapaCategorias the mapa categorias
	 * @param proceso the proceso
	 * @param cantidadProceso the cantidad proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception {	
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistrosSubEntradaProceso(regEntradaFind, mapaCategorias,proceso,cantidadProceso);
	}		

	/**
	 * Gets the saldo registro.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the saldo registro
	 * @throws Exception the exception
	 */
	public Double getSaldoRegistro(String codigoEntrada)throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getSaldoRegistro(codigoEntrada);
	}
	
	/**
	 * Gets the rEEN producto.
	 *
	 * @param idProducto the id producto
	 * @return the rEEN producto
	 * @throws Exception the exception
	 */
	public Vector getREENProducto(Long idProducto) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getREENProducto(idProducto);
	}

	/**
	 * Gets the rEEN producto.
	 *
	 * @param codigoEan the codigo ean
	 * @return the rEEN producto
	 * @throws Exception the exception
	 */
	public Vector getREENProducto(String codigoEan) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getREENProducto(codigoEan);
	}
	
	/**
	 * Gets the rEEN detalle.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the rEEN detalle
	 * @throws Exception the exception
	 */
	public Vector getREENDetalle(String codigoEntrada) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getREENDetalle(codigoEntrada);
	}	

	/**
	 * Gets the etiqueta re.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the etiqueta re
	 * @throws Exception the exception
	 */
	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getEtiquetaRE(codigoEntrada);
	}		

	//Maquinaria
	/**
	 * Adds the mq.
	 *
	 * @param maquina the maquina
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addMQ(Maquina maquina) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addMQ(maquina);
	}

	/**
	 * Adds the mt.
	 *
	 * @param mant the mant
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addMT(Mantenimiento mant) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addMT(mant);
	}

	/**
	 * Gets the tipo mant.
	 *
	 * @return the tipo mant
	 * @throws Exception the exception
	 */
	public Vector<TipoMantenimiento> getTipoMant()throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getTipoMant();
	}

	/**
	 * Gets the maquinas.
	 *
	 * @return the maquinas
	 * @throws Exception the exception
	 */
	public Vector<Maquina> getMaquinas(long idTipo, long idMaquina, String fecha)throws Exception{
		return((IRegistroEntradaDataService)
				(new ServiceLocator()).getService("IRegistroEntradaDataService")).getMaquinas(idTipo,idMaquina, fecha);
	}

	/**
	 * Gets the registros mt.
	 *
	 * @param idTipoMant the id tipo mant
	 * @param idMaquina the id maquina
	 * @param fechaConsulta the fecha consulta
	 * @return the registros mt
	 * @throws Exception the exception
	 */
	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception{	
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistrosMT(idTipoMant,idMaquina,fechaConsulta);
    }

	/**
	 * Gets the orden entrada temporal.
	 *
	 * @param registroEntrada the registro entrada
	 * @return the orden entrada temporal
	 * @throws Exception the exception
	 */
	public Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOrdenEntradaTemporal(registroEntrada);
	}

	/**
	 * Adds the registro entrada tmp.
	 *
	 * @param registroEntrada the registro entrada
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroEntradaTmp(registroEntrada,listindic,listestados);
	}
	
	/**
	 * Adds the registro entrada tmp.
	 *
	 * @param registroEntrada the registro entrada
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistroEntradaTmp(registroEntrada);
	}

	/**
	 * Gets the registros entrada temp.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the registros entrada temp
	 * @throws Exception the exception
	 */
	public Vector<RegistroEntrada> getRegistrosEntradaTemp(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistrosEntradaTmp(codigoOrden);
	}

	/**
	 * Gets the orden registro tmp.
	 *
	 * @return the orden registro tmp
	 * @throws Exception the exception
	 */
	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOrdenRegistroTmp();
	}

	public Boolean deleteTemporales(String idOperario) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).deleteTemporales(idOperario);
	}

	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addRegistrosTemporales(idOperario);
	}

	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).loadRegistroEntradaTmp(regEntrada);
	}

	public Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).loadEstadoFabasTmp(regEntrada);
	}
	
	/**
	 * Load incidencias tmp.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	public Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).loadIncidenciasTmp(regEntrada);
	}
	
	/**
	 * Load estado fabas.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	public Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).loadEstadoFabas(regEntrada);
	}

	public Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).loadIncidencias(regEntrada);
	}

	public Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).updateRegistroEntradaTmp(regEntradaFind, regEntradaActualiza, listindic, listestados);
	}

	public Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaElimina) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).deleteRegistroEntradaTmp(regEntradaFind, regEntradaElimina);
	}

	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		return((IRegistroEntradaDataService)
				(new ServiceLocator()).getService("IRegistroEntradaDataService")).deleteRegistroEntradaTmp(idOperario);
	}

	public Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).deleteOrdenEntrada(codigoEntrada);
	}

	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOrdenRegistroTmp(codigoOrden);
	}

	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaCaducidadTmp(codigoOrden);
	}

	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaRegistroTmp(codigoOrden);
	}

	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaCaducidad(codigoOrden);
	}

	public String getFechaRegistro(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getFechaRegistro(codigoOrden);
	}

	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOrdenEntrada(codigoOrden);
	}

	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getOrdenEntradaTmp(codigoOrden);
	}

	public Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistroEntradas(entrada, filtro);
	}

	public RegistroEntrada getRegistroEntrada(String codigoEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistroEntrada(codigoEntrada);
	}

	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getRegistrosCalidad(codigoEntrada);
	}

	public boolean addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addAnalisisCalidadRegistro(calidad);
	}

	public Vector getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getBultosRegistroEntrada(entrada);
	}

	public Boolean updateBultoRE(Bulto bulto) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).updateBultoRE(bulto);
	}

	public Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addBultoRE(idEntrada,numBulto,peso);
	}

	public Boolean addBultoRE(Bulto bulto) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addBultoRE(bulto);
	}

	public Boolean inicializaBultosRE(Bulto bulto, double total) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).inicializaBultosRE(bulto, total);
	}

	public Vector<TipoMaquina> getTiposMaquinas() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getTiposMaquinas();
	}

	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion, long idTipo, long idMaquina) throws Exception{
		return((IRegistroEntradaDataService)(new ServiceLocator()).
				getService("IRegistroEntradaDataService")).getMantenimientos(idMantenimientoProgramacion, idTipo, idMaquina);
	}

	public Vector<Ciclo> getCiclos() throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getCiclos();
	}

	public boolean addMTProgramado(Mantenimiento entry) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).addMTProgramado(entry);
	}

	public void inseRegMT(Mantenimiento entry) throws Exception{
		((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).inseRegMT(entry);
	}

	public void devolucion(RegistroEntrada entry) throws Exception {
		((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).devolucion(entry);
	}

	public void checkMT() throws Exception {
		((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).checkMT();
	}

	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).getDevoluciones(fecha);
	}

	public Vector<Mantenimiento> getMantenimientosProgramados(long tipo,
			long idMaquina, String fechaConsulta) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).
			getMantenimientosProgramados(tipo, idMaquina, fechaConsulta);
	}

	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception {
		return((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).
			updateRegistroOrden(entry);
	}

	public String generarCodigoEntrada() throws Exception {
		return((IRegistroEntradaDataService)
				(new ServiceLocator()).getService("IRegistroEntradaDataService")).generarCodigoEntrada();
	}

	public void reaprovecharDevolucion(LineaProducto linea) throws Exception {
		((IRegistroEntradaDataService)(new ServiceLocator()).getService("IRegistroEntradaDataService")).reaprovecharDevolucion(linea);
	}

	public Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception {
		return((IRegistroEntradaDataService)
				(new ServiceLocator()).getService("IRegistroEntradaDataService")).getDevolucionesLote(lote);
	}
}