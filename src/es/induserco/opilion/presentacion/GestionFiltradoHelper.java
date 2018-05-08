package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFiltradoService;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionFiltradoHelper.
 */
public class GestionFiltradoHelper {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception
	{
		return ((IGestionRegistroFiltradoService)(new ServiceLocator()).getService("IGestionRegistroFiltradoService")).getFechaRegistro();
	}

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception
	{
		return ((IGestionRegistroFiltradoService)(new ServiceLocator()).getService("IGestionRegistroFiltradoService")).getProductos();
	}
}

