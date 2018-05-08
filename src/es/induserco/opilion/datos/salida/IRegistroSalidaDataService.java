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
import es.induserco.opilion.data.entidades.*;

public interface IRegistroSalidaDataService {

	Vector getClientes() throws Exception;
	Vector getProductos() throws Exception;
	Vector getVehiculos() throws Exception;
	String getFechaRegistro() throws Exception;
	String getCodigoAlbaran() throws Exception;	
	Vector getTipoVehiculos() throws Exception;
	Vector getComerciales() throws Exception;
	Boolean addRegistroSalida(RegistroSalida exit) throws Exception;
	Boolean updateRegistroSalida(RegistroSalida exitf,RegistroSalida exitu) throws Exception;
	Boolean deleteRegistroSalida(RegistroSalida exitf,RegistroSalida exitd) throws Exception;
	Vector getRegistroSalidas(String lote, String codigoSalida, Date fecha,Long idProducto) throws Exception;
	Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception;
	Vector getDetalleAlbaran(Albaran albaran) throws Exception;
	Boolean addAlbaran(Albaran albaran) throws Exception;
	Vector loadRegistroSalida(String codigoSalida) throws Exception;	
	Albaran getAlbaran(String codigoAlbaran, boolean lineaCarrefour) throws Exception;
	Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran) throws Exception;
	//Albarán de Orden
	Vector getRSLineaAlbaran(String codigoAlbaran, Long linNum) throws Exception;
	Vector getRSLineaAlbaranTotal(String codigoAlbaran, Long linNum) throws Exception;
	Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception;
	Albaran getTotalesAlbaranOrden(String codigoAlbaran) throws Exception;
	//Consulta de albaranes
	Vector getAlbaranes(Albaran albaran, int filtro)throws Exception;
	//Facturación
	boolean addFactura(String codigoAlbaran, Factura fact) throws Exception;
	Boolean updateEstadoAlbaran(String codigoAlbaran) throws Exception;
	//Detalle de RS en un albaran
	RegistroSalida getAlbaranRS(String orden) throws Exception;
	Vector getDireccionesClientes(String tipo) throws Exception;
	Vector getFormasDePago() throws Exception;
	Albaran getTotalesOrden(String codigoOrden) throws Exception;
	String getInfoProducto(String EAN) throws Exception;
	String getDireccionesEAN(String idDireccion) throws Exception;
	Vector getDireccionesCantidades(Long idOrders, String linNum) throws Exception;
	List<OrdersLin> getLineasPedido(String codigo) throws Exception;
	boolean actualizaEstadoPedido(String pedido, char c) throws Exception;
	Vector<Factura> getFacturas(Factura factura, String fechaInicio, String fechaFin, Usuario usuario) throws Exception;
	Factura getFactura(String codigo) throws Exception;
	String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega) throws Exception;
	Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion) throws Exception;
	String unirLineasPedidosDireccion(List<String> codigosLineasDireccion,
			DTM fechaEntrega, Long idDireccion) throws Exception;
	char comprobarLoteLeido(Lote lote) throws Exception;
	void entregarAlbaran(String codigoAlbaran, String usuario) throws Exception;
	Vector<Direccion> getDireccionesAlbaran(Albaran albaran) throws Exception;
	Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm) throws Exception;
	Vector<Lote> getLotesProducto(long idProducto) throws Exception;
	void actualizarAlbaran(Albaran albaran) throws Exception;
	Factura getNuevaFactura() throws Exception;
	void insertarFacturaLibre(Factura factura) throws Exception;
	boolean updateFactura(Factura fact) throws Exception;
	void updateEstadoFactura(Factura factura) throws Exception;
	Vector<OrdersLin> getProductosPedidos(long idCliente) throws Exception;
	Vector<Producto> getProductosAlbaran(String idPedido) throws Exception;
	Vector<Producto> getProductosFactura(String idPedido) throws Exception;
	Vector<Factura> facturasAlbaran(String codigoAlbaran) throws Exception;
	void updateFechaVencimientoFactura(Factura factura) throws Exception;
	void dividirFacturaCuotas(Factura factura) throws Exception;
	Vector<EstadoPedido> getEstadosFacturas() throws Exception;
	Vector<Albaran> getAlbaran(String codigoAlbaran, Date fecha, Long idCliente) throws Exception;
	Vector<PortesTransporte> getPortesTransporte() throws Exception;
	Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception;
	Comercial getLanzaderaPedidos(long idComercial) throws Exception;
	Lanzadera insertaLogLanzadera(Lanzadera lanzaderaAux) throws Exception;
}