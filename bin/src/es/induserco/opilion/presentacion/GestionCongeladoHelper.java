package es.induserco.opilion.presentacion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCongeladoService;

public class GestionCongeladoHelper {
	
	public String getFechaRegistro() throws Exception{
		return ((IGestionRegistroCongeladoService)(new ServiceLocator()).getService("IGestionRegistroCongeladoService")).getFechaRegistro();
	}

	public Vector getProductos() throws Exception{
		return ((IGestionRegistroCongeladoService)(new ServiceLocator()).getService("IGestionRegistroCongeladoService")).getProductos();
	}

	public Vector<GestionProduccion> getRegistroCongelados(String orden,String fecha,Long idProducto) throws Exception{
		return ((IGestionRegistroCongeladoService)(new ServiceLocator()).getService("IGestionRegistroCongeladoService")).getRegistroCongelados(orden,fecha,idProducto);
	}

	public Vector getRegistroEntradaCongelado(String orden,long idProducto) throws Exception{
		return ((IGestionRegistroCongeladoService)(new ServiceLocator()).getService("IGestionRegistroCongeladoService")).getRegistroEntradaCongelado(orden,idProducto);
	}

	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones, String orden, String proceso)throws Exception {
			((IGestionRegistroCongeladoService) (new ServiceLocator()).getService("IGestionRegistroCongeladoService")).
				guardarUbicacionesCongelado(ubicaciones,orden,proceso);
	}
}