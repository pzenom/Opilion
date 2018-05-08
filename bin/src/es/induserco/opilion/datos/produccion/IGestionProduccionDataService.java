package es.induserco.opilion.datos.produccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public interface IGestionProduccionDataService {

	Vector getProductos() throws Exception;
	Vector getPresentacionProductos(boolean stockSuficiente) throws Exception;	
	Vector getProductosDesgranado() throws Exception;	
	Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception;
	Vector getProductosDesgranadoVaina() throws Exception;	
	Vector getEnvases() throws Exception;
	String getFechaRegistro() throws Exception;
	String getCodigoRegistroProceso(String proceso) throws Exception;	
	Vector getOperarios() throws Exception;
	Vector getRegistroEnvases(long idEnvase, String filtro) throws Exception;
	Vector getRegistroEnvases(List listenvases) throws Exception;	
	Vector getRegistroIngredientes() throws Exception;
	String generarNumeroLote(String codigo) throws Exception;	
	Boolean addTmpRegistroIngredientesEnvases(GestionProduccion grod, GestionProduccion gpro) throws Exception;
	Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception;
	Vector getTmpRegistroIngredientesEnvases(String orden) throws Exception;
	Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception;
	Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception;
	Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,String orden) throws Exception;	
	Vector getTmpRegistroIngredientesEnvases() throws Exception;
	Vector getRegistroIngredientes(List listaingred) throws Exception;
	Vector getRegistroIngredientes(String orden, long idCategoria, long idProducto) throws Exception;
	Vector getRegistroIngredientes(String orden, long idProducto) throws Exception;	
	Vector getMermasProduccion(String fecha,Long idProducto) throws Exception;
	Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception;	
	Boolean updateRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu) throws Exception;
	Boolean deleteRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodd) throws Exception;
	Boolean addRegistroProceso(GestionProduccion gprod, String proceso) throws Exception;
	Boolean updateRegistroProceso(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception;
	Vector getREProceso(String orden, String proceso) throws Exception;	
	Vector getREProceso(String orden, long idProducto, int idIncidencia)throws Exception;	
	Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception;	
	Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception;	
	Vector getRegistroEntradaCongelado(String orden,long idProducto) throws Exception;	
	Vector getRegistroEntradaCribado(String orden,long idProducto) throws Exception;
	Vector getDetalleCribado(String codigoEntrada)throws Exception;
	String getCodigoEntradaOrden(String orden, String proceso)throws Exception;
	String getHoraInicioProceso(String orden) throws Exception;
	Vector<RegistroEntrada> getRegistroIngredientes(long idProducto, String filtro) throws Exception;
	Vector<GestionProduccion> getInfoMateriaPrima(long idProducto) throws Exception;
	Boolean addComponentesProceso(List<String> listaelementos, String orden,String tipo) throws Exception;
	Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception;
	Boolean insertaCantidadesEnvases(Map mapaCantidades, String orden)throws Exception;
	Vector<GestionProduccion> getRegistroFumigados(String orden, String fecha)throws Exception;
	Vector<GestionProduccion> getRegistroSeleccion(String orden, String fecha)throws Exception;	
	Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro) throws Exception;
	Vector<GestionProduccion> getRegistroCribados(String orden, String fecha, Long idProducto) throws Exception;
	Vector<GestionProduccion> getRegistroDesgranados(String orden, String fecha, Long idProducto) throws Exception;	
	Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha, Long idProducto) throws Exception;
	Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gprod, String tipo)throws Exception;
	Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception;
	Boolean delProcesoPendiente(RegistroEnvasado envasado) throws Exception;
	RegistroEnvasado getProcesoPendiente(RegistroEnvasado envasado) throws Exception;
	Vector <LineaProducto> getLineaProducto(String idOrden, String proceso,String filtro,int option) throws Exception;
	GestionProduccion getMaestroEN(String orden) throws Exception;
	Vector<GestionProduccion> getDetalleEN(String orden) throws Exception;
	GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception;
	/**
	 * Devuelve un vector con los campos "codigoEntrada, cantidad, elaborado, lote, horainicio, orden, idProducto e idEnvase" de un proceso de envasado.
	 *
	 * @param idEnvasado Id del proceso de envasado
	 * @param filtro Filtro utilizado para seleccionar el proceso de envasado
	 * @return La informaci�n sobre el proceso de envasado
	 * @throws Exception Fallo leyendo la informaci�n del proceso de la base de datos
	 */
	GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception;
	/**
	 * A partir del id de gesti�n de envasado, obtenemos el id del producto que ha sido envasado.
	 *
	 * @param id Identificador de la gesti�n de envasado
	 * @return id Identificador del producto envasado
	 * @throws Exception Fallo obteniendo el identificador del producto
	 */
	int idGestionToProducto(int id) throws Exception;
	/**
	 * Actualiza toda la informaci�n sobre un proceso de envasado cuando �sta se modifica.
	 *
	 * @param cantidad Cantidad de productos finales
	 * @param orden Orden del proceso de envasado
	 * @param fechaInicio Fecha de inicio para el proceso de envasado
	 * @param operario Operario responsable del proceso de envasado
	 * @param observaciones Observaciones respecto al proceso de envasado
	 * @param materias Materias reservadas para el proceso de envasado
	 * @param envases Envases reservados para el proceso de envasado
	 * @param materiasLimpiar Materias que hay que liberar del proceso de envasado
	 * @param envasesLimpiar Envases que hay que liberar del proceso de envasado
	 * @return true, Si el proceso de envasado se actualiza correctamente
	 * @throws Exception La actualizaci�n del proceso de envasado falla
	 */
	boolean actualizaProcesoEnvasado(int cantidad, String orden, String fechaInicio, String operario, String observaciones,
			ArrayList<LineaProducto> materias, ArrayList<LineaProducto> envases,
			ArrayList<LineaProducto> materiasLimpiar, ArrayList<LineaProducto> envasesLimpiar) throws Exception;
	/**
	 * Devuelve un vector con todas las materias primas asociadas a un producto
	 *
	 * @param idProducto Identificador del producto
	 * @param filtro Filtro utilizado para buscar el producto
	 * @return Materias primas asociadas al producto idProducto
	 * @throws Exception Fallo obteniendo las materias primas asociadas al producto
	 */
	Vector<LineaProducto> getIngredientesProducto(Long idProducto, String filtro) throws Exception;
	/**
	 * Devuelve un vector con todos los envases asociados a un producto
	 *
	 * @param idProducto Identificador del producto
	 * @param filtro Filtro utilizado para buscar el producto
	 * @return Envases asociados al producto idProducto
	 * @throws Exception Fallo obteniendo los envases asociados al producto
	 */
	Vector<LineaProducto> getEnvasesProducto(Long idProducto, String filtro) throws Exception;
	/**
	 * Modifica la base de datos cuando se inicia un proceso de envasado
	 * 
	 * @param orden Orden de envasado del proceso
	 * @return true, Si las actualizaci�n se realizan con �xito
	 * @throws Exception Fallo actualizando la base de datos
	 */
	boolean iniciaProcesoEnvasado(String orden, String operario) throws Exception;
	boolean pausaProcesoEnvasado(String orden, String operario,
			double elaborado, String elaboradoAgrupar, String observaciones) throws Exception;
	boolean productoCompuesto(String orden) throws Exception;
	Vector<GestionProduccion> getProcesosEnvasado(String orden,
			String fecha, String lote, Long idProducto, String estados[], char habilitado)
			throws Exception;
	String getObservacionesEnvasado(String orden) throws Exception;
	boolean finalizaProcesoEnvasado(RegistroEnvasado regi, boolean anula) throws Exception;
	boolean actualizarPrecioCosteProducto(String orden) throws Exception;
	Vector<RegistroEntrada> getDetallesEnvasado(String ordenEnvasado) throws Exception;
	boolean setUbicado(String ordenEnvasado, Vector<Ubicacion> ubicaciones, boolean actualizarCampoUbicado) throws Exception;
	Vector<LineaProducto> getEnvasesMateriasEnvasado(String ordenEnvasado, String filtro, int option) throws Exception;
	Vector<RegistroActividad> getRegistrosActividad(String proceso) throws Exception;
	Vector<Ubicacion> getUbicacionesEnvasado(String orden, String lote) throws Exception;
	Vector<GestionProduccion> getDetallesRegistroProceso(String tipoProceso,
			String proceso) throws Exception;
	Boolean addComponentesEnvasado(List<String> listaelementos, String orden,
			String tipo) throws Exception;
	Vector<LineaProducto> getInfoMateria(String codigoEntrada) throws Exception;
	Vector<LineaProducto> getInfoMateriasProceso(String ordenProceso, String proceso) throws Exception;
	void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones, String orden, String proceso)throws Exception;
	Vector<Producto> getAgrupaciones(boolean suficienteStock) throws Exception;
	void updateFechaProgramada(GestionProduccion gpro) throws Exception;
	char compruebaLoteCantidad(Lote lote) throws Exception;
	Vector<LineaProducto> getProductosComponenLote(String lote) throws Exception;
	Vector<RegistroEntrada> getLotesIngrediente(long idMateriaPrima) throws Exception;
	Vector<RegistroEntrada> getLotesEnvase(long idEnvase) throws Exception;
	Vector<Producto> getTrazabilidadRegistroEntrada(String registro) throws Exception;
	void actualizaCaducidadEnvasado(String proceso, String caducidad, String operario) throws Exception;
	boolean esFabaFresca(String orden) throws Exception;
}