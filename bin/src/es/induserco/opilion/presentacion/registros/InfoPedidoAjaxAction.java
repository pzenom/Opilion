package es.induserco.opilion.presentacion.registros;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;

public class InfoPedidoAjaxAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Order order = new Order();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("getModel InfoPedidoAjaxAction");
		return order;
	}

	public String execute() throws Exception {
		System.out.println("Execute InfoPedidoAjaxAction");
		Vector<Order> pedidos = new EdifactParserHelper().orderVisualizaService(order.getBgmNum(), true);
		request.getSession().setAttribute("pedidos", pedidos);
		return SUCCESS;
	}
}