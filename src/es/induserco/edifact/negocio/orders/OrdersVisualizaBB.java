package es.induserco.edifact.negocio.orders;

import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.negocio.EdifactParserDataHelper;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;

public class OrdersVisualizaBB implements IOrdersVisualizaService {

	public Vector orderExists(Order order) throws Exception {
		Vector resultado = new Vector();	
		if(new EdifactParserDataHelper().orderExists(order)) {
			System.out.println("*******ERROR********");
			String mensaje="[ERROR] El fichero ya ha sido subido previamente, o hay un fichero en conflicto";
			resultado.add(mensaje);
			return resultado;
		}
		return null; 
	}

	public Vector<Order> orderLista(Order pedido, int filtro) throws Exception {
		System.out.println("OrdersVisualizaBB: orderLista");
		return new EdifactParserDataHelper().orderLista(pedido, filtro);
	}

	//@Override
	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("OrdersVisualizaBB: orderLista " + codigo);
		return new EdifactParserDataHelper().orderLista(codigo, todasLocalizaciones);
	}

	//@Override
	public Vector<LineaAlbaran> getDireccionesOrder(String pedido) throws Exception {
		return new EdifactParserDataHelper().getDireccionesOrder(pedido);
	}

	//@Override
	public boolean eliminaOrder(String codigo) throws Exception {
		return new EdifactParserDataHelper().eliminaOrder(codigo);
	}

	//@Override
	public Vector<Producto> getProdutosPedido(Order order) throws Exception {
		return new EdifactParserDataHelper().getProdutosPedido(order);
	}

	//@Override
	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception {
		return new EdifactParserDataHelper().getInformacionEstado(estado);
	}
}