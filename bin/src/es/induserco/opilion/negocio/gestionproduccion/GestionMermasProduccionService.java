package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

public class GestionMermasProduccionService implements IGestionMermasProduccionService {

	//@Override
	public Vector getMermasProduccion(String fecha, Long idProducto) throws Exception {
		return new GestionMermasProduccionBB().getMermasProduccion(fecha, idProducto);
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionMermasProduccionBB().getProductos();
	}
}