package es.induserco.opilion.datos.salida;

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
import es.induserco.opilion.negocio.gestionregistrosalida.GestionRegistroSalidaBB;

public class RegistroSalidaDataService implements IRegistroSalidaDataService {

	//@Override
	public Boolean addRegistroSalida(RegistroSalida exit) throws Exception {
		return (new RegistroSalidaDAO()).addRegistroSalida(exit);
	}

	//@Override
	public Boolean updateRegistroSalida(RegistroSalida exitf,RegistroSalida exitu) throws Exception {
		return (new RegistroSalidaDAO()).updateRegistroSalida(exitf,exitu);
	}

	//@Override
	public Boolean deleteRegistroSalida(RegistroSalida exitf,
			RegistroSalida exitd) throws Exception {
		return (new RegistroSalidaDAO()).deleteRegistroSalida(exitf,exitd);
	}

	//@Override
	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception{
		return (new RegistroSalidaDAO()).getEncabezadoAlbaran(codigoAlbaran);
	}

	//@Override
	public Vector getClientes() throws Exception {
		return (new RegistroSalidaDAO()).getClientes();
	}

	//@Override
	public Vector getProductos() throws Exception {
		return (new RegistroSalidaDAO()).getProductos();
	}

	//@Override
	public Vector getVehiculos() throws Exception {
		return (new RegistroSalidaDAO()).getVehiculos();
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return (new RegistroSalidaDAO()).getFechaRegistro();
	}

	//@Override
	public String getCodigoAlbaran() throws Exception {
		return (new RegistroSalidaDAO()).getCodigoAlbaran();
	}	

	//@Override
	public Vector getRegistroSalidas(String lote, String codigoSalida, Date fecha,Long idProducto) throws Exception {
		return (new RegistroSalidaDAO()).getRegistroSalidas(lote, codigoSalida,fecha,idProducto);
	}

	//@Override
	public Vector getTipoVehiculos() throws Exception {
		return (new RegistroSalidaDAO()).getTipoVehiculos();
	}

	//@Override
	public Vector getComerciales() throws Exception {
		return (new RegistroSalidaDAO()).getComerciales();
	}

	//@Override
	public Vector getDetalleAlbaran(Albaran albaran) throws Exception {
		return (new RegistroSalidaDAO()).getDetalleAlbaran(albaran);
	}

	//@Override
	public Boolean addAlbaran(Albaran albaran) throws Exception {
		return (new RegistroSalidaDAO()).addAlbaran(albaran);
	}	

	//@Override
	public Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception {
		return (new RegistroSalidaDAO()).getRSLineaAlbaran(codigoAlbaran,linNum);
	}	

	//@Override
	public Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception{
		return (new RegistroSalidaDAO()).getRSLineaAlbaranTotal(codigoAlbaran,linNum);
	}	

	//@Override
	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception{
			return (new RegistroSalidaDAO()).getEncabezadoAlbaranOrden(orden);
	}	

	//@Override
	public Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception{
			return (new RegistroSalidaDAO()).getTotalesAlbaranOrden(codigoAlbaran);
	}	

	//@Override
	public Vector loadRegistroSalida(String codigoSalida) throws Exception {
		return (new RegistroSalidaDAO()).loadRegistroSalida(codigoSalida);
	}

	//@Override
	public Vector getAlbaran(String codigoAlbaran, Date fecha,Long idCliente) throws Exception {
		return (new RegistroSalidaDAO()).getAlbaran(codigoAlbaran,fecha,idCliente);
	}
	
	//@Override
	public boolean addFactura(String codigoAlbaran,Factura fact) throws Exception {
		return (new RegistroSalidaDAO()).addFactura(codigoAlbaran,fact);
	}	

	//@Override
	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception{
		return (new RegistroSalidaDAO()).updateDetaAlba(mapaPrecUnit,codigoAlbaran);
	}	

	//@Override	
	public Vector getAlbaranes(Albaran albaran, int filtro)throws Exception{
		return (new RegistroSalidaDAO()).getAlbaranes(albaran, filtro);
	}

	//@Override
	public Boolean updateEstadoAlbaran(String codigoAlbaran) throws Exception {
		return null;
	}
	
	//@Override	
	public RegistroSalida getAlbaranRS(String orden) throws Exception {
		return (new RegistroSalidaDAO()).getAlbaranRS(orden);
	}

	//@Override
	public Vector getDireccionesClientes(String tipo) throws Exception {
		return (new RegistroSalidaDAO()).getDireccionesClientes(tipo);
	}	
	
	//@Override
	public Vector getFormasDePago() throws Exception {
		return (new RegistroSalidaDAO()).getFormasDePago();
	}

	//@Override
	public Albaran getTotalesOrden(String codigoOrden) throws Exception {
		return (new RegistroSalidaDAO()).getTotalesOrden(codigoOrden);
	}

	//@Override
	public String getDireccionesEAN(String idDireccion) throws Exception {
		return (new RegistroSalidaDAO()).getDireccionesEAN(idDireccion);
	}

	//@Override
	public String getInfoProducto(String EAN) throws Exception {
		return (new RegistroSalidaDAO()).getInfoProducto(EAN);
	}

	//@Override
	public Vector getDireccionesCantidades(Long idOrders, String linNum)
			throws Exception {
		return (new RegistroSalidaDAO()).getDireccionesCantidades(idOrders, linNum);
	}

	//@Override
	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		return (new RegistroSalidaDAO()).getLineasPedido(codigo);
	}

	//@Override
	public boolean actualizaEstadoPedido(String pedido, char c)
			throws Exception {
		return (new RegistroSalidaDAO()).actualizaEstadoPedido(pedido, c);
	}

	//@Override
	public Vector<Factura> getFacturas(Factura factura, String fechaInicio,
			String fechaFin, Usuario usuario) throws Exception {
		return (new RegistroSalidaDAO()).getFacturas(factura, fechaInicio, fechaFin, usuario);
	}

	//@Override
	public Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception {
		return (new RegistroSalidaDAO()).getAlbaran(codigoAlbaran, lineaCarrefour);
	}

	//@Override
	public Factura getFactura(String codigo) throws Exception {
		return (new RegistroSalidaDAO()).getFactura(codigo);
	}

	//@Override
	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception {
		return new RegistroSalidaDAO().unirPedidos(codigosPedidos, fechaEntrega);
	}

	//@Override
	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion)
			throws Exception {
		return new RegistroSalidaDAO().getLineasPedidoDireccion(idDireccion);
	}

	//@Override
	public String unirLineasPedidosDireccion(
			List<String> codigosLineasDireccion, DTM fechaEntrega,
			Long idDireccion) throws Exception {
		return new RegistroSalidaDAO().unirLineasPedidosDireccion(codigosLineasDireccion, fechaEntrega, idDireccion);
	}

	//@Override
	public char comprobarLoteLeido(Lote lote) throws Exception {
		return new RegistroSalidaDAO().comprobarLoteLeido(lote);
	}

	//@Override
	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran)
			throws Exception {
		return new RegistroSalidaDAO().getDireccionesAlbaran(albaran);
	}

	//@Override
	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm)
			throws Exception {
		return new RegistroSalidaDAO().unirAlbaranes(codigosAlbaranes, dtm);
	}

	//@Override
	public Vector<Lote> getLotesProducto(long idProducto) throws Exception {
		return new RegistroSalidaDAO().getLotesProducto(idProducto);
	}
	
	//@Override
	public void actualizarAlbaran(Albaran albaran) throws Exception {
		new RegistroSalidaDAO().actualizarAlbaran(albaran);
	}

	//@Override
	public Factura getNuevaFactura() throws Exception {
		return new RegistroSalidaDAO().getNuevaFactura();
	}

	//@Override
	public void insertarFacturaLibre(Factura factura) throws Exception {
		new RegistroSalidaDAO().insertarFacturaLibre(factura);
	}
	
	//@Override
	public boolean updateFactura(Factura f) throws Exception {
		return new RegistroSalidaDAO().updateFactura(f);
	}
	
	//@Override
	public void updateEstadoFactura(Factura f) throws Exception {
		new RegistroSalidaDAO().updateEstadoFactura(f);
	}
	
	//@Override
	public Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception {
		return new RegistroSalidaDAO().getProductosPedidos(idCliente);
	}
	
	//@Override
	public Vector<Producto> getProductosAlbaran(String idPedido) throws Exception {
		return new RegistroSalidaDAO().getProductosAlbaran(idPedido);
	}
	
	//@Override
	public Vector<Producto> getProductosFactura(String idPedido) throws Exception {
		return new RegistroSalidaDAO().getProductosFactura(idPedido);
	}

	//@Override
	public void entregarAlbaran(String codigoAlbaran, String usuario) throws Exception {
		new RegistroSalidaDAO().entregarAlbaran(codigoAlbaran, usuario);
	}

	//@Override
	public Vector<Factura> facturasAlbaran(String codigoAlbaran)
			throws Exception {
		return new RegistroSalidaDAO().facturasAlbaran(codigoAlbaran);
	}

	public void updateFechaVencimientoFactura(Factura factura) throws Exception {
		new RegistroSalidaDAO().updateFechaVencimientoFactura(factura);
	}
	
	public void dividirFacturaCuotas(Factura factura) throws Exception{
		new RegistroSalidaDAO().dividirFacturaCuotas(factura);
	}
	
	public Vector<EstadoPedido> getEstadosFacturas() throws Exception {
		return new RegistroSalidaDAO().getEstadosFacturas();
	}

	public Vector<PortesTransporte> getPortesTransporte() throws Exception {
		return new RegistroSalidaDAO().getPortesTransporte();
	}

	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception {
		return new RegistroSalidaDAO().getTemperaturasTransporte();
	}

	public Comercial getLanzaderaPedidos(long idComercial) throws Exception {
		return new RegistroSalidaDAO().getLanzaderaPedidos(idComercial);
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception {
		return new RegistroSalidaDAO().insertaLogLanzadera(lanzaderaAux);
	}
}