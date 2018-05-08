package es.induserco.opilion.presentacion;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.x12.DTM;
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
import es.induserco.opilion.negocio.gestionregistrosalida.IGestionRegistroSalidaService;

public class GestionRegistroSalidaHelper{
	
	public String getFechaRegistro() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getFechaRegistro();
	}

	public String getCodigoAlbaran() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getCodigoAlbaran();
	}	

	public Boolean addRegistroSalida(RegistroSalida exit) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).addRegistroSalida(exit);
	}

	public Boolean updateRegistroSalida(RegistroSalida exitf,RegistroSalida exitu) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).updateRegistroSalida(exitf,exitu);
	}

	public Boolean deleteRegistroSalida(RegistroSalida exitf,RegistroSalida exitd) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).deleteRegistroSalida(exitf,exitd);
	}

	public Vector getClientes() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getClientes();
	}

	public Vector getProductos() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getProductos();
	}

	public Vector getVehiculos() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getVehiculos();
	}

	public Vector getTipoVehiculos() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getTipoVehiculos();
	}

	public Vector getRegistroSalidas(String lote,String codigoEntrada, Date fecha,Long idProducto) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getRegistroSalidas(lote,codigoEntrada,fecha,idProducto);
	}

	public Vector getComerciales() throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getComerciales();
	}

	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getEncabezadoAlbaran(codigoAlbaran);
	}	
	
	public Vector<RegistroSalida> getDetalleAlbaran(Albaran albaran) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getDetalleAlbaran(albaran);
	}	

	public Boolean addAlbaran(Albaran albaran) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).addAlbaran(albaran);
	}	

	public Vector loadRegistroSalida(String codigoSalida) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).loadRegistroSalida(codigoSalida);		
	}

	public Vector getAlbaran(String codigoAlbaran, Date fecha,Long idCliente) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getAlbaran(codigoAlbaran,fecha,idCliente);		
	}
	
	public Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getRSLineaAlbaran(codigoAlbaran,linNum);		
	}	

	public Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getRSLineaAlbaranTotal(codigoAlbaran,linNum);		
	}	

	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getEncabezadoAlbaranOrden(orden);		
	}	

	public Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getTotalesAlbaranOrden(codigoAlbaran);		
	}	

	public Vector getAlbaranes(Albaran albaran, int filtro) throws Exception {
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getAlbaranes(albaran, filtro);		
	}	

	public Boolean addFactura(String codigoAlbaran,Factura fact) throws Exception{
		return ((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).addFactura(codigoAlbaran,fact);		
	}

	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).updateDetaAlba(mapaPrecUnit,codigoAlbaran);
	}

	public Vector<Direccion> getDireccionesClientes(String tipo) throws Exception{
		return (Vector<Direccion>)((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getDireccionesClientes(tipo);
	}

	public Object getFormasDePago() throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getFormasDePago();
	}

	public Albaran getTotalesOrden(String bgmNum) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getTotalesOrden(bgmNum);
	}


	public String getInfoProducto(String EAN) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getInfoProducto(EAN);
	}

	public String getDireccionesEAN(String idDireccion) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getDireccionesEAN(idDireccion);
	}

	public Vector getDireccionesCantidades(Long idOrders, String linNum) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getDireccionesCantidades(idOrders, linNum);
	}

	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getLineasPedido(codigo);
	}

	public boolean actualizaEstadoPedido(String pedido, char c) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).actualizaEstadoPedido(pedido, c);
	}

	public Vector<Factura> getFacturas(Factura factura, String fechaInicio, String fechaFin, Usuario usuario) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getFacturas(factura, fechaInicio, fechaFin, usuario);
	}

	public Factura getFactura(String codigoFactura) throws Exception {
		return((IRegistroSalidaDataService)(new ServiceLocator()).
				getService("IRegistroSalidaDataService")).getFactura(codigoFactura);
	}
	
	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).unirPedidos(codigosPedidos, fechaEntrega);
	}

	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion)throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getLineasPedidoDireccion(idDireccion);
	}

	public String unirLineasPedidosDireccion(
			List<String> codigosLineasDireccion, DTM fechaEntrega, Long idDireccion) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).unirLineasPedidosDireccion(codigosLineasDireccion, fechaEntrega, idDireccion);
	}

	public Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getAlbaran(codigoAlbaran, lineaCarrefour);
	}

	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getDireccionesAlbaran(albaran);
	}

	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).unirAlbaranes(codigosAlbaranes, dtm);
	}

	public void actualizarAlbaran(Albaran albaran) throws Exception{
		((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).actualizarAlbaran(albaran);
	}

	public Factura getNuevaFactura()throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getNuevaFactura();
	}

	public void insertarFacturaLibre(Factura factura) throws Exception {
		((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).insertarFacturaLibre(factura);
	}

	public boolean updateFactura(Factura fact) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).updateFactura(fact);
	}

	public void updateEstadoFactura(Factura factura) throws Exception{
		((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).updateEstadoFactura(factura);
	}

	public Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception {
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getProductosPedidos(idCliente);
	}

	public Vector<Producto> getProductosAlbaran(String idPedido) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getProductosAlbaran(idPedido);
	}
	
	public Vector<Producto> getProductosFactura(String idPedido) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).getProductosFactura(idPedido);
	}

	public Vector<Factura> facturasAlbaran(String codigoAlbaran) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).facturasAlbaran(codigoAlbaran);
	}

	public void updateFechaVencimientoFactura(Factura factura) throws Exception{
		((IGestionRegistroSalidaService)(new ServiceLocator()).
		getService("IGestionRegistroSalidaService")).updateFechaVencimientoFactura(factura);
	}
	
	public void dividirFacturaCuotas(Factura factura) throws Exception{
		((IGestionRegistroSalidaService)(new ServiceLocator()).
				getService("IGestionRegistroSalidaService")).dividirFacturaCuotas(factura);
	}

	public Vector<EstadoPedido> getEstadosFacturas() throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getEstadosFacturas();
	}

	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getTemperaturasTransporte();
	}

	public Vector<PortesTransporte> getPortesTransporte() throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getPortesTransporte();
	}

	public Comercial getLanzaderaPedidos(long idComercial) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).getLanzaderaPedidos(idComercial);
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception{
		return((IGestionRegistroSalidaService)(new ServiceLocator()).getService("IGestionRegistroSalidaService")).insertaLogLanzadera(lanzaderaAux);
	}
}