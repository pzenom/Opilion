package es.induserco.edifact.datos.orders;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;

public class OrdersDataService implements IOrdersDataService {

	//@Override
	public Boolean addOrder(Order pedido) throws Exception {
		System.out.println("OrdersDataService: addPedido");
		return (new OrdersDAO()).addOrder(pedido);
	}

	//@Override
	public Boolean orderExists(Order pedido) throws Exception {
		System.out.println("OrdersDataService: orderExists");
		return (new OrdersDAO()).orderExists(pedido);
	}

	//@Override
	public Vector<Order> orderLista(Order pedido, int filtro) throws Exception {
		System.out.println("OrdersDataService: orderLista");
		return (new OrdersDAO()).orderLista(pedido, filtro);
	}

	//@Override
	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("OrdersDataService: orderLista");
		return (new OrdersDAO()).orderLista(codigo, todasLocalizaciones);
	}

	//@Override
	public long getProximoId() throws Exception {
		System.out.println("OrdersDataService: getProximoId");
		return (new OrdersDAO()).getProximoId();
	}
	
	public Vector<LineaAlbaran> getDireccionesOrder(String pedido) throws Exception{
		System.out.println("OrdersDataService: getProximoId");
		return (new OrdersDAO()).getDireccionesOrder(pedido);
	}

	//@Override
	public boolean eliminaOrder(String codigo) throws Exception {
		return (new OrdersDAO()).eliminaOrder(codigo);
	}

	//@Override
	public boolean updateOrder(Order pedido) throws Exception {
		return (new OrdersDAO()).updateOrder(pedido);
	}

	//@Override
	public Vector<Producto> getProdutosPedido(Order order) throws Exception {
		return (new OrdersDAO()).getProdutosPedido(order);
	}

	//@Override
	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception {
		return (new OrdersDAO()).getInformacionEstado(estado);
	}
}