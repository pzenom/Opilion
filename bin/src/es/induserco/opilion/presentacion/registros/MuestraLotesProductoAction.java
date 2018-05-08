package es.induserco.opilion.presentacion.registros;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;

public class MuestraLotesProductoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto p = new Producto();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return p;
	}

	public String execute() throws Exception{
		System.out.println("MuestraLotesProducto");
		Vector<Lote> lotesProducto = new RegistroSalidaDataHelper().getLotesProducto(p.getIdProducto());
		request.getSession().setAttribute("lotesProducto", lotesProducto);
		return SUCCESS;
	}
}