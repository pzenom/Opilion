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

public interface IGestionUbicacionesService {

	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception;
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception;
	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception;
	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception;
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception;
	
	public Boolean addUbicacion (Ubicacion ubicacion) throws Exception;

	public Vector getUbicaciones(Long idDireccion) throws Exception;

	public Boolean updateUbicacion (Ubicacion ubicacionFind, Ubicacion ubicacionUpdate)throws Exception;

	public Ubicacion loadUbicacion (Ubicacion entry) throws Exception;

	public Vector getDireccionesUbicacion()throws Exception;

	public Boolean addRegistroUbicacion(RegistroUbicacion entry) throws Exception;

	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry)throws Exception;

	public Vector getPasillosUbicacion()throws Exception;

	public Vector getEstanteriasUbicacion()throws Exception;

	public Vector getAlturasUbicacion()throws Exception;
	public String getPlanoAlmacen(int idAlmacen) throws Exception;
	public Ubicacion getUbicacion(Ubicacion ubi) throws Exception;
	public long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception;
	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco) throws Exception;
	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubicacion) throws Exception;
	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento) throws Exception;
	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia, boolean registroEntrada, boolean produccion) throws Exception;
	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception;
	public void reubicar(String orden, String entrada, String idUbicacion, String idUbicacionNueva, long idPalet, double cantidad) throws Exception;
	public Vector<Incidencia> getIncidencias() throws Exception;
	public long ubicarSeleccion(String registro, String idHueco, long idPalet, double cantidad) throws Exception;
	public void ubicarCongelado(String proceso, String tipoProceso, Ubicacion ubicacion) throws Exception;
	public Vector<Ubicacion> getBigBags()throws Exception;
	public void registrarAlmacen(Ubicacion almacen) throws Exception;
	public long getIdHuecoVehiculo(int idVehiculo) throws Exception;
	public Vector<Vehiculo> getVehiculos() throws Exception;
	public int mover(String origen, String producto, double cantidad, String destino) throws Exception;
	public int merma(String origen, String producto, double cantidad) throws Exception;
	public long getIdHueco(String ubicacion) throws Exception;
	public int checkUbicacion(Lote l) throws Exception;
	public long getIdUbicacion(long idHueco, String registro) throws Exception;
	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception;
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception;
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception;
	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception;
}