package es.induserco.opilion.presentacion.cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class RegistroClienteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Entidad entry = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Cliente Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro", (new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listaformaspago", (new GestionEntidadesHelper()).getFormasPago());
		request.getSession().setAttribute("listaprovincias", (new GestionEntidadesHelper()).getProvincias());
		request.getSession().setAttribute("listadiasentrega", (new GestionEntidadesHelper()).getListaDiasEntrega());
		request.getSession().setAttribute("listarutas", (new GestionEntidadesHelper()).getListaRutas());
		request.getSession().setAttribute("listasectores", (new GestionEntidadesHelper()).getListaSectores());
		request.getSession().setAttribute("listatipos",(new GestionEntidadesHelper()).getListaTiposProveedores());
		request.getSession().setAttribute("listabancos", (new GestionEntidadesHelper()).getListaBancos());			
		//Levantamos el evento success para especificar que todo esta bien
		SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es_ES"));
		Date fechaDate = new Date();
		String fecha = formateador.format(fechaDate);
		request.getSession().setAttribute("hoy", fecha);
		
		return (SUCCESS);
	}
}