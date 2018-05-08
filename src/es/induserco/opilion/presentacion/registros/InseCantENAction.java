package es.induserco.opilion.presentacion.registros;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class InseCantENAction.
 */
public class InseCantENAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private RegistroOrden entry = new RegistroOrden();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar Cantidades de Envasados Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {
		return entry;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {

		RegistroSalida salida = new RegistroSalida();
		Albaran albaran = new Albaran();
		String tipo="S";
		salida.setOrdenEnvasado((String)request.getParameter("orden"));
		
		//Recupera el num de linea
		String linNum=(String)request.getSession().getAttribute("linNum");

		if(linNum!=null){
			salida.setNumLinea(Long.parseLong(linNum));
		}
		
		albaran.setCodigoAlbaran((String)request.getSession().getAttribute("albaran"));		
		if((String)request.getSession().getAttribute("tipo") !=null)
		tipo="O";
		
		salida.setAlbaran(albaran);
		
		//Recupera las cantidades del RS
		salida.setPesoNeto(Double.parseDouble(request.getParameter("pesoNeto")));		
		salida.setNumeroBultos(Long.parseLong(request.getParameter("numeroBultos")));
		salida.setCantidadUnitaria(Double.parseDouble(request.getParameter("cantidadProducto")));
		salida.setPrecioUnitario(Double.parseDouble(request.getParameter("precioUnitario")));

		System.out.println("en insecant el numero de linea es "+linNum);	
		System.out.println("orden es :"+salida.getOrdenEnvasado());
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		salida.setIdOperario(usuario.getLogin());
		
		//Inserta el RS
		new GestionRegistroSalidaHelper().addRegistroSalida(salida); 

		//Detalle del albarán recarga nuevamente
		
		/*
		Vector albaranDetalle = new Vector();
		albaranDetalle=new GestionRegistroSalidaHelper().getDetalleAlbaran(albaran);		
		request.getSession().setAttribute("listars",albaranDetalle);		
		*/
		
		//recupera tipo de albaran O si es de orden 
		if (tipo.equals("S"))
			return SUCCESS;
		else
			return "redirect";
			
	}

}
