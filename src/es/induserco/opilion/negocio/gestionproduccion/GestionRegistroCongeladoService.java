package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;

public class GestionRegistroCongeladoService implements IGestionRegistroCongeladoService {

	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroCongeladoBB().getFechaRegistro();
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroCongeladoBB().getProductos();
	}

	//@Override
	public Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha,
			Long idProducto) throws Exception {
		return new GestionRegistroCongeladoBB().getRegistroCongelados(orden,fecha,idProducto);
	}

	//@Override
	public Vector getRegistroEntradaCongelado(String orden, long idProducto) throws Exception {
		return new GestionRegistroCongeladoBB().getRegistroEntradaCongelado(orden,idProducto);
	}

	//@Override
	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones,
			String orden, String proceso) throws Exception {
		new GestionRegistroCongeladoBB().guardarUbicacionesCongelado(ubicaciones, orden, proceso);
	}
}