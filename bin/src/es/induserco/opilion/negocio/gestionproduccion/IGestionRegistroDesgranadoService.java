package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.produccion.GestionProduccionDAO;
import es.induserco.opilion.infraestructura.ServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroDesgranadoService.
 */
public interface IGestionRegistroDesgranadoService {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception;
	/*Sirve para agregar un nuevo registro del proceso*/
	/**
	 * Adds the registro proceso.
	 *
	 * @param gprod the gprod
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso) throws Exception;
	
	/**
	 * Insert registro envasado.
	 *
	 * @param gprodf the gprodf
	 * @param gprodu the gprodu
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception;	
	
	/**
	 * Update registro proceso.
	 *
	 * @param gprodf the gprodf
	 * @param gprodu the gprodu
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateRegistroProceso(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception;
	
	/**
	 * Gets the rE proceso.
	 *
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the rE proceso
	 * @throws Exception the exception
	 */
	public Vector getREProceso(String orden, String proceso) throws Exception;
	/*actualiza cantidades de los RE del proceso*/
	/**
	 * Update re cant proceso.
	 *
	 * @param mapaCantRE the mapa cant re
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception;
	/*actualiza mermas de los RE del proceso*/
	/**
	 * Update re merm proceso.
	 *
	 * @param mapaCantRE the mapa cant re
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception;
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception;
	
	/**
	 * Gets the productos desgranado.
	 *
	 * @return the productos desgranado
	 * @throws Exception the exception
	 */
	public Vector getProductosDesgranado() throws Exception;
	
	/**
	 * Gets the sub registros entrada.
	 *
	 * @param gprod the gprod
	 * @param proceso the proceso
	 * @return the sub registros entrada
	 * @throws Exception the exception
	 */
	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception;
	
	/**
	 * Gets the productos desgranado vaina.
	 *
	 * @return the productos desgranado vaina
	 * @throws Exception the exception
	 */
	public Vector getProductosDesgranadoVaina() throws Exception;
	
	/**
	 * Gets the codigo registro proceso.
	 *
	 * @param proceso the proceso
	 * @return the codigo registro proceso
	 * @throws Exception the exception
	 */
	public String getCodigoRegistroProceso(String proceso) throws Exception;
	
	/**
	 * Gets the registro desgranados.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @param idProducto the id producto
	 * @return the registro desgranados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroDesgranados(String orden,String fecha,Long idProducto) throws Exception;
	
	/**
	 * Gets the registro seleccion.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @return the registro seleccion
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroSeleccion(String orden,String fecha) throws Exception;
	
	/**
	 * Gets the codigo entrada orden.
	 *
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the codigo entrada orden
	 * @throws Exception the exception
	 */
	public String getCodigoEntradaOrden(String orden, String proceso)throws Exception;
	
	/**
	 * Gets the rE proceso.
	 *
	 * @param orden the orden
	 * @param idProducto the id producto
	 * @param idIncidencia the id incidencia
	 * @return the rE proceso
	 * @throws Exception the exception
	 */
	public Vector getREProceso(String orden, long idProducto, int idIncidencia)throws Exception;
	
	//public Vector<GestionProduccion> getDetallesRegistroSeleccion(String proceso)throws Exception;
	public Vector<GestionProduccion> getDetallesRegistroProceso(
			String tipoProceso, String proceso) throws Exception;
	
	public Vector<LineaProducto> getInfoMateria(String codigoEntrada) throws Exception;
	
	public Vector<LineaProducto> getInfoMateriasProceso(String orden,String proceso) throws Exception;
}