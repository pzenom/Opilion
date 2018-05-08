package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionRegistroCongeladoBB implements IGestionRegistroCongeladoService{

	//@Override
	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionProduccionDataHelper().getFechaRegistro();
	}

	//@Override
	public Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha,
			Long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroCongelados(orden, fecha, idProducto);
		return entradas;
	}

	//@Override
	public Vector getRegistroEntradaCongelado(String orden,long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector entradas = edh.getRegistroEntradaCongelado(orden, idProducto);
		return entradas;
	}

	//@Override
	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones,
			String orden, String proceso) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		edh.guardarUbicacionesCongelado(ubicaciones, orden, proceso);
	}
}