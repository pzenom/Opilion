package es.induserco.opilion.datos.entrada;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.*;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public interface IRegistroEntradaDataService {

	Vector getProveedores() throws Exception;	
	Vector getTransportistas() throws Exception;
	Vector getOperarios() throws Exception;
	
	/**
	 * Gets the productos.
	 *
	 * @param filtro the filtro
	 * @return the productos
	 * @throws Exception the exception
	 */
	Vector getProductos(String filtro) throws Exception;
	
	/**
	 * Gets the estados fabas.
	 *
	 * @return the estados fabas
	 * @throws Exception the exception
	 */
	Vector getEstadosFabas() throws Exception;
	
	Vector getCategorias(String codigoEntrada) throws Exception;
	
	/**
	 * Gets the formatos.
	 *
	 * @return the formatos
	 * @throws Exception the exception
	 */
	Vector getFormatos() throws Exception;
	
	/**
	 * Gets the vehiculos.
	 *
	 * @return the vehiculos
	 * @throws Exception the exception
	 */
	Vector getVehiculos() throws Exception;
	
	/**
	 * Gets the incidencias.
	 *
	 * @return the incidencias
	 * @throws Exception the exception
	 */
	Vector getIncidencias() throws Exception;
	
	/**
	 * Gets the codigo orden.
	 *
	 * @return the codigo orden
	 * @throws Exception the exception
	 */
	String getCodigoOrden() throws Exception;	
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	String getFechaRegistro() throws Exception;
	
	/**
	 * Adds the ro movimiento vehiculo.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception;	
	
	/**
	 * Adds the registro orden.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addRegistroOrden(RegistroOrden entry) throws Exception;
	
	/**
	 * Adds the registro entrada.
	 *
	 * @param entry the entry
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addRegistroEntrada(RegistroEntrada entry,List listindic,List listestados) throws Exception;
	
	/**
	 * Update registro entrada.
	 *
	 * @param entryf the entryf
	 * @param entryu the entryu
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateRegistroEntrada(RegistroEntrada entryf,RegistroEntrada entryu,List listindic,List listestados) throws Exception;
	
	Boolean deleteRegistroEntrada(RegistroEntrada entryd) throws Exception;
	
	/**
	 * Gets the registro entradas.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @param fecha the fecha
	 * @param idProducto the id producto
	 * @return the registro entradas
	 * @throws Exception the exception
	 */
	Vector getRegistroEntradas(String codigoEntrada, Date fecha,Long idProducto) throws Exception;
	
	/**
	 * Load registro entrada.
	 *
	 * @param regEntrada the reg entrada
	 * @return the registro entrada
	 * @throws Exception the exception
	 */
	RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception;	
	
	/**
	 * Gets the registros entrada orden.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the registros entrada orden
	 * @throws Exception the exception
	 */
	Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception;	
	Boolean addAnalisisCalidadRegistro(RegistroEntrada entry, Long varIGL,
			Long varSPL, Long varDPL, Long varDAL, Long varML, double calidad,
			String baremoCalidad)throws Exception;
	String getFechaCaducidad()throws Exception;
	Vector getCosechas()throws Exception;
	
	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 * @throws Exception the exception
	 */
	Vector getOrdenes() throws Exception;	
	
	/**
	 * Gets the ordenes.
	 *
	 * @param orden the orden
	 * @return the ordenes
	 * @throws Exception the exception
	 */
	Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception;
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	Vector getProductos() throws Exception;
	
	/**
	 * Gets the envases.
	 *
	 * @return the envases
	 * @throws Exception the exception
	 */
	Vector getEnvases()throws Exception;
	
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
	Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind, RegistroEntrada entry, List listindic,
			List listestados)throws Exception;
	
	/**
	 * Gets the tipo ubicaciones.
	 *
	 * @return the tipo ubicaciones
	 * @throws Exception the exception
	 */
	Vector getTipoUbicaciones()throws Exception;
	
	/**
	 * Gets the incidencias filtradas.
	 *
	 * @param filtro the filtro
	 * @return the incidencias filtradas
	 * @throws Exception the exception
	 */
	Vector getIncidenciasFiltradas(int filtro)throws Exception;
	
	/**
	 * Gets the categorias filtradas.
	 *
	 * @param filtro the filtro
	 * @return the categorias filtradas
	 * @throws Exception the exception
	 */
	Vector getCategoriasFiltradas(int filtro)throws Exception;
	
	/**
	 * Gets the saldo registro.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the saldo registro
	 * @throws Exception the exception
	 */
	Double getSaldoRegistro(String codigoEntrada)throws Exception;
	
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
	Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind, RegistroEntrada entry, List listindic,
			List listestados,String tipoProceso)throws Exception;
	
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
	String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception;
	
	/**
	 * Gets the rEEN producto.
	 *
	 * @param idProducto the id producto
	 * @return the rEEN producto
	 * @throws Exception the exception
	 */
	Vector getREENProducto(Long idProducto) throws Exception;
	
	/**
	 * Gets the rEEN producto.
	 *
	 * @param codigoEan the codigo ean
	 * @return the rEEN producto
	 * @throws Exception the exception
	 */
	Vector getREENProducto(String codigoEan) throws Exception;	
	
	/**
	 * Gets the rEEN detalle.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the rEEN detalle
	 * @throws Exception the exception
	 */
	Vector getREENDetalle(String codigoEntrada) throws Exception;
	
	/**
	 * Gets the etiqueta re.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the etiqueta re
	 * @throws Exception the exception
	 */
	RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception;

	//Maquinaria
	/**
	 * Adds the mq.
	 *
	 * @param maquina the maquina
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addMQ(Maquina maquina) throws Exception;
	
	/**
	 * Adds the mt.
	 *
	 * @param mant the mant
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addMT(Mantenimiento mant) throws Exception;	
	
	/**
	 * Gets the tipo mant.
	 *
	 * @return the tipo mant
	 * @throws Exception the exception
	 */
	Vector<TipoMantenimiento> getTipoMant()throws Exception;
	
	/**
	 * Gets the maquinas.
	 *
	 * @return the maquinas
	 * @throws Exception the exception
	 */
	Vector<Maquina> getMaquinas(long idTipo, long idMaquina, String fecha)throws Exception;
	
	/**
	 * Gets the registros mt.
	 *
	 * @param idTipoMant the id tipo mant
	 * @param idMaquina the id maquina
	 * @param fechaConsulta the fecha consulta
	 * @return the registros mt
	 * @throws Exception the exception
	 */
	Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception;
	
	//Registro de Entrada/Orden Temporal
	/**
	 * Gets the orden entrada temporal.
	 *
	 * @param registroEntrada the registro entrada
	 * @return the orden entrada temporal
	 * @throws Exception the exception
	 */
	Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception;
	Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception;
	Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception;
	Vector<RegistroEntrada> getRegistrosEntradaTmp(String codigoOrden)throws Exception;
	
	/**
	 * Gets the orden registro tmp.
	 *
	 * @return the orden registro tmp
	 * @throws Exception the exception
	 */
	RegistroEntrada getOrdenRegistroTmp() throws Exception;
	
	/**
	 * Gets the orden registro tmp.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the orden registro tmp
	 * @throws Exception the exception
	 */
	RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception;
	
	Boolean deleteTemporales(String idOperario) throws Exception;
	Boolean addRegistrosTemporales(String idOperario) throws Exception;
	
	/**
	 * Load registro entrada tmp.
	 *
	 * @param regEntrada the reg entrada
	 * @return the registro entrada
	 * @throws Exception the exception
	 */
	RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception;
	
	/**
	 * Load estado fabas tmp.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception;
	
	/**
	 * Load incidencias tmp.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception;
	
	/**
	 * Load estado fabas.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception;
	
	/**
	 * Load incidencias.
	 *
	 * @param regEntrada the reg entrada
	 * @return the vector
	 * @throws Exception the exception
	 */
	Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception;
	
	/**
	 * Update registro entrada tmp.
	 *
	 * @param regEntradaFind the reg entrada find
	 * @param regEntradaActualiza the reg entrada actualiza
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception;
	
	Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind, RegistroEntrada regEntradaElimina) throws Exception;
	
	Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception;

	Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception;
	
	/**
	 * Gets the fecha caducidad tmp.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the fecha caducidad tmp
	 * @throws Exception the exception
	 */
	String getFechaCaducidadTmp(String codigoOrden)throws Exception;
	
	/**
	 * Gets the fecha registro tmp.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the fecha registro tmp
	 * @throws Exception the exception
	 */
	String getFechaRegistroTmp(String codigoOrden)throws Exception;
	
	/**
	 * Gets the fecha caducidad.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the fecha caducidad
	 * @throws Exception the exception
	 */
	String getFechaCaducidad(String codigoOrden)throws Exception;
	
	/**
	 * Gets the fecha registro.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	String getFechaRegistro(String codigoOrden)throws Exception;
	
	/**
	 * Gets the fecha caducidad.
	 *
	 * @param codigoOrden the codigo orden
	 * @param temp the temp
	 * @return the fecha caducidad
	 * @throws Exception the exception
	 */
	String getFechaCaducidad(String codigoOrden, Boolean temp) throws Exception;
	
	/**
	 * Gets the fecha registro.
	 *
	 * @param codigoOrden the codigo orden
	 * @param temp the temp
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	String getFechaRegistro(String codigoOrden, Boolean temp) throws Exception;
	
	/**
	 * Gets the orden entrada.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the orden entrada
	 * @throws Exception the exception
	 */
	RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception;
	
	/**
	 * Gets the orden entrada tmp.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the orden entrada tmp
	 * @throws Exception the exception
	 */
	RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception;
	RegistroOrden getOrdenEntrada(String codigoOrden, Boolean temp) throws Exception;
	Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception;
	
	/**
	 * Gets the registro entrada.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the registro entrada
	 * @throws Exception the exception
	 */
	RegistroEntrada getRegistroEntrada(String codigoEntrada)throws Exception;
	
	//CRUD CALIDAD
	/**
	 * Gets the registros calidad.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the registros calidad
	 * @throws Exception the exception
	 */
	Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception;
	
	/**
	 * Adds the analisis calidad registro.
	 *
	 * @param calidad the calidad
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception;
	
	//CRUD BULTOS
	/**
	 * Adds the bulto re.
	 *
	 * @param idEntrada the id entrada
	 * @param numBulto the num bulto
	 * @param peso the peso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception;
	
	/**
	 * Adds the bulto re.
	 *
	 * @param bulto the bulto
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addBultoRE(Bulto bulto) throws Exception;
	
	/**
	 * Gets the bultos registro entrada.
	 *
	 * @param entrada the entrada
	 * @return the bultos registro entrada
	 * @throws Exception the exception
	 */
	Vector getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception;
	
	/**
	 * Update bulto re.
	 *
	 * @param bulto the bulto
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateBultoRE(Bulto bulto) throws Exception;
	Boolean inicializaBultosRE(Bulto bulto, double pesoTotal) throws Exception;
	Vector<TipoMaquina> getTiposMaquinas() throws Exception;
	Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion,
			long idTipo, long idMaquina) throws Exception;
	Vector<Ciclo> getCiclos() throws Exception;
	boolean addMTProgramado(Mantenimiento mant) throws Exception;
	void inseRegMT(Mantenimiento mant) throws Exception;
	void devolucion(RegistroEntrada entry)
			throws Exception;
	void checkMT() throws Exception;
	Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception;
	Vector<Mantenimiento> getMantenimientosProgramados(long idTipoMant,
			long idMaquina, String fechaConsulta) throws Exception;
	Boolean updateRegistroOrden(RegistroOrden entry) throws Exception;
	String generarCodigoEntrada() throws Exception;

	void reaprovecharDevolucion(LineaProducto linea) throws Exception;
	Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception;
}