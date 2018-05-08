package es.induserco.edifact.negocio.orders;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;

public interface IOrdersVisualizaService {
	public Vector orderExists (Order pedido) throws Exception;
	public Vector<Order> orderLista (Order pedido, int filtro) throws Exception;
	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones)throws Exception;
	public Vector<LineaAlbaran> getDireccionesOrder(String codigo) throws Exception;
	public boolean eliminaOrder(String codigo) throws Exception;
	public Vector<Producto> getProdutosPedido(Order order) throws Exception;
	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception;
}