package es.induserco.opilion.presentacion.registros;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.negocio.EdifactParserDataHelper;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class NuevoPedidoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Albaran Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return albaran;
	}

	public String execute() throws Exception {
		long pedido = new EdifactParserDataHelper().getProximoId();
		String cadenaPedido = "P" + pedido;
		request.getSession().setAttribute("pedido", cadenaPedido);
		request.getSession().setAttribute("fecharegistro",
				(new GestionRegistroSalidaHelper()).getFechaRegistro());//.replace('/', '-'));
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		Vector<Direccion> v = (Vector<Direccion>)(new GestionRegistroSalidaHelper()).getDireccionesClientes("E");
		request.getSession().setAttribute("listadirecciones", v);
		//request.getSession().setAttribute("listaFormasPago", (new GestionEntidadesHelper()).getFormasPagoEntidad(new Entidad()));
		request.getSession().setAttribute("listaproductos",(new GestionProductosHelper()).getProductos(null, true));
		request.getSession().setAttribute("listaCategorizaciones", new GestionProductosHelper().getGrupoProducto());
		return (SUCCESS);
	}
}