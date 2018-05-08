package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.ServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroFumigadoService.
 */
public interface IGestionRegistroFumigadoService {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception;
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception;
	
	/**
	 * Gets the registro fumigados.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @return the registro fumigados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroFumigados(String orden,String fecha) throws Exception;

}

