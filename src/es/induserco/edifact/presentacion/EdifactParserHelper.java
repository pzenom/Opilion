package es.induserco.edifact.presentacion;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.negocio.orders.IOrdersParserService;
import es.induserco.edifact.negocio.orders.IOrdersVisualizaService;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class EdifactParserHelper {
	
	public Vector<Order> orderParserService(String fichero) throws Exception {
		System.out.println("OrderParserHelper");
		return ((IOrdersParserService)(new ServiceLocator()).getService("IOrdersParserService")).orderParse(fichero);
	}

	public Vector<Order> orderVisualizaService(Order pedido, int filtro) throws Exception {
		System.out.println("EdifactPArserHelper: OrderVisualizaService");
		return ((IOrdersVisualizaService)(new ServiceLocator()).
				getService("IOrdersVisualizaService")).orderLista(pedido, filtro);
	}

	public Vector<Order> orderVisualizaService(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("EdifactPArserHelper: OrderVisualizaService "+codigo);
		return ((IOrdersVisualizaService)(new ServiceLocator()).getService("IOrdersVisualizaService")).orderLista(codigo, todasLocalizaciones);
	}

	public Vector<LineaAlbaran> getDireccionesOrder(String codigo) throws Exception {
		return ((IOrdersVisualizaService)(new ServiceLocator()).getService("IOrdersVisualizaService")).getDireccionesOrder(codigo);
	}

	public boolean eliminaOrder(String codigo)throws Exception{
		return ((IOrdersVisualizaService)
				(new ServiceLocator()).getService("IOrdersVisualizaService")).eliminaOrder(codigo);
	}

	public Vector<Producto> getProdutosPedido(Order order) throws Exception{
		return ((IOrdersVisualizaService)
				(new ServiceLocator()).getService("IOrdersVisualizaService")).getProdutosPedido(order);
	}

	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception{
		return ((IOrdersVisualizaService)
				(new ServiceLocator()).getService("IOrdersVisualizaService")).getInformacionEstado(estado);
	}
}