package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionMermasProduccionService;

public class GestionMermasProduccionHelper {

	public Vector getProductos() throws Exception{
		return ((IGestionMermasProduccionService)(new ServiceLocator()).getService("IGestionMermasProduccionService")).getProductos();
	}

	public Vector getMermasProduccion(String fecha,Long idProducto) throws Exception{
		return ((IGestionMermasProduccionService)
				(new ServiceLocator()).getService("IGestionMermasProduccionService")).getMermasProduccion(fecha, idProducto);
	}
}