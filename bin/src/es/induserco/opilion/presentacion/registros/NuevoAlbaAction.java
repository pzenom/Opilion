package es.induserco.opilion.presentacion.registros;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class NuevoAlbaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
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
		//Colocamos las listas de datos en la request.
		
		Albaran albaran= new Albaran();
		albaran.setCodigoAlbaran(new GestionRegistroSalidaHelper().getCodigoAlbaran());
		albaran.setTipoAlbaran("S");
		request.getSession().setAttribute("albaran", albaran.getCodigoAlbaran());
		request.getSession().setAttribute("fecharegistro",(new GestionRegistroSalidaHelper()).getFechaRegistro());
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		request.getSession().setAttribute("listaproductos",(new GestionRegistroSalidaHelper()).getProductos());
		request.getSession().setAttribute("listatipovehiculos",(new GestionRegistroSalidaHelper()).getTipoVehiculos());
		request.getSession().setAttribute("listacomerciales",(new GestionRegistroSalidaHelper()).getComerciales());
		
		request.getSession().setAttribute("listaregienvasados",(new GestionRegistroEntradaHelper().getREENProducto((long) 0)));

		return (SUCCESS);
	}
}