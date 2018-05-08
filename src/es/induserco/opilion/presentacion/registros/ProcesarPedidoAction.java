package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

/**
 * @author Induserco, andres (28/04/2011)
 * @version 0.3
 */
public class ProcesarPedidoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {
	
	private HttpServletRequest request;
	private Order pedido = new Order();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ProcesarPedidoAction Action...");
		this.request=request;	
	} 

	//@Override
	public Object getModel() {		
		return pedido;
	}
	
	/**
	 * @author andres (28/04/2011)
	 * @since 0.2
	 */
 	public String execute() throws IOException {
		System.out.println("Execute ProcesarPedidoAction");	
 		try{
			String codigo = pedido.getBgmNum();
			System.out.println("Código del pedido: " + codigo);
			boolean acepta = true;
			//Comprobar si hay stock para todos los productos en el pedido
			//1. Obtenemos todas las lineas del pedido
			List<OrdersLin> lineas = new ArrayList<OrdersLin>();
			Vector<Producto> faltaStock = new Vector<Producto>();
			lineas = new GestionRegistroSalidaHelper().getLineasPedido(codigo);
			Iterator itera = lineas.listIterator();
			double pesoAlbaran = 0;
			//Para cada linea, mirar si tenemos stock del producto
			while(itera.hasNext() ) {
				double pesoLinea = 0;
				OrdersLin lin = (OrdersLin) itera.next();
				double cantidadPedida = Double.parseDouble(lin.getQty21Cant());
				long idProducto = lin.getIdProducto();
				int cantidadLinea = Integer.parseInt(lin.getQty21Cant());
				//Comprobar el stock que hay para cada ean
				Producto aux = new Producto();
				aux.setIdProducto(idProducto);
				Vector<Producto> prods = new GestionProductosHelper().getProductos(aux, false);
				if (prods.size() > 0){
					Producto producto = prods.elementAt(0);
					double pesoProducto = producto.getPeso();
					pesoLinea = pesoProducto * cantidadLinea;
					//Reutilizo el campo cantidad para meter la cantidadPedida del producto
					producto.setCantidad(cantidadPedida);
					double stock = producto.getStock();
					if (stock < cantidadPedida){
						faltaStock.add(producto);
						acepta = false;
					}
				} else
					acepta = false;
				lin.setPeso(pesoLinea);
				pesoAlbaran += pesoLinea;
			}
			request.getSession().setAttribute("pesoNetoTotal", pesoAlbaran);
			request.getSession().setAttribute("listaFaltaStock", faltaStock);
			request.getSession().setAttribute("pedido", codigo);
			request.getSession().setAttribute("estadoPedido", pedido.getEstado());
			if (acepta){
				//Ahora hay que actualizar el estado del pedido a A (Aceptado)
				new GestionRegistroSalidaHelper().actualizaEstadoPedido(codigo, 'A');
		 		return SUCCESS;
			}
			else{
				return "faltaStock";
			}
 		} catch (Exception e){
 			e.printStackTrace();
 		}
		return ERROR;
	}
}