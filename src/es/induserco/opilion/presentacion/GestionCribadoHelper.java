package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionCribadoHelper.
 */
public class GestionCribadoHelper {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getFechaRegistro();
	}
	
	/**
	 * Gets the hora inicio proceso.
	 *
	 * @param orden the orden
	 * @return the hora inicio proceso
	 * @throws Exception the exception
	 */
	public String getHoraInicioProceso(String orden) throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getHoraInicioProceso(orden);
	}
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getProductos();
	}
	
	/**
	 * Gets the registro cribados.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @param idProducto the id producto
	 * @return the registro cribados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroCribados(String orden,String fecha,Long idProducto) throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getRegistroCribados(orden,fecha,idProducto);
	}

	/**
	 * Gets the registro entrada cribado.
	 *
	 * @param orden the orden
	 * @param idProducto the id producto
	 * @return the registro entrada cribado
	 * @throws Exception the exception
	 */
	public Vector getRegistroEntradaCribado(String orden,long idProducto) throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getRegistroEntradaCribado(orden,idProducto);
	}

	/**
	 * Gets the detalle cribado.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the detalle cribado
	 * @throws Exception the exception
	 */
	public Vector getDetalleCribado(String codigoEntrada) throws Exception
	{
		return ((IGestionRegistroCribadoService)(new ServiceLocator()).getService("IGestionRegistroCribadoService")).getDetalleCribado(codigoEntrada);
	}

}


