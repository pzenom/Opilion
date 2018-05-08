package es.induserco.edifact.negocio.orders;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;

public class OrdersVisualizaService implements IOrdersVisualizaService {

	//@Override
	public Vector orderExists(Order pedido) throws Exception {
		System.out.println("OrdersVisualizaService: orderExists");
		return new OrdersVisualizaBB().orderExists(pedido);
	}

	//@Override
	public Vector<Order> orderLista(Order pedido, int filtro) throws Exception {
		System.out.println("OrdersVisualizaService: orderLista");
		return new OrdersVisualizaBB().orderLista(pedido, filtro);
	}

	//@Override
	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("OrdersVisualizaService: orderLista");
		return new OrdersVisualizaBB().orderLista(codigo, todasLocalizaciones);
	}

	//@Override
	public Vector<LineaAlbaran> getDireccionesOrder(String pedido) throws Exception {
		return new OrdersVisualizaBB().getDireccionesOrder(pedido);
	}

	//@Override
	public boolean eliminaOrder(String codigo) throws Exception {
		return new OrdersVisualizaBB().eliminaOrder(codigo);
	}

	//@Override
	public Vector<Producto> getProdutosPedido(Order order) throws Exception {
		return new OrdersVisualizaBB().getProdutosPedido(order);
	}

	//@Override
	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception {
		return new OrdersVisualizaBB().getInformacionEstado(estado);
	}
}