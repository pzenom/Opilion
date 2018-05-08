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

public class GestionRegistroSalidaBB implements IGestionRegistroSalidaService{
	
	//@Override
	public Boolean addRegistroSalida(RegistroSalida exit) throws Exception {
		return new RegistroSalidaDataHelper().addRegistroSalida(exit);
	}

	//@Override
	public Boolean updateRegistroSalida(RegistroSalida exitf,RegistroSalida exitu) throws Exception {
		return new RegistroSalidaDataHelper().updateRegistroSalida(exitf,exitu);
	}

	//@Override
	public Boolean deleteRegistroSalida(RegistroSalida exitf,
			RegistroSalida exitd) throws Exception {
		return new RegistroSalidaDataHelper().deleteRegistroSalida(exitf,exitd);
	}

	//@Override
	public Vector getClientes() throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector proveedores = edh.getProveedores();
		return proveedores;
	}

	//@Override
	public Vector getProductos() throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	//@Override
	public Vector getVehiculos() throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector vehiculos = edh.getVehiculos();
		return vehiculos;
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return new RegistroSalidaDataHelper().getFechaRegistro();
	}

	//@Override
	public String getCodigoAlbaran() throws Exception {
		return new RegistroSalidaDataHelper().getCodigoAlbaran();
	}

	//@Override
	public Vector getRegistroSalidas(String lote, String codigoSalida, Date fecha,Long idProducto) throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector salidas = edh.getRegistroSalidas(lote, codigoSalida, fecha, idProducto);
		return salidas;
	}

	//@Override
	public Vector getTipoVehiculos() throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector tipovehiculos = edh.getTipoVehiculos();
		return tipovehiculos;
	}

	//@Override
	public Vector getComerciales() throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector comerciales = edh.getComerciales();
		return comerciales;
	}

	//@Override
	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception {
		return new RegistroSalidaDataHelper().getEncabezadoAlbaran(codigoAlbaran);
	}	

	//@Override
	public Vector getDetalleAlbaran(Albaran albaran) throws Exception{
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector detalle = edh.getDetalleAlbaran(albaran);
		return detalle;		
	}

	//@Override
	public Boolean addAlbaran(Albaran albaran) throws Exception {
		return new RegistroSalidaDataHelper().addAlbaran(albaran);
	}

	//@Override
	public Vector loadRegistroSalida(String codigoSalida) throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector rs = edh.loadRegistroSalida(codigoSalida);
		return rs;	
	}

	//@Override
	public Vector getAlbaran(String codigoAlbaran, Date fecha,Long idCliente) throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector rs = edh.getAlbaran(codigoAlbaran,fecha,idCliente);
		return rs;	
	}	

	//@Override
	public Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Albaran al = edh.getAlbaran(codigoAlbaran, lineaCarrefour);
		return al;	
	}
	
	//@Override	
	public Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception {
		RegistroSalidaDataHelper edh = new RegistroSalidaDataHelper();
		Vector rs = edh.getRSLineaAlbaran(codigoAlbaran,linNum);
		return rs;
	}	

	//@Override	
	public Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception {
		return new RegistroSalidaDataHelper().getRSLineaAlbaranTotal(codigoAlbaran,linNum);
	}	

	//@Override	
	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception {
		return new RegistroSalidaDataHelper().getEncabezadoAlbaranOrden(orden);
	}	

	//@Override
	public Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception{
		return new RegistroSalidaDataHelper().getTotalesAlbaranOrden(codigoAlbaran);
	}	

	//@Override
	public Boolean addFactura(String codigoAlbaran,Factura fact) throws Exception {
		return new RegistroSalidaDataHelper().addFactura(codigoAlbaran,fact);
	}

	//@Override
	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception {
		return new RegistroSalidaDataHelper().updateDetaAlba(mapaPrecUnit,codigoAlbaran);
	}

	//@Override
	public Vector getAlbaranes(Albaran albaran, int filtro) throws Exception {
		return new RegistroSalidaDataHelper().getAlbaranes(albaran,filtro);
	}	

	//@Override
	public RegistroSalida getAlbaranRS(String orden) throws Exception{
		return new RegistroSalidaDataHelper().getAlbaranRS(orden);
	}

	//@Override
	public Vector getDireccionesClientes(String tipo) throws Exception {
		return new RegistroSalidaDataHelper().getDireccionesClientes(tipo);
	}
	
	//@Override
	public Vector getFormasDePago() throws Exception {
		return new RegistroSalidaDataHelper().getFormasDePago();
	}

	//@Override
	public Albaran getTotalesOrden(String bgmNum) throws Exception {
		return new RegistroSalidaDataHelper().getTotalesOrden(bgmNum);
	}

	//@Override
	public String getDireccionesEAN(String idDireccion) throws Exception {
		return new RegistroSalidaDataHelper().getDireccionesEAN(idDireccion);
	}

	//@Override
	public String getInfoProducto(String ean) throws Exception {
		return new RegistroSalidaDataHelper().getInfoProducto(ean);
	}

	//@Override
	public Vector getDireccionesCantidades(Long idOrders, String linNum) throws Exception {
		return new RegistroSalidaDataHelper().getDireccionesCantidades(idOrders, linNum);
	}

	//@Override
	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		return new RegistroSalidaDataHelper().getLineasPedido(codigo);
	}

	//@Override
	public boolean actualizaEstadoPedido(String pedido, char c) throws Exception {
		return new RegistroSalidaDataHelper().actualizaEstadoPedido(pedido, c);
	}

	//@Override
	public Vector<Factura> getFacturas(Factura factura, String fechaInicio,
			String fechaFin, Usuario usuario) throws Exception {
		return new RegistroSalidaDataHelper().getFacturas(factura, fechaInicio, fechaFin, usuario);
	}

	//@Override
	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception {
		return new RegistroSalidaDataHelper().unirPedidos(codigosPedidos, fechaEntrega);
	}

	//@Override
	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion)
			throws Exception {
		return new RegistroSalidaDataHelper().getLineasPedidoDireccion(idDireccion);
	}

	//@Override
	public String unirLineasPedidosDireccion(List<String> codigosLineasDireccion, DTM fechaEntrega,
			Long idDireccion) throws Exception {
		return new RegistroSalidaDataHelper().unirLineasPedidosDireccion(codigosLineasDireccion, fechaEntrega, idDireccion);
	}

	//@Override
	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran)	throws Exception {
		return new RegistroSalidaDataHelper().getDireccionesAlbaran(albaran);
	}

	//@Override
	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm)	throws Exception {
		return new RegistroSalidaDataHelper().unirAlbaranes(codigosAlbaranes, dtm);
	}
	
	//@Override
	public void actualizarAlbaran(Albaran albaran) throws Exception {
		new RegistroSalidaDataHelper().actualizarAlbaran(albaran);
	}

	//@Override
	public Factura getNuevaFactura() throws Exception {
		return new RegistroSalidaDataHelper().getNuevaFactura();
	}

	//@Override
	public void insertarFacturaLibre(Factura factura) throws Exception {
		new RegistroSalidaDataHelper().insertarFacturaLibre(factura);
	}
	
	//@Override
	public boolean updateFactura(Factura f) throws Exception {
		return new RegistroSalidaDataHelper().updateFactura(f);
	}
	
	//@Override
	public void updateEstadoFactura(Factura f) throws Exception {
		new RegistroSalidaDataHelper().updateEstadoFactura(f);
	}
	
	//@Override
	public Vector<Producto> getProductosAlbaran(String idAlbaran) throws Exception {
		return new RegistroSalidaDataHelper().getProductosAlbaran(idAlbaran);
	}
	
	//@Override
	public Vector<Producto> getProductosFactura(String idFactura) throws Exception {
		return new RegistroSalidaDataHelper().getProductosFactura(idFactura);
	}

	//@Override
	public Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception {
		return new RegistroSalidaDataHelper().getProductosPedidos(idCliente);
	}
	
	//@Override
	public Vector<Factura> facturasAlbaran(String codigoAlbaran) throws Exception {
		return new RegistroSalidaDataHelper().facturasAlbaran(codigoAlbaran);
	}

	//@Override
	public void updateFechaVencimientoFactura(Factura factura) throws Exception {
		new RegistroSalidaDataHelper().updateFechaVencimientoFactura(factura);
	}
	
	public void dividirFacturaCuotas(Factura factura) throws Exception{
		new RegistroSalidaDataHelper().dividirFacturaCuotas(factura);
	}

	//@Override
	public Vector<EstadoPedido> getEstadosFacturas() throws Exception {
		return new RegistroSalidaDataHelper().getEstadosFacturas();
	}

	//@Override
	public Vector<PortesTransporte> getPortesTransporte() throws Exception {
		return new RegistroSalidaDataHelper().getPortesTransporte();
	}

	//@Override
	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception {
		return new RegistroSalidaDataHelper().getTemperaturasTransporte();
	}

	public Comercial getLanzaderaPedidos(long idComercial) throws Exception {
		return new RegistroSalidaDataHelper().getLanzaderaPedidos(idComercial);
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception {
		return new RegistroSalidaDataHelper().insertaLogLanzadera(lanzaderaAux);
	}
}