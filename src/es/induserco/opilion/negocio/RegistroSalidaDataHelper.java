package es.induserco.opilion.negocio;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Comercial;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.PortesTransporte;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.data.entidades.TemperaturaTransporte;
import es.induserco.opilion.datos.salida.IRegistroSalidaDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class RegistroSalidaDataHelper {
	
	public String getFechaRegistro()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getFechaRegistro();
	}
	
	public String getCodigoAlbaran()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getCodigoAlbaran();
	}	

	public Boolean addRegistroSalida (RegistroSalida entry) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).addRegistroSalida(entry);
	}

	public Boolean updateRegistroSalida (RegistroSalida entryf,RegistroSalida entryu) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).updateRegistroSalida(entryf,entryu);
	}

	public Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception {	
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getRSLineaAlbaran(codigoAlbaran,linNum);		 
	}	

	public Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception {	
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getRSLineaAlbaranTotal(codigoAlbaran,linNum);		 
	}	

	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getEncabezadoAlbaranOrden(orden);
	}	

	public Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getTotalesAlbaranOrden(codigoAlbaran);
	}	

	public Boolean deleteRegistroSalida (RegistroSalida entryf,RegistroSalida entryd) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).deleteRegistroSalida(entryf,entryd);		 
	
	}

	public Vector getProveedores()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getClientes();

	}

	public Vector getProductos()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getProductos();	
	}

	public Vector getVehiculos()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getVehiculos();
	}

	public Vector getRegistroSalidas(String lote, String codigoSalida, Date fecha,Long idProducto)throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getRegistroSalidas(lote, codigoSalida,fecha,idProducto);
	}

	public Vector getTipoVehiculos()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getTipoVehiculos();	
	}

	public Vector getComerciales()throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getComerciales();
	}

	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getEncabezadoAlbaran(codigoAlbaran);
	}

	public Vector getDetalleAlbaran(Albaran albaran) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getDetalleAlbaran(albaran);
	}	

	public Boolean addAlbaran(Albaran albaran) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).addAlbaran(albaran);
	}

	public Vector loadRegistroSalida(String codigoSalida) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).loadRegistroSalida(codigoSalida);		
	}

	public Vector getAlbaran(String codigoAlbaran, Date fecha,Long idCliente) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getAlbaran(codigoAlbaran,fecha,idCliente);
	}

	public Boolean addFactura(String codigoAlbaran,Factura fact) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).addFactura(codigoAlbaran,fact);
	}	

	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).updateDetaAlba(mapaPrecUnit,codigoAlbaran);
	}

	public Vector getAlbaranes(Albaran albaran, int filtro) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getAlbaranes(albaran,filtro);
	}
	
	public RegistroSalida getAlbaranRS(String orden) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getAlbaranRS(orden);
	}

	public Vector getDireccionesClientes(String tipo) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getDireccionesClientes(tipo);
	}

	public Vector getFormasDePago() throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getFormasDePago();
	}

	public Albaran getTotalesOrden(String bgmNum) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getTotalesOrden(bgmNum);
	}

	public String getDireccionesEAN(String idDireccion) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getDireccionesEAN(idDireccion);
	}

	public String getInfoProducto(String ean) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getInfoProducto(ean);
	}

	public Vector getDireccionesCantidades(Long idOrders, String linNum) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getDireccionesCantidades(idOrders, linNum);
	}

	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getLineasPedido(codigo);
	}

	public boolean actualizaEstadoPedido(String pedido, char c) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).actualizaEstadoPedido(pedido, c);
	}

	public Vector<Factura> getFacturas(Factura factura, String fechaInicio,
			String fechaFin, Usuario usuario) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getFacturas(factura, fechaInicio, fechaFin, usuario);
	}

	public Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getAlbaran(codigoAlbaran, lineaCarrefour);
	}

	public Factura getFactura(String codigoFactura) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getFactura(codigoFactura);
	}

	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).unirPedidos(codigosPedidos, fechaEntrega);
	}

	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getLineasPedidoDireccion(idDireccion);
	}

	public String unirLineasPedidosDireccion(
			List<String> codigosLineasDireccion, DTM fechaEntrega,
			Long idDireccion) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).unirLineasPedidosDireccion(codigosLineasDireccion, fechaEntrega, idDireccion);
	}

	public char comprobarLoteLeido(Lote lote) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).comprobarLoteLeido(lote);
	}

	public void entregarAlbaran(String codigoAlbaran, String usuario) throws Exception {
		((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).entregarAlbaran(codigoAlbaran, usuario);
	}

	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getDireccionesAlbaran(albaran);
	}

	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).unirAlbaranes(codigosAlbaranes, dtm);
	}

	public Vector<Lote> getLotesProducto(long idProducto) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getLotesProducto(idProducto);
	}
	
	public void actualizarAlbaran(Albaran albaran) throws Exception{
		((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).actualizarAlbaran(albaran);
	}

	public Factura getNuevaFactura() throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getNuevaFactura();
	}

	public void insertarFacturaLibre(Factura factura) throws Exception{
		((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).insertarFacturaLibre(factura);
	}
	
	public boolean updateFactura(Factura f) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).updateFactura(f);
	}

	public void updateEstadoFactura(Factura f) throws Exception {
		((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).updateEstadoFactura(f);
	}
	
	public Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getProductosPedidos(idCliente);
	}
	
	public Vector<Producto> getProductosAlbaran(String idPedido) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getProductosAlbaran(idPedido);
	}
	
	public Vector<Producto> getProductosFactura(String idPedido) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getProductosFactura(idPedido);
	}

	public Vector<Factura> facturasAlbaran(String codigoAlbaran) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).facturasAlbaran(codigoAlbaran);
	}

	public void updateFechaVencimientoFactura(Factura factura) throws Exception{
		((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).updateFechaVencimientoFactura(factura);
	}

	public void dividirFacturaCuotas(Factura factura) throws Exception {
		((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).dividirFacturaCuotas(factura);
	}
	
	public Vector<EstadoPedido> getEstadosFacturas() throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getEstadosFacturas();
	}

	public Vector<PortesTransporte> getPortesTransporte() throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getPortesTransporte();
	}

	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getTemperaturasTransporte();
	}

	public Comercial getLanzaderaPedidos(long idComercial) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).getLanzaderaPedidos(idComercial);
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception{
		return((IRegistroSalidaDataService)(new ServiceLocator()).getService("IRegistroSalidaDataService")).insertaLogLanzadera(lanzaderaAux);
	}
}