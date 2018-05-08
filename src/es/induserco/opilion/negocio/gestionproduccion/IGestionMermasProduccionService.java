package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

public interface IGestionMermasProduccionService {

	public Vector getProductos() throws Exception;
	public Vector getMermasProduccion(String fecha,Long idProducto) throws Exception;
}