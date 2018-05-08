package es.induserco.opilion.negocio.gestionubicaciones;

import java.util.ArrayList;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.negocio.UbicacionDataHelper;

public class GestionUbicacionesBB implements IGestionUbicacionesService{

	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception {
		return new UbicacionDataHelper().getLanzaderas(lanzadera);
	}
	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception {
		return new UbicacionDataHelper().getVehiculoCompleto(idVehiculo);
	}
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception {
		return new UbicacionDataHelper().getVehiculoLanzadera(idVehiculo);
	}
	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception {
		return new UbicacionDataHelper().insertaLogLanzadera(lanzadera);
	}
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception {
		return new UbicacionDataHelper().getNumeroProductosNuevaLanzadera(idVehiculo);
	}
	
	public Boolean addUbicacion(Ubicacion ubicacion) throws Exception {
		return new UbicacionDataHelper().addUbicacion(ubicacion);		
	}

	public Vector getUbicaciones(Long idDireccion) throws Exception{	
		//Recuperamos la lista de ubicaciones
		UbicacionDataHelper edh = new UbicacionDataHelper();
		Vector ubicaciones = edh.getUbicaciones(idDireccion);
		//si hay que aplicar alguna regla del negocio aqui!!!
		return ubicaciones;
	}	

	public Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate) throws Exception {
		return new UbicacionDataHelper().updateUbicacion(ubicacionFind, ubicacionUpdate);		
	}

	public Ubicacion loadUbicacion(Ubicacion ubicacion) throws Exception {
		return new UbicacionDataHelper().loadUbicacion(ubicacion);		
	}	

	public Vector getDireccionesUbicacion() throws Exception{	
		//Recuperamos la lista de ubicaciones
		UbicacionDataHelper edh = new UbicacionDataHelper();
		Vector ubicaciones = edh.getDireccionesUbicacion();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return ubicaciones;
	}

	
	public Boolean addRegistroUbicacion(RegistroUbicacion entry) throws Exception {
		return new UbicacionDataHelper().addRegistroUbicacion(entry);		
	}

	
	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return new UbicacionDataHelper().addRegistroUbicacionTmp(entry);
	}

	public Vector getPasillosUbicacion() throws Exception{	
		//Recuperamos la lista de ubicaciones
		UbicacionDataHelper edh = new UbicacionDataHelper();
		Vector pasillos = edh.getPasillosUbicacion();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return pasillos;
	}

	public Vector getEstanteriasUbicacion() throws Exception{	
		//Recuperamos la lista de ubicaciones
		UbicacionDataHelper edh = new UbicacionDataHelper();
		Vector estanterias = edh.getEstanteriasUbicacion();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return estanterias;
	}

	public Vector getAlturasUbicacion() throws Exception{	
		//Recuperamos la lista de ubicaciones
		UbicacionDataHelper edh = new UbicacionDataHelper();
		Vector alturas = edh.getAlturasUbicacion();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return alturas;
	}

	
	public String getPlanoAlmacen(int idAlmacen) throws Exception {
		return new UbicacionDataHelper().getPlanoAlmacen(idAlmacen);
	}

	
	public Ubicacion getUbicacion(Ubicacion ubi) throws Exception{
		return new UbicacionDataHelper().getUbicacion(ubi);
	}

	
	public long guardarRegistro(long idHueco, String registro, long idPalet)
			throws Exception {
		return new UbicacionDataHelper().guardarRegistro(idHueco, registro, idPalet);
	}

	
	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco)
			throws Exception {
		return new UbicacionDataHelper().getRegistrosAlmacenados(idHueco);
	}

	
	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubicacion)
			throws Exception {
		return new UbicacionDataHelper().getHuecosOcupados(ubicacion);
	}

	
	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento)
			throws Exception {
		new UbicacionDataHelper().salvarUbicaciones(ubicaciones, movimiento);
	}

	
	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia,
			boolean registroEntrada, boolean produccion)
			throws Exception {
		return new UbicacionDataHelper().sacarUbicaciones(ubicaciones, incidencia,
				registroEntrada, produccion);
	}

	
	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13)
			throws Exception {
		return new UbicacionDataHelper().getUbicacionesEntrada(entrada, ean13);
	}

	
	public void reubicar(String orden, String entrada, String idUbicacion,
			String idUbicacionNueva, long idPalet, double cantidad) throws Exception {
		new UbicacionDataHelper().reubicar(orden, entrada, idUbicacion, idUbicacionNueva, idPalet, cantidad);
	}

	
	public Vector<Incidencia> getIncidencias() throws Exception {
		return new UbicacionDataHelper().getIncidencias();
	}

	
	public long ubicarSeleccion(String registro, String idHueco, long idPalet,
			double cantidad) throws Exception {
		return new UbicacionDataHelper().ubicarSeleccion(registro, idHueco, idPalet, cantidad);
	}

	
	public void ubicarCongelado(String proceso, String tipo, Ubicacion ubicacion)
			throws Exception {
		new UbicacionDataHelper().ubicarCongelado(proceso, tipo, ubicacion);
	}

	
	public Vector<Ubicacion> getBigBags() throws Exception {
		return new UbicacionDataHelper().getBigBags();
	}

	
	public void registrarAlmacen(Ubicacion almacen) throws Exception {
		new UbicacionDataHelper().registrarAlmacen(almacen);
	}

	
	public long getIdHuecoVehiculo(int idVehiculo) throws Exception {
		return new UbicacionDataHelper().getIdHuecoVehiculo(idVehiculo);
	}

	
	public Vector<Vehiculo> getVehiculos() throws Exception {
		return new UbicacionDataHelper().getVehiculos();
	}

	
	public int mover(String origen, String producto, double cantidad,
			String destino) throws Exception {
		return new UbicacionDataHelper().mover(origen, producto, cantidad, destino);
	}
	
	public int merma(String origen, String producto, double cantidad) throws Exception {
		return new UbicacionDataHelper().merma(origen, producto, cantidad);
	}
	
	public long getIdHueco(String descripcion) throws Exception {
		return new UbicacionDataHelper().getIdHueco(descripcion);
	}

	public int checkUbicacion(Lote l) throws Exception {
		return new UbicacionDataHelper().checkUbicacion(l);
	}

	public long getIdUbicacion(long idHueco, String registro) throws Exception {
		return new UbicacionDataHelper().getIdUbicacion(idHueco, registro);
	}
	
	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception {
		return new UbicacionDataHelper().getMovimientosLote(lote);
	}
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		return new UbicacionDataHelper().getUbicacionesRegistro(lote);
	}
	
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception{
		return new UbicacionDataHelper().getModificacionesStock(lote);
	}
	
	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception{
		return new UbicacionDataHelper().getUbicacionRegistro(lote, idHueco);
	}
}