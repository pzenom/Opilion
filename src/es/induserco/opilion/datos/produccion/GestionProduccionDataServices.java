package es.induserco.opilion.datos.produccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class GestionProduccionDataServices implements IGestionProduccionDataService {

	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception{
		return (new GestionProduccionDAO()).insertRegistroEnvasado(gprodf,gprodu,proceso);
	}

	public Vector getEnvases() throws Exception {
		return (new GestionProduccionDAO()).getEnvases();
	}

	public String getFechaRegistro() throws Exception {
		return (new GestionProduccionDAO()).getFechaRegistro();
	}

	public String getHoraInicioProceso(String orden) throws Exception {
		return (new GestionProduccionDAO()).getHoraInicioProceso(orden);
	}

	public Vector getProductos() throws Exception {
		return (new GestionProduccionDAO()).getProductos();
	}

	public Boolean updateRegistroEnvasado(GestionProduccion gprodf, GestionProduccion gprodu) throws Exception {
		return (new GestionProduccionDAO()).updateRegistroEnvasado(gprodf,gprodu);
	}

	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodd) throws Exception {
		return (new GestionProduccionDAO()).deleteRegistroEnvasado(gprodf,gprodd);
	}

	public Vector getMermasProduccion(String fecha, Long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getMermasProduccion(fecha,idProducto);
	}

	//@Override
	public Vector getOperarios() throws Exception {
		return (new GestionProduccionDAO()).getOperarios();
	}

	//@Override
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso)
			throws Exception {
		return (new GestionProduccionDAO()).addRegistroProceso(gprod, proceso);
	}

	//@Override
	public Vector getREProceso(String orden, String proceso) throws Exception {
		return (new GestionProduccionDAO()).getREProceso(orden, proceso);
	}

	//@Override
	public Vector getREProceso(String orden,long idProducto,int idIncidencia) throws Exception {	
		return (new GestionProduccionDAO()).getREProceso(orden,idProducto,idIncidencia);
	}		

	//@Override
	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return (new GestionProduccionDAO()).updateRECantProceso(mapaCantRE,orden,proceso);
	}	

	//@Override
	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return (new GestionProduccionDAO()).updateREMermProceso(mapaCantRE,orden,proceso);
	}

	//@Override
	public Boolean updateRegistroProceso(GestionProduccion gprodf,
			GestionProduccion gprodu, String proceso) throws Exception {
		return (new GestionProduccionDAO()).updateRegistroProceso(gprodf, gprodu, proceso);
	}

	//@Override
	public Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha, Long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getRegistroCongelados(orden, fecha, idProducto);
	}

	//@Override
	public Vector getRegistroIngredientes() throws Exception {
		return (new GestionProduccionDAO()).getRegistroIngredientes();
	}

	//@Override
	public Vector getRegistroIngredientes(List listaingred) throws Exception {
		return (new GestionProduccionDAO()).getRegistroIngredientes(listaingred);
	}

	//@Override
	public Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception{
		return (new GestionProduccionDAO()).getSubRegistrosEntradaEnvasado(orden);
	}

	//@Override
	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod,GestionProduccion gpro) throws Exception {
		return (new GestionProduccionDAO()).addTmpRegistroIngredientesEnvases(gprod,gpro);
	}

	//@Override
	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception {
		return (new GestionProduccionDAO()).updateTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	//@Override
	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,String orden) throws Exception {
		return (new GestionProduccionDAO()).updateTmpRegistroMermasEnvases(mapaCantidades,orden);
	}

	//@Override
	public Vector getTmpRegistroIngredientesEnvases() throws Exception {
		return (new GestionProduccionDAO()).getTmpRegistroIngredientesEnvases();
	}

	//@Override
	public Vector getTmpRegistroIngredientesEnvases(String orden) throws Exception {
		return (new GestionProduccionDAO()).getTmpRegistroIngredientesEnvases(orden);
	}

	//@Override
	public Vector getRegistroIngredientes(String orden, long idCategoria,
			long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getRegistroIngredientes(orden, idCategoria, idProducto);
	}

	//@Override
	public Vector getRegistroIngredientes(long idProducto, String filtro) throws Exception {
		return (new GestionProduccionDAO()).getRegistroIngredientes(idProducto, filtro);
	}

	//@Override
	public Vector getRegistroEnvases(long idEnvase, String filtro) throws Exception {
		return (new GestionProduccionDAO()).getRegistroEnvases(idEnvase, filtro);
	}

	//@Override
	public Vector getRegistroEntradaCongelado(String orden, long idProducto)throws Exception {
		return (new GestionProduccionDAO()).getRegistroEntradaCongelado(orden, idProducto);
	}

	//@Override
	public Vector getRegistroEntradaCribado(String orden,long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getRegistroEntradaCribado(orden, idProducto);
	}
	
	public Vector getDetalleCribado(String codigoEntrada)  throws Exception {
		return (new GestionProduccionDAO()).getDetalleCribado(codigoEntrada);
	}

	public Vector getRegistroIngredientes(String orden, long idProducto)throws Exception {
		return (new GestionProduccionDAO()).getRegistroIngredientes(orden, idProducto);
	}

	public Vector getProductosDesgranado() throws Exception {
		return (new GestionProduccionDAO()).getProductosDesgranado();
	}

	//@Override
	public Vector getProductosDesgranadoVaina() throws Exception {
		return (new GestionProduccionDAO()).getProductosDesgranadoVaina();
	}

	//@Override
	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception {
		return (new GestionProduccionDAO()).getSubRegistrosEntrada(gprod, proceso);
	}

	//@Override
	public String getCodigoRegistroProceso(String proceso) throws Exception {
		return (new GestionProduccionDAO()).getCodigoRegistroProceso(proceso);
	}

	public String getCodigoEntradaOrden(String orden, String proceso) throws Exception {
		return (new GestionProduccionDAO()).getCodigoEntradaOrden(orden, proceso);
	}

	public String generarNumeroLote(String codigo) throws Exception {
		return (new GestionProduccionDAO().generarNumeroLote(codigo));
	}

	public Vector getPresentacionProductos(boolean stockSuficiente) throws Exception {
		return (new GestionProduccionDAO()).getPresentacionProductos(stockSuficiente);
	}

	public Vector getRegistroEnvases(List listenvases) throws Exception {
		return (new GestionProduccionDAO()).getRegistroEnvases(listenvases);
	}

	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getInfoMateriaPrima(idProducto);
	}

	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		return (new GestionProduccionDAO()).addOrdenEnvasado(envasado);
	}

	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception {
		return (new GestionProduccionDAO()).addRegistroEnvasado(gprod);
	}

	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden)
			throws Exception {
		return (new GestionProduccionDAO()).addTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	public Boolean insertaCantidadesEnvases(Map mapaCantidades, String orden)throws Exception {
		return (new GestionProduccionDAO()).insertaCantidadesEnvases(mapaCantidades,orden);
	}
	
	public Vector<GestionProduccion> getRegistroFumigados(String orden, String fecha) throws Exception {
		return (new GestionProduccionDAO()).getRegistroFumigados(orden, fecha);
	}

	public Vector<GestionProduccion> getRegistroSeleccion(String orden, String fecha) throws Exception {
		return (new GestionProduccionDAO()).getRegistroSeleccion(orden, fecha);
	}	

	public Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro) throws Exception {
		return (new GestionProduccionDAO()).getRegistroEnvasados(orden,idProducto, filtro);
	}	

	public Vector<GestionProduccion> getRegistroCribados(String orden, String fecha, Long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getRegistroCribados(orden, fecha, idProducto);
	}

	public Vector<GestionProduccion> getRegistroDesgranados(String orden, String fecha, Long idProducto) throws Exception {
		return (new GestionProduccionDAO()).getRegistroDesgranados(orden, fecha, idProducto);
	}	

	public GestionProduccion getMaestroEN(String orden) throws Exception {
		return (new GestionProduccionDAO()).getMaestroEN(orden);
	}

	public Vector<GestionProduccion> getDetalleEN(String orden) throws Exception {
		return (new GestionProduccionDAO()).getDetalleEN(orden);
	}
	
	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception {
		return (new GestionProduccionDAO()).getEtiquetaEN(codigoOrden);
	}

	public Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception {
		return (new GestionProduccionDAO()).getProcesosPendientes(gprod, tipo);
	}

	public Boolean delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		return (new GestionProduccionDAO()).delProcesoPendiente(envasado);
	}

	public RegistroEnvasado getProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		return (new GestionProduccionDAO()).getProcesoPendiente(envasado);
	}

	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception {
		return (new GestionProduccionDAO()).getInfoProcesosEnv(orden, filtro);
	}

	public Vector <LineaProducto> getLineaProducto(String idOrden, String proceso,String filtro,int option) throws Exception {
		return (new GestionProduccionDAO()).getLineaProducto(idOrden, proceso,filtro,option);
	}

	public int idGestionToProducto(int id) throws Exception {
		return (new GestionProduccionDAO()).idGestionToProducto(id);
	}

	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		return (new GestionProduccionDAO()).getIngredientesProducto(idProducto, filtro);
	}

	public Vector getEnvasesProducto(Long idProducto, String filtro) throws Exception {
		return (new GestionProduccionDAO()).getEnvasesProducto(idProducto, filtro);
	}

	public boolean iniciaProcesoEnvasado(String orden, String operario) throws Exception {
		return (new GestionProduccionDAO()).iniciaProcesoEnvasado(orden, operario);
	}

	public boolean pausaProcesoEnvasado(String orden, String operario, double elaborado, String elaboradoAgrupar, String observaciones) throws Exception {
		return (new GestionProduccionDAO()).pausaProcesoEnvasado(orden, operario, elaborado, elaboradoAgrupar, observaciones);
	}

	public boolean finalizaProcesoEnvasado(RegistroEnvasado reg, boolean anula) throws Exception {
		return (new GestionProduccionDAO()).finalizaProcesoEnvasado(reg, anula);
	}

	public boolean productoCompuesto(String orden) throws Exception {
		return (new GestionProduccionDAO()).productoCompuesto(orden);
	}
	
	public Vector<GestionProduccion> getProcesosEnvasado(String orden, String fecha, String lote, Long idProducto, String estados[], char habilitado) throws Exception {
		return (new GestionProduccionDAO()).getProcesosEnvasado(orden, fecha, lote, idProducto, estados, habilitado);
	}

	public String getObservacionesEnvasado(String orden) throws Exception {
		return (new GestionProduccionDAO()).getObservacionesEnvasado(orden);
	}
	
	public boolean actualizarPrecioCosteProducto(String orden) throws Exception {
		return (new GestionProduccionDAO()).actualizarPrecioCosteProducto(orden);
	}

	public Vector<RegistroEntrada> getDetallesEnvasado(String ordenEnvasado) throws Exception {
		return (new GestionProduccionDAO()).getDetallesEnvasado(ordenEnvasado);
	}

	public boolean setUbicado(String ordenEnvasado, Vector<Ubicacion> ubicaciones, boolean actualizarCampoUbicado) throws Exception {
		return (new GestionProduccionDAO()).setUbicado(ordenEnvasado, ubicaciones, actualizarCampoUbicado);
	}

	public Vector<LineaProducto> getEnvasesMateriasEnvasado(String ordenEnvasado, String filtro, int option) throws Exception {
		return (new GestionProduccionDAO()).getEnvasesMateriasEnvasado(ordenEnvasado, filtro, option);
	}

	public Vector<RegistroActividad> getRegistrosActividad(String proceso) throws Exception {
		return (new GestionProduccionDAO()).getRegistrosActividad(proceso);
	}

	public boolean actualizaProcesoEnvasado(int cantidad, String orden,
			String fechaInicio, String operario, String observaciones,
			ArrayList<LineaProducto> materias,
			ArrayList<LineaProducto> envases,
			ArrayList<LineaProducto> materiasLimpiar,
			ArrayList<LineaProducto> envasesLimpiar) throws Exception {
		return (new GestionProduccionDAO()).actualizaProcesoEnvasado(cantidad, orden, fechaInicio, operario, observaciones,
				materias, envases, materiasLimpiar, envasesLimpiar);
	}

	public Boolean addComponentesEnvasado(List<String> listaelementos, String orden, String tipo) throws Exception {
		return (new GestionProduccionDAO()).addComponentesEnvasado(listaelementos, orden, tipo);
	}

	public Boolean addComponentesProceso(List<String> listaelementos, String orden, String tipo) throws Exception {
		return (new GestionProduccionDAO()).addComponentesProceso(listaelementos, orden, tipo);
	}

	public Vector<Ubicacion> getUbicacionesEnvasado(String orden, String lote) {
		return (new GestionProduccionDAO()).getUbicacionesEnvasado(orden, lote);
	}

	public Vector<GestionProduccion> getDetallesRegistroProceso(String tipoProceso, String proceso) throws Exception {
		return (new GestionProduccionDAO()).getDetallesRegistroProceso(tipoProceso, proceso);
	}

	public Vector<LineaProducto> getInfoMateria(String codigoEntrada) throws Exception {
		return (new GestionProduccionDAO()).getInfoMateria(codigoEntrada);
	}

	public Vector<LineaProducto> getInfoMateriasProceso(String ordenProceso, String proceso) throws Exception {
		return (new GestionProduccionDAO()).getInfoMateriasProceso(ordenProceso, proceso);
	}

	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones, String orden, String proceso) throws Exception {
		(new GestionProduccionDAO()).guardarUbicacionesCongelado(ubicaciones, orden, proceso);
	}

	public Vector<Producto> getAgrupaciones(boolean suficienteStock) throws Exception {
		return (new GestionProduccionDAO()).getAgrupaciones(suficienteStock);
	}

	public void updateFechaProgramada(GestionProduccion gpro) throws Exception {
		(new GestionProduccionDAO()).updateFechaProgramada(gpro);
	}

	public char compruebaLoteCantidad(Lote lote) throws Exception {
		return (new GestionProduccionDAO()).compruebaLoteCantidad(lote);
	}

	public Vector<LineaProducto> getProductosComponenLote(String lote) throws Exception {
		return (new GestionProduccionDAO()).getProductosComponenLote(lote);
	}

	public Vector<RegistroEntrada> getLotesIngrediente(long idMateriaPrima) throws Exception {
		return (new GestionProduccionDAO()).getLotesIngrediente(idMateriaPrima);
	}

	public Vector<RegistroEntrada> getLotesEnvase(long idEnvase) throws Exception {
		return (new GestionProduccionDAO()).getLotesEnvase(idEnvase);
	}

	public Vector<Producto> getTrazabilidadRegistroEntrada(String registro) throws Exception {
		return (new GestionProduccionDAO()).getTrazabilidadRegistroEntrada(registro);
	}

	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception {
		(new GestionProduccionDAO()).actualizaCaducidadEnvasado(proceso, caducidad, usuario);
	}
	
	public boolean esFabaFresca(String orden) throws Exception {
		return (new GestionProduccionDAO()).esFabaFresca(orden);
	}
}