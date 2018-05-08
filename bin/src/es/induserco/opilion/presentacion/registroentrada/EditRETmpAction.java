package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class EditRETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Tmp Update Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));

			entry=(new GestionRegistroEntradaHelper()).getOrdenRegistroTmp((String)request.getAttribute("codigoEntrada"));
			
			/*String fecha=(String)entry.getFechaLlegada().toString();
			System.out.println(fecha);
			StringTokenizer st = new StringTokenizer (fecha,"-");
			fecha = st.nextElement().toString()+'-';
			String dia =st.nextElement().toString();
			fecha += st.nextElement().toString()+'-'+dia;
			System.out.println("Fecha parseada "+fecha);
			request.getSession().setAttribute("fecharegistro",fecha);*/
			
			System.out.println("EDITRETMPACTION FCaducidad:" +(new GestionRegistroEntradaHelper()).getFechaCaducidadTmp((String)request.getAttribute("codigoEntrada")));
			System.out.println("EDITRETMPACTION FRegistro: " +(new GestionRegistroEntradaHelper()).getFechaRegistroTmp((String)request.getAttribute("codigoEntrada")));
			System.out.println("EDITRETMPACTION FLlegada:" +entry.getFecha());

			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistroTmp((String)request.getAttribute("codigoEntrada")));
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidadTmp((String)request.getAttribute("codigoEntrada")));		
			request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listatransportistas",(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());
			request.getSession().setAttribute("listatipoubicacion",(new GestionRegistroEntradaHelper()).getTipoUbicaciones());
			request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
			request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
			request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
			request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
			request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
			request.getSession().setAttribute("listaestadosfabas", (new GestionRegistroEntradaHelper()).getEstadosFabas());
			request.getSession().setAttribute("listaincidencias", (new GestionRegistroEntradaHelper()).getIncidencias());
			request.getSession().setAttribute("tiporegistro", entry.getIdTipoRegistro());
		
			// Se guarda en la request los datos del RE y sus incidencias
			request.getSession().setAttribute("listaregistros",(new GestionRegistroEntradaHelper()).loadRegistroEntradaTmp(entry));
			request.getSession().setAttribute("listaestadostmp", (new GestionRegistroEntradaHelper()).loadEstadoFabasTmp(entry));
			request.getSession().setAttribute("listaincidenciastmp", (new GestionRegistroEntradaHelper()).loadIncidenciasTmp(entry));
		
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);
		}
		
		catch (Exception e) {
			return (ERROR);
		}
	}
}