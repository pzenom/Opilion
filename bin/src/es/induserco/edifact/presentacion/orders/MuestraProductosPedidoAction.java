package es.induserco.edifact.presentacion.orders;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class MuestraProductosPedidoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Order order = new Order();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return order;
	}

	public String execute() throws Exception{
		System.out.println("MuestraProductosPedidoAction");
		String idPedido = request.getParameter("idPedido");
		char primerCaracter = idPedido.charAt(0);
		Vector<Producto> productosPedido = null;
		if (primerCaracter == 'P'){
			order.setBgmNum(idPedido);
			productosPedido = new EdifactParserHelper().getProdutosPedido(order);
		}else{
			if (primerCaracter == 'A'){
				productosPedido = new GestionRegistroSalidaHelper().getProductosAlbaran(idPedido);
			}else{
				if (primerCaracter == 'F'){
					productosPedido = new GestionRegistroSalidaHelper().getProductosFactura(idPedido);
				}
			}
		}
		request.getSession().setAttribute("productosPedido", productosPedido);
		request.getSession().setAttribute("idPedido", idPedido);
		return SUCCESS;
	}
}