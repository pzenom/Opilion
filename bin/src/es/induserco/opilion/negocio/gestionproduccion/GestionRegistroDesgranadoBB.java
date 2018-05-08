package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.produccion.GestionProduccionDAO;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionRegistroDesgranadoBB implements IGestionRegistroDesgranadoService{

	//@Override
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso) throws Exception {
		return new GestionProduccionDataHelper().addRegistroProceso(gprod,proceso);
	}

	//@Override
	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception {
		return new GestionProduccionDataHelper().insertRegistroEnvasado(gprodf,gprodu,proceso);
	}	

	//@Override
	public Boolean updateRegistroProceso(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception {
		return new GestionProduccionDataHelper().updateRegistroProceso(gprodf,gprodu,proceso);
	}

	//@Override
	public Vector getREProceso(String orden, String proceso) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getREProceso(orden,proceso);
		return producto;
	}

	//@Override
	public Vector getREProceso(String orden,long idProducto,int idIncidencia) throws Exception {	
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getREProceso(orden,idProducto,idIncidencia);
		return producto;
	}		

	//@Override
	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return new GestionProduccionDataHelper().updateRECantProceso(mapaCantRE,orden,proceso);
	}

	//@Override
	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return new GestionProduccionDataHelper().updateREMermProceso(mapaCantRE,orden,proceso);
	}

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
	public Vector<GestionProduccion> getRegistroDesgranados(String orden, String fecha,
			Long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroDesgranados(orden, fecha, idProducto);
		return entradas;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroSeleccion(String orden,String fecha)throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroSeleccion(orden, fecha);
		return entradas;
	}

	//@Override
	public Vector getProductosDesgranado() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector productos = edh.getProductosDesgranado();
		return productos;
	}

	//@Override
	public Vector getProductosDesgranadoVaina() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector productos = edh.getProductosDesgranadoVaina();
		return productos;
	}

	//@Override
	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector subregistros = edh.getSubRegistrosEntrada(gprod,proceso);
		return subregistros;
	}

	//@Override
	public String getCodigoRegistroProceso(String proceso) throws Exception {
		return new GestionProduccionDataHelper().getCodigoRegistroProceso(proceso);
	}

	//@Override
	public String getCodigoEntradaOrden(String orden,String proceso) throws Exception {
		return new GestionProduccionDataHelper().getCodigoEntradaOrden(orden,proceso);
	}

	//@Override
	public Vector<GestionProduccion> getDetallesRegistroProceso(
			String tipoProceso, String proceso) throws Exception {
		return new GestionProduccionDataHelper().getDetallesRegistroProceso(tipoProceso, proceso);
	}

	//@Override
	public Vector<LineaProducto> getInfoMateria(String codigoEntrada)
			throws Exception {
		return new GestionProduccionDataHelper().getInfoMateria(codigoEntrada);
	}

	//@Override
	public Vector<LineaProducto> getInfoMateriasProceso(String orden,
			String proceso) throws Exception {
		return new GestionProduccionDataHelper().getInfoMateriasProceso(orden, proceso);
	}
}