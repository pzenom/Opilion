package es.induserco.opilion.negocio;

import java.util.ArrayList;
import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.datos.ubicacion.IUbicacionDataService;
import es.induserco.opilion.datos.ubicacion.UbicacionDataService;

public class UbicacionDataHelper {
	
	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getVehiculoCompleto(idVehiculo);		 
	}
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getVehiculoLanzadera(idVehiculo);		 
	}
	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception{
		return ((IUbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).insertaLogLanzadera(lanzadera);
	}
	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception{
		return ((IUbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getLanzaderas(lanzadera);
	}
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getNumeroProductosNuevaLanzadera(idVehiculo);		 
	}
	
	public Boolean addUbicacion (Ubicacion ubicacion) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).addUbicacion(ubicacion);		 
	}

	public Vector getUbicaciones(Long idDireccion)throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getUbicaciones(idDireccion);
	}

	public Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate)  throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).updateUbicacion(ubicacionFind, ubicacionUpdate);
	}

	public Ubicacion loadUbicacion(Ubicacion ubicacion)  throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).loadUbicacion(ubicacion);
	}

	public Vector getDireccionesUbicacion() throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getDireccionesUbicacion();
	}

	public Boolean addRegistroUbicacion (RegistroUbicacion entry) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).addRegistroUbicacion(entry);		 
	}

	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).addRegistroUbicacionTmp(entry);
	}	

	public Vector getPasillosUbicacion() throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getPasillosUbicacion();
	}	

	public Vector getEstanteriasUbicacion() throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getEstanteriasUbicacion();
	}		

	public Vector getAlturasUbicacion() throws Exception {
		return((IUbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getAlturasUbicacion();
	}

	public Boolean addRegistrosTemporales() throws Exception {
		System.out.println("UDH: addregtemp");
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).addRegistrosTemporales();
	}

	public String getPlanoAlmacen(int idAlmacen) throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getPlanoAlmacen(idAlmacen);
	}

	public Ubicacion getUbicacion(Ubicacion ubi) throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getUbicacion(ubi);
	}

	public long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).guardarRegistro(idHueco, registro, idPalet);
	}

	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco)throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getRegistrosAlmacenados(idHueco);
	}

	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubicacion) throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getHuecosOcupados(ubicacion);
	}

	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento) throws Exception {
		((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).salvarUbicaciones(ubicaciones, movimiento);
	}

	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia, boolean registroEntrada, boolean produccion) throws Exception{
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).
				sacarUbicaciones(ubicaciones, incidencia, registroEntrada, produccion);
	}

	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception {
		return((UbicacionDataService)
				(new ServiceLocator()).getService("IUbicacionDataService")).getUbicacionesEntrada(entrada, ean13);
	}

	public void reubicar(String orden, String entrada, String idUbicacion,
			String idUbicacionNueva, long idPalet, double cantidad) throws Exception{
		((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).reubicar(orden,
				entrada, idUbicacion, idUbicacionNueva, idPalet, cantidad);
	}

	public Vector<Incidencia> getIncidencias() throws Exception {
		return((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getIncidencias();
	}

	public long ubicarSeleccion(String registro, String idHueco, long idPalet, double cantidad) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).ubicarSeleccion(registro,
				idHueco, idPalet, cantidad);
	}

	public void ubicarCongelado(String proceso, String tipo, Ubicacion ubicacion) throws Exception {
		((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).ubicarCongelado(proceso, tipo, ubicacion);
	}

	public Vector<Ubicacion> getBigBags() throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getBigBags();
	}

	public void registrarAlmacen(Ubicacion almacen) throws Exception {
		((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).registrarAlmacen(almacen);
	}

	public long getIdHuecoVehiculo(int idVehiculo) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getIdHuecoVehiculo(idVehiculo);
	}

	public Vector<Vehiculo> getVehiculos() throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).getVehiculos();
	}

	public int mover(String origen, String producto, double cantidad,
			String destino) throws Exception{
		return ((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).mover(origen, producto, cantidad, destino);
	}

	public int merma(String origen, String producto, double cantidad) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).
				getService("IUbicacionDataService")).merma(origen, producto, cantidad);
	}

	public long getIdHueco(String descripcion) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getIdHueco(descripcion);
	}

	public int checkUbicacion(Lote l) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).checkUbicacion(l);
	}

	public long getIdUbicacion(long idHueco, String registro) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getIdUbicacion(idHueco, registro);
	}
	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getMovimientosLote(lote);
	}
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getUbicacionesRegistro(lote);
	}
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception{
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getModificacionesStock(lote);
	}
	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception{
		return ((UbicacionDataService)(new ServiceLocator()).getService("IUbicacionDataService")).getUbicacionRegistro(lote, idHueco);
	}
}