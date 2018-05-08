package es.induserco.edifact.negocio;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.datos.orders.IOrdersDataService;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class EdifactParserDataHelper {
	
	public Boolean orderParse(Order pedido) throws Exception {
		System.out.println("EdifactParserDataHelper: orderParse");
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).addOrder(pedido);
	}

	public Boolean orderExists(Order pedido) throws Exception {
		System.out.println("EdifactParserDataHelper-OrderExists");
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).orderExists(pedido);
	}

	public Vector<Order> orderLista(Order pedido, int filtro) throws Exception {
		System.out.println("EdifactParserDataHelper-OrderLista");
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).orderLista(pedido, filtro);
	}

	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("EdifactParserDataHelper-OrderLista especifico: "+codigo);
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).orderLista(codigo, todasLocalizaciones);
	}

	public long getProximoId() throws Exception{
		System.out.println("EdifactParserDataHelper-get proximo id");
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).getProximoId();
	}

	public Vector<LineaAlbaran> getDireccionesOrder(String order) throws Exception {
		return((IOrdersDataService)(new ServiceLocator()).getService("IOrdersParserDataService")).getDireccionesOrder(order);
	}

	public boolean eliminaOrder(String codigo) throws Exception {
		return((IOrdersDataService)(new ServiceLocator()).
				getService("IOrdersParserDataService")).eliminaOrder(codigo);
	}

	public boolean updateOrder(Order pedido) throws Exception{
		return((IOrdersDataService)(new ServiceLocator()).
				getService("IOrdersParserDataService")).updateOrder(pedido);
	}

	public Vector<Producto> getProdutosPedido(Order order) throws Exception {
		return((IOrdersDataService)(new ServiceLocator()).
				getService("IOrdersParserDataService")).getProdutosPedido(order);
	}

	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception {
		return((IOrdersDataService)(new ServiceLocator()).
				getService("IOrdersParserDataService")).getInformacionEstado(estado);
	}
}