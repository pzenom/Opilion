package es.induserco.opilion.presentacion.cliente;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class GestionSalidasClientesAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidad = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }

	//@Override
	public Object getModel() { return entidad; }

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		System.out.println("GestionSalidasClientesAction...");
		request.getSession().setAttribute("idCliente", entidad.getIdUsuario());
		entidad = new GestionEntidadesHelper().loadEntidad(entidad);
		request.getSession().setAttribute("cliente", entidad);
		Albaran albaran = new Albaran();
		albaran.setCliente(entidad);
		//cargamos los albaranes. los null que le paso son las fechas del filtro de albaranes
		request.getSession().setAttribute("listaAlbaranes",
				(new GestionRegistroSalidaHelper()).getAlbaranes(albaran, 0));//0: todos
		/* request.getSession().setAttribute("listaPedidos",
				(new GestionEntidadesHelper()).getPedidosCliente(entidad.getIdUsuario())); */
		request.getSession().setAttribute("direcciones", entidad.getDirecciones());
		/* Cargar datos para estad�sticas */
		Vector<OrdersLin> productosPedidos = new GestionRegistroSalidaHelper().getProductosPedidos(entidad.getIdUsuario());
		request.getSession().setAttribute("productosPedidos", productosPedidos);
		Order order = new Order();
		order.setNadBy(entidad.getIdUsuario().toString());
		request.getSession().setAttribute("listaPedidos", new EdifactParserHelper().orderVisualizaService(order, 1));//1: ultimos 20
		Factura f = new Factura();
		f.setIdCliente(entidad.getIdUsuario());
		request.getSession().setAttribute("listaFacturas", new GestionRegistroSalidaHelper().getFacturas(f, null, null, us));
		return (SUCCESS);
	}
}