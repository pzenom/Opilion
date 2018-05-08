package es.induserco.edifact.presentacion.orders;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.entidades.EstadoPedido;

public class MuestraInformacionEstadoAction extends ActionSupport implements
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
		System.out.println("MuestraInformacionEstadoAction");
		String estado = request.getParameter("estado"), idPedido = request.getParameter("idPedido");
		Vector<EstadoPedido> estadosPedido = new EdifactParserHelper().getInformacionEstado(estado);
		request.getSession().setAttribute("estadosPedido", estadosPedido);
		request.getSession().setAttribute("idPedido", idPedido);
		return SUCCESS;
	}
}