package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroFiltradoService.
 */
public interface IGestionRegistroFiltradoService {
	
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
	
}