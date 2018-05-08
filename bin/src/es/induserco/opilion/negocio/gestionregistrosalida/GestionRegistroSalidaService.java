package es.induserco.opilion.negocio.gestionregistrosalida;

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
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;

public class GestionRegistroSalidaService implements IGestionRegistroSalidaService{

	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroSalidaBB().getFechaRegistro();
	}

	//@Override
	public String getCodigoAlbaran() throws Exception {
		return new GestionRegistroSalidaBB().getCodigoAlbaran();
	}

	//@Override
	public Vector getClientes() throws Exception {
		return new GestionRegistroSalidaBB().getClientes();
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroSalidaBB().getProductos();
	}

	//@Override
	public Vector getVehiculos() throws Exception {
		return new GestionRegistroSalidaBB().getVehiculos();
	}	

	//@Override
	public Vector getTipoVehiculos() throws Exception {
		return new GestionRegistroSalidaBB().getTipoVehiculos();
	}

	//@Override
	public Vector getComerciales() throws Exception {
		return new GestionRegistroSalidaBB().getComerciales();
	}

	//@Override
	public Boolean addRegistroSalida(RegistroSalida exit) throws Exception {
		return new GestionRegistroSalidaBB().addRegistroSalida(exit);
	}

	//@Override
	public Boolean updateRegistroSalida(RegistroSalida exitf,RegistroSalida exitu) throws Exception {
		return new GestionRegistroSalidaBB().updateRegistroSalida(exitf,exitu);
	}

	//@Override
	public Boolean deleteRegistroSalida(RegistroSalida exitf,
			RegistroSalida exitd) throws Exception {
		return new GestionRegistroSalidaBB().deleteRegistroSalida(exitf,exitd);
	}

	//@Override
	public Vector getRegistroSalidas(String lote, String codigoSalida, Date fecha,Long idProducto) throws Exception {
		return new GestionRegistroSalidaBB().getRegistroSalidas(lote, codigoSalida, fecha, idProducto);
	}

	//@Override
	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception{
		return new GestionRegistroSalidaBB().getEncabezadoAlbaran(codigoAlbaran);
	}

	//@Override
	public Vector getDetalleAlbaran(Albaran albaran) throws Exception{
		return new GestionRegistroSalidaBB().getDetalleAlbaran(albaran);
	}

	//@Override
	public Boolean addAlbaran(Albaran albaran) throws Exception{
		return new GestionRegistroSalidaBB().addAlbaran(albaran);
	}

	//@Override
	public Vector loadRegistroSalida(String codigoSalida) throws Exception {
		return (new GestionRegistroSalidaBB()).loadRegistroSalida(codigoSalida);
	}

	//@Override
	public Vector getAlbaran(String codigoAlbaran, Date fecha,Long idCliente) throws Exception {
		return (new GestionRegistroSalidaBB()).getAlbaran(codigoAlbaran,fecha,idCliente);
	}

	//@Override	
	public Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception {
		return new GestionRegistroSalidaBB().getRSLineaAlbaran(codigoAlbaran,linNum);
	}

	//@Override	
	public Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception {
		return new GestionRegistroSalidaBB().getRSLineaAlbaranTotal(codigoAlbaran,linNum);
	}

	//@Override	
	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception {
		return new GestionRegistroSalidaBB().getEncabezadoAlbaranOrden(orden);
	}

	//@Override
	public Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception{
		return new GestionRegistroSalidaBB().getTotalesAlbaranOrden(codigoAlbaran);
	}	

	//@Override
	public Vector getAlbaranes(Albaran albaran, int filtro) throws Exception {
		return new GestionRegistroSalidaBB().getAlbaranes(albaran, filtro);
	}		

	//@Override
	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception {
		return new GestionRegistroSalidaBB().updateDetaAlba(mapaPrecUnit,codigoAlbaran);
	}

	//@Override
	public Boolean addFactura(String codigoAlbaran,Factura fact) throws Exception{
		return new GestionRegistroSalidaBB().addFactura(codigoAlbaran,fact);
	}

	//@Override
	public RegistroSalida getAlbaranRS(String orden) throws Exception{
		return new GestionRegistroSalidaBB().getAlbaranRS(orden);
	}

	//@Override
	public Vector getDireccionesClientes(String tipo) throws Exception {
		return new GestionRegistroSalidaBB().getDireccionesClientes(tipo);
	}

	//@Override
	public Object getFormasDePago() throws Exception {
		return new GestionRegistroSalidaBB().getFormasDePago();
	}

	//@Override
	public Albaran getTotalesOrden(String bgmNum) throws Exception {
		return new GestionRegistroSalidaBB().getTotalesOrden(bgmNum);
	}

	//@Override
	public String getDireccionesEAN(String idDireccion) throws Exception {
		return new GestionRegistroSalidaBB().getDireccionesEAN(idDireccion);
	}

	//@Override
	public String getInfoProducto(String ean) throws Exception {
		return new GestionRegistroSalidaBB().getInfoProducto(ean);
	}

	//@Override
	public Vector getDireccionesCantidades(Long idOrders, String linNum)
			throws Exception {
		return new GestionRegistroSalidaBB().getDireccionesCantidades(idOrders, linNum);
	}

	//@Override
	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		return new GestionRegistroSalidaBB().getLineasPedido(codigo);
	}

	//@Override
	public boolean actualizaEstadoPedido(String pedido, char c) throws Exception {
		return new GestionRegistroSalidaBB().actualizaEstadoPedido(pedido, c);
	}

	//@Override
	public Vector<Factura> getFacturas(Factura factura, String fechaInicio,	String fechaFin, Usuario usuario) throws Exception {
		return new GestionRegistroSalidaBB().getFacturas(factura, fechaInicio, fechaFin, usuario);
	}

	//@Override
	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception {
		return new GestionRegistroSalidaBB().unirPedidos(codigosPedidos, fechaEntrega);
	}

	//@Override
	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion)
			throws Exception {
		return new GestionRegistroSalidaBB().getLineasPedidoDireccion(idDireccion);
	}

	//@Override
	public String unirLineasPedidosDireccion(
			List<String> codigosLineasDireccion, DTM fechaEntrega,
			Long idDireccion) throws Exception {
		return new GestionRegistroSalidaBB().unirLineasPedidosDireccion(codigosLineasDireccion, fechaEntrega, idDireccion);
	}

	//@Override
	public Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception {
		return new GestionRegistroSalidaBB().getAlbaran(codigoAlbaran, lineaCarrefour);
	}

	//@Override
	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran)
			throws Exception {
		return new GestionRegistroSalidaBB().getDireccionesAlbaran(albaran);
	}

	//@Override
	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm)
			throws Exception {
		return new GestionRegistroSalidaBB().unirAlbaranes(codigosAlbaranes, dtm);
	}
	
	//@Override
	public void actualizarAlbaran(Albaran albaran) throws Exception {
		new RegistroSalidaDataHelper().actualizarAlbaran(albaran);
	}

	//@Override
	public Factura getNuevaFactura() throws Exception {
		return new GestionRegistroSalidaBB().getNuevaFactura();
	}

	//@Override
	public void insertarFacturaLibre(Factura factura) throws Exception {
		new RegistroSalidaDataHelper().insertarFacturaLibre(factura);
	}
	
	//@Override
	public boolean updateFactura(Factura f) throws Exception {
		return new GestionRegistroSalidaBB().updateFactura(f);
	}
	
	//@Override
	public void updateEstadoFactura(Factura f) throws Exception {
		new GestionRegistroSalidaBB().updateEstadoFactura(f);
	}
	
	//@Override
	public Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception {
		return new GestionRegistroSalidaBB().getProductosPedidos(idCliente);
	}
	
	//@Override
	public Vector<Producto> getProductosFactura(String idFactura) throws Exception {
		return new GestionRegistroSalidaBB().getProductosFactura(idFactura);
	}
	
	//@Override
	public Vector<Producto> getProductosAlbaran(String idAlbaran) throws Exception {
		return new GestionRegistroSalidaBB().getProductosAlbaran(idAlbaran);
	}
	
	//@Override
	public Vector<Factura> facturasAlbaran(String codigoAlbaran) throws Exception {
		return new GestionRegistroSalidaBB().facturasAlbaran(codigoAlbaran);
	}

	//@Override
	public void updateFechaVencimientoFactura(Factura factura) throws Exception {
		new GestionRegistroSalidaBB().updateFechaVencimientoFactura(factura);
	}
	
	//@Override
	public void dividirFacturaCuotas(Factura factura) throws Exception{
		new GestionRegistroSalidaBB().dividirFacturaCuotas(factura);
	}
	
	//@Override
	public Vector<EstadoPedido> getEstadosFacturas() throws Exception {
		return new GestionRegistroSalidaBB().getEstadosFacturas();
	}

	//@Override
	public Vector<PortesTransporte> getPortesTransporte() throws Exception {
		return new GestionRegistroSalidaBB().getPortesTransporte();
	}

	//@Override
	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception {
		return new GestionRegistroSalidaBB().getTemperaturasTransporte();
	}

	public Comercial getLanzaderaPedidos(long idComercial) throws Exception {
		return new GestionRegistroSalidaBB().getLanzaderaPedidos(idComercial);
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception {
		return new GestionRegistroSalidaBB().insertaLogLanzadera(lanzaderaAux);
	}
}