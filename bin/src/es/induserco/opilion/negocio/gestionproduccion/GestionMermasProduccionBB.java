package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionMermasProduccionBB implements IGestionMermasProduccionService{

	//@Override
	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	//@Override
	public Vector getMermasProduccion(String fecha,Long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector mermas = edh.getMermasProduccion(fecha, idProducto);
		return mermas;
	}
}