package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionFumigadoHelper.
 */
public class GestionFumigadoHelper {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception
	{
		return ((IGestionRegistroFumigadoService)(new ServiceLocator()).getService("IGestionRegistroFumigadoService")).getFechaRegistro();
	}
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception
	{
		return ((IGestionRegistroFumigadoService)(new ServiceLocator()).getService("IGestionRegistroFumigadoService")).getProductos();
	}
	
	/**
	 * Gets the registro fumigados.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @return the registro fumigados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroFumigados(String orden,String fecha) throws Exception
	{
		return ((IGestionRegistroFumigadoService)(new ServiceLocator()).getService("IGestionRegistroFumigadoService")).getRegistroFumigados(orden,fecha);
	}

}



