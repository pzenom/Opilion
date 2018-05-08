package es.induserco.opilion.presentacion;

import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.produccion.IGestionProduccionDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionDesgranadoHelper.
 */
public class GestionDesgranadoHelper {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getFechaRegistro();
	}
	
	/**
	 * Adds the registro proceso.
	 *
	 * @param gprod the gprod
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso) throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).addRegistroProceso(gprod,proceso);
	}

	/**
	 * Insert registro envasado.
	 *
	 * @param gprodf the gprodf
	 * @param gprodu the gprodu
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).insertRegistroEnvasado(gprodf,gprodu,proceso);
	}

	/**
	 * Update registro proceso.
	 *
	 * @param gprodf the gprodf
	 * @param gprodu the gprodu
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateRegistroProceso(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).updateRegistroProceso(gprodf,gprodu, proceso);
	}

	/*Obtiene RE del proceso*/
	/**
	 * Gets the rE proceso.
	 *
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the rE proceso
	 * @throws Exception the exception
	 */
	public Vector getREProceso(String orden, String proceso) throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getREProceso(orden, proceso);
	}
	
	/*Obtiene RE faltos del proceso*/
	/**
	 * Gets the rE proceso.
	 *
	 * @param orden the orden
	 * @param idProducto the id producto
	 * @param idIncidencia the id incidencia
	 * @return the rE proceso
	 * @throws Exception the exception
	 */
	public Vector getREProceso(String orden, long idProducto, int idIncidencia)throws Exception {
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getREProceso(orden, idProducto,idIncidencia);
	}
	
	/*Actualiza cantidades de los RE del proceso*/
	/**
	 * Update re cant proceso.
	 *
	 * @param mapaCantRE the mapa cant re
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).updateRECantProceso(mapaCantRE,orden, proceso);
	}
	/*Actualiza mermas de los RE del proceso*/
	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).updateREMermProceso(mapaCantRE,orden, proceso);
	}	

	public Vector getProductos() throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getProductos();
	}

	public Vector<GestionProduccion> getRegistroDesgranados(String orden,String fecha,Long idProducto) throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getRegistroDesgranados(orden,fecha,idProducto);
	}

	public Vector<GestionProduccion> getRegistroSeleccion(String orden,String fecha) throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getRegistroSeleccion(orden,fecha);
	}

	public Vector getProductosDesgranado() throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getProductosDesgranado();
	}

	public Vector getProductosDesgranadoVaina() throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getProductosDesgranadoVaina();
	}

	/**
	 * Gets the sub registros entrada.
	 *
	 * @param gprod the gprod
	 * @param proceso the proceso
	 * @return the sub registros entrada
	 * @throws Exception the exception
	 */
	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getSubRegistrosEntrada(gprod,proceso);
	}
	
	/**
	 * Gets the codigo registro proceso.
	 *
	 * @param proceso the proceso
	 * @return the codigo registro proceso
	 * @throws Exception the exception
	 */
	public String getCodigoRegistroProceso(String proceso) throws Exception
	{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getCodigoRegistroProceso(proceso);
	}

	/**
	 * Gets the codigo entrada orden.
	 *
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the codigo entrada orden
	 * @throws Exception the exception
	 */
	public String getCodigoEntradaOrden(String orden, String proceso)throws Exception{
		return ((IGestionRegistroDesgranadoService)(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getCodigoEntradaOrden(orden,proceso);
	}

	/*public Vector<GestionProduccion> getDetallesRegistroSeleccion(String proceso)throws Exception{
		return ((IGestionRegistroDesgranadoService)
				(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getDetallesRegistroSeleccion(proceso);
	}*/

	public Vector<GestionProduccion> getDetallesRegistroProceso(
			String tipoProceso, String proceso) throws Exception {
		return ((IGestionRegistroDesgranadoService)
				(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getDetallesRegistroProceso(tipoProceso, proceso);
	}

	public Vector<LineaProducto> getInfoMateria(String codigoEntrada) throws Exception {
		return ((IGestionRegistroDesgranadoService)
				(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getInfoMateria(codigoEntrada);
	}

	public Vector<LineaProducto> getInfoMateriasProceso(String orden, String proceso) throws Exception{
		return ((IGestionRegistroDesgranadoService)
				(new ServiceLocator()).getService("IGestionRegistroDesgranadoService")).getInfoMateriasProceso(orden, proceso);
	}
}