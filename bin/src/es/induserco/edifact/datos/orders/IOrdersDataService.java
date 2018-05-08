package es.induserco.edifact.datos.orders;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;

public interface IOrdersDataService {
	Boolean addOrder(Order pedido) throws Exception;
	Boolean orderExists(Order pedido) throws Exception;
	Vector<Order> orderLista(Order pedido, int filtro) throws Exception;
	Vector<Order> orderLista(String codigo, boolean todasLocalizaciones)throws Exception;
	long getProximoId() throws Exception;
	Vector<LineaAlbaran> getDireccionesOrder(String order) throws Exception;
	boolean eliminaOrder(String codigo) throws Exception;
	boolean updateOrder(Order pedido) throws Exception;
	Vector<Producto> getProdutosPedido(Order order) throws Exception;
	Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception;
}