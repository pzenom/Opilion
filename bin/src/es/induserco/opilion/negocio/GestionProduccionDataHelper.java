package es.induserco.opilion.negocio;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.produccion.IGestionProduccionDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class GestionProduccionDataHelper {
	
	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addOrdenEnvasado(envasado);
	}	
	
	public Boolean addRegistroEnvasado (GestionProduccion gprod) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addRegistroEnvasado(gprod);
	}	

	public Boolean updateRegistroEnvasado (GestionProduccion gprodf,GestionProduccion gprodu) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateRegistroEnvasado(gprodf,gprodu);
	}

	public Boolean deleteRegistroEnvasado (GestionProduccion gprodf,GestionProduccion gprodd) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).deleteRegistroEnvasado(gprodf,gprodd);
	}
	
	public Vector getProductos()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getProductos();
	}

	public Vector getOperarios()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getOperarios();
	}

	public Vector getEnvases()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getEnvases();
	}

	public String getFechaRegistro()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getFechaRegistro();
	}

	public String getHoraInicioProceso(String orden) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getHoraInicioProceso(orden);
	}		

	public Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).
				getService("IGestionProduccionDataService")).getRegistroEnvasados(orden,idProducto, filtro);
	}
	
	public Vector getMermasProduccion(String fecha,Long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getMermasProduccion(fecha,idProducto);
	}

	public Vector<GestionProduccion> getRegistroCribados(String orden, String fecha,Long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroCribados(orden,fecha,idProducto);
	}

	public Boolean addRegistroProceso (GestionProduccion gprod, String proceso) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addRegistroProceso(gprod, proceso);
	}
	
	public Boolean updateRegistroProceso (GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateRegistroProceso(gprodf,gprodu,proceso);		 
	}

	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).insertRegistroEnvasado(gprodf,gprodu,proceso);		 
	}

	public Vector getREProceso(String orden, String proceso) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getREProceso(orden,proceso);	
	}		

	public Vector getREProceso(String orden, long idProducto, int idIncidencia)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getREProceso(orden,idProducto,idIncidencia);
	}

	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception {	
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateRECantProceso(mapaCantRE,orden,proceso);	
	}		

	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception {	
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateREMermProceso(mapaCantRE,orden,proceso);	
	}		

	public Vector<GestionProduccion> getRegistroDesgranados(String orden, String fecha,Long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroDesgranados(orden,fecha,idProducto);
	}

	public Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha,
			Long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroCongelados(orden,fecha,idProducto);
	}

	public Vector<GestionProduccion> getRegistroFumigados(String orden, String fecha)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroFumigados(orden,fecha);
	}

	public Vector<GestionProduccion> getRegistroSeleccion(String orden, String fecha)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroSeleccion(orden,fecha);
	}	

	public Vector getRegistroIngredientes()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes();
	}

	public Vector getRegistroIngredientes(List listaingre)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes(listaingre);	
	}

	public Vector getRegistroIngredientes(String orden, long idCategoria, long idProducto) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes(orden, idCategoria, idProducto);
	}

	public Vector getRegistroIngredientes(String orden, long idProducto) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes(orden, idProducto);
	}
	
	public Vector getRegistroIngredientes(long idProducto, String filtro) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes(idProducto, filtro);
	}	
	
	public Vector getRegistroEnvases(long idEnvase, String filtro)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroEnvases(idEnvase, filtro);
	}

	public Vector getRegistroEnvases(List listenvases)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroEnvases(listenvases);
	}

	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod, GestionProduccion gpro)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addTmpRegistroIngredientesEnvases(gprod,gpro);
	}

	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	public Vector getTmpRegistroIngredientesEnvases(String orden)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getTmpRegistroIngredientesEnvases(orden);
	}

	public Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception{
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getSubRegistrosEntradaEnvasado(orden);
	}

	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateTmpRegistroMermasEnvases(mapaCantidades,orden);
	}

	public Vector getTmpRegistroIngredientesEnvases()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getTmpRegistroIngredientesEnvases();

	}

	public Vector getRegistroEntradaCongelado(String orden,long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroEntradaCongelado(orden,idProducto);
	}	

	public Vector getRegistroEntradaCribado(String orden,long idProducto)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroEntradaCribado(orden,idProducto);
	}
	
	public Vector getDetalleCribado(String codigoEntrada) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getDetalleCribado(codigoEntrada);
	}

	public Vector getProductosDesgranado()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getProductosDesgranado();	
	}

	public Vector getProductosDesgranadoVaina()throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getProductosDesgranadoVaina();
	}

	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getSubRegistrosEntrada(gprod, proceso);
	}

	public String getCodigoRegistroProceso(String proceso)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getCodigoRegistroProceso(proceso);
	}

	public String getCodigoEntradaOrden(String orden, String proceso) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getCodigoEntradaOrden(orden, proceso);
	}

	public String generarNumeroLote(String codigo)throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).generarNumeroLote(codigo);
	}

	public Vector getPresentacionProductos(boolean stockSuficiente)throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getPresentacionProductos(stockSuficiente);
	}

	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getInfoMateriaPrima(idProducto);
	}

	//Para el registro de MP para el proceso de Envasado
	public Boolean addComponentesEnvasado(List listaelementos, String orden,String tipo) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addComponentesEnvasado(listaelementos,orden,tipo);
	}

	public Boolean insertaCantidadesEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).insertaCantidadesEnvases(mapaCantidades,orden);
	}	

	public Boolean addComponentesProceso(List listaelementos, String orden,String tipo) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).addComponentesProceso(listaelementos,orden,tipo);
	}

	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getEtiquetaEN(codigoOrden);
	}

	public GestionProduccion getMaestroEN(String orden) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getMaestroEN(orden);
	}

	public Vector getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getProcesosPendientes(gprod, tipo);
	}

	public Boolean delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).delProcesoPendiente(envasado);
	}

	public Vector getEnvasesInfo() throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getEnvases();
	}

	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception {
		return((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getInfoProcesosEnv(orden, filtro);
	}

	public Vector <LineaProducto> getLineaProducto(String idOrden, String proceso,String filtro,int option) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getLineaProducto(idOrden, proceso, filtro, option);
	}
	
	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getIngredientesProducto(idProducto, filtro);
	}

	public Vector getEnvasesProducto(Long idProducto, String filtro) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getEnvasesProducto(idProducto, filtro);
	}
	
	public Vector<GestionProduccion> getDetalleEN(String orden) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getDetalleEN(orden);
	}

	public Vector<GestionProduccion> getDetallesRegistroProceso(String tipoProceso, String proceso) throws Exception {
		return ((IGestionProduccionDataService)
				(new ServiceLocator()).getService("IGestionProduccionDataService")).getDetallesRegistroProceso(tipoProceso, proceso);
	}

	public Vector<LineaProducto> getInfoMateria(String codigoEntrada) throws Exception{
		return ((IGestionProduccionDataService)	(new ServiceLocator()).getService("IGestionProduccionDataService")).getInfoMateria(codigoEntrada);
	}

	public Vector<LineaProducto> getInfoMateriasProceso(String orden, String proceso) throws Exception{
		return ((IGestionProduccionDataService)
				(new ServiceLocator()).getService("IGestionProduccionDataService")).getInfoMateriasProceso(orden,proceso);
	}

	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones,
			String orden, String proceso) throws Exception {
		((IGestionProduccionDataService)
				(new ServiceLocator()).getService("IGestionProduccionDataService")).guardarUbicacionesCongelado(ubicaciones, orden, proceso);
	}
	
	public Vector<Producto> getTrazabilidadRegistroEntrada(String registro) throws Exception{
		return ((IGestionProduccionDataService) (new ServiceLocator()).getService("IGestionProduccionDataService")).getTrazabilidadRegistroEntrada(registro);
	}
	
	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception{
		((IGestionProduccionDataService)
				(new ServiceLocator()).getService("IGestionProduccionDataService")).actualizaCaducidadEnvasado(proceso, caducidad, usuario);
	}
}