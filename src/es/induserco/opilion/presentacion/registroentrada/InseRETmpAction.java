package es.induserco.opilion.presentacion.registroentrada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class InseRETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	@SuppressWarnings("unused")
	private Map<?, ?> session;
	private RegistroEntrada entry = new RegistroEntrada();
	private String url;
	public String getUrl() { return url; }

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Accediendo al Registro Entrada Temporal Action...");
		this.request = request;
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
		try {
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			entry.setIdOperario(us.getLogin());
			entry.setCodigoOrden((String)request.getSession().getAttribute("codigoOrden"));	
			entry.setLote(request.getParameter("lote"));
			entry.setCantidad(Double.valueOf(request.getParameter("cant")).doubleValue());
			entry.setCostoUnitario(Double.valueOf(request.getParameter("cUni")).doubleValue());
			System.out.println("Costo Unitario: " + entry.getCostoUnitario());
			entry.setCostoTotal(Double.valueOf(request.getParameter("cTotal")).doubleValue());
			if (request.getParameter("tipoReg").compareTo("Materia Prima") == 0 ||
					request.getParameter("tipoReg").compareTo("M") == 0){
				entry.setIdTipoRegistro("M");
			}else
				if (request.getParameter("tipoReg").compareTo("Envases") == 0 ||
						request.getParameter("tipoReg").compareTo("E") == 0){
					entry.setIdTipoRegistro("E");
				}
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = new Date();
			try {
				fecha = formateador.parse(request.getParameter("fLlegada"));
				entry.setFechaLlegada(fecha.toString());
				String fechaCaducidad = request.getParameter("fCaduc");
				entry.setFechaCaducidad(fechaCaducidad);
			}
			catch (Exception e) {
			   System.out.println("Error en las fechas");
			}
			entry.setIdProducto(Long.valueOf(request.getParameter("idPro")));
			entry.setIdEnvase(Long.valueOf(request.getParameter("idEnv")));		
			entry.setIdCategoriaEntrada(Long.valueOf(request.getParameter("idCatEntr")));		
			entry.setIdCategoria(Long.valueOf(request.getParameter("idCat")));
			entry.setIdCosecha(Long.valueOf(request.getParameter("idCosecha")));		
			entry.setIdFormatoEntrega(Long.valueOf(request.getParameter("idFormEntr")));
			entry.setNumeroBultos(Long.valueOf(request.getParameter("numBultos")));
			entry.setNumeroPallets(Long.valueOf(request.getParameter("numPallets")));
			entry.setTemperatura(Double.valueOf(request.getParameter("temp")).doubleValue());
			entry.setHumedad(Double.valueOf(request.getParameter("hum")).doubleValue());
			//entry.setIdTipoUbicacion(Long.valueOf(request.getParameter("idTipUbic")));			
			entry.setNotasIncidencias(request.getParameter("notasInc"));
			// Listas con los estados e incidencias
			String est = request.getParameter("estados");
			List<String> listaEstados = obtieneLista(est);
			System.out.println("Estados: " + listaEstados);
			String inc = request.getParameter("inci");
			List<String> listaIncidencias = obtieneLista(inc);
			System.out.println("Incidencias: " + listaIncidencias);
			new GestionRegistroEntradaHelper().addRegistroEntradaTmp(entry,listaIncidencias,listaEstados);
			url = "PreviaRegistroOETmp.action";
			request.getSession().setAttribute("codigoEntrada",entry.getCodigoEntrada());
			return "redirect";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	// Función que, dado un String, lo convierte en List
	private List<String> obtieneLista (String l){
		List<String> lista = new ArrayList<String>();
		String elto;
		StringTokenizer tokenizer = new StringTokenizer(l, ",");
		while (tokenizer.hasMoreTokens()) {
			elto = tokenizer.nextToken();
			lista.add(elto);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}