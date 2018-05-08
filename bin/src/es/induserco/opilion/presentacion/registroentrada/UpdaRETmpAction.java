package es.induserco.opilion.presentacion.registroentrada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class UpdaRETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada regEntradaFind = new RegistroEntrada();
	private RegistroEntrada regEntradaActualiza = new RegistroEntrada();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar Registro Entrada Tmp Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return regEntradaActualiza;
	}

	public String execute() throws Exception {
		try {
			System.out.println("codigo entrada a enviar al update "+request.getSession().getAttribute("codigoEntrada"));
			regEntradaFind.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));
			
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			regEntradaFind.setIdOperario(us.getLogin());
			regEntradaActualiza.setCodigoOrden((String)request.getSession().getAttribute("codigoOrden"));	
			regEntradaActualiza.setLote(request.getParameter("lote"));
			regEntradaActualiza.setIdProveedor(Long.valueOf(request.getParameter("idProv")));
			regEntradaActualiza.setAlbaran(request.getParameter("alb"));
			regEntradaActualiza.setOrigen(request.getParameter("origen"));
			regEntradaActualiza.setCantidad(Double.valueOf(request.getParameter("cant")).doubleValue());
			regEntradaActualiza.setCostoUnitario(Double.valueOf(request.getParameter("cUni")).doubleValue());
			System.out.println("Costo Unitario: " + regEntradaActualiza.getCostoUnitario());
			regEntradaActualiza.setCostoTotal(Double.valueOf(request.getParameter("cTotal")).doubleValue());
			//regEntradaActualiza.setIdTipoRegistro(request.getParameter("tipoReg"));
		
			System.out.println("la fecha de llegada es "+request.getParameter("fLlegada"));
			// Formatear las fechas
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = new Date();
			try {
				fecha = formateador.parse(request.getParameter("fLlegada"));
				regEntradaActualiza.setFechaLlegada(fecha.toString());
				String fechaCaducidad = request.getParameter("fCaduc");
				regEntradaActualiza.setFechaCaducidad(fechaCaducidad);
			}
			catch (Exception e) {
			   System.out.println("Error en las fechas");
			}
				
			regEntradaActualiza.setIdProducto(Long.valueOf(request.getParameter("idPro")));
			regEntradaActualiza.setIdEnvase(Long.valueOf(request.getParameter("idEnv")));		
			regEntradaActualiza.setIdCategoriaEntrada(Long.valueOf(request.getParameter("idCatEntr")));		
			regEntradaActualiza.setIdCategoria(Long.valueOf(request.getParameter("idCat")));
			regEntradaActualiza.setIdCosecha(Long.valueOf(request.getParameter("idCosecha")));		
			regEntradaActualiza.setIdFormatoEntrega(Long.valueOf(request.getParameter("idFormEntr")));
			regEntradaActualiza.setNumeroBultos(Long.valueOf(request.getParameter("numBultos")));
			regEntradaActualiza.setNumeroPallets(Long.valueOf(request.getParameter("numPallets")));
			regEntradaActualiza.setTemperatura(Double.valueOf(request.getParameter("temp")).doubleValue());
			regEntradaActualiza.setHumedad(Double.valueOf(request.getParameter("hum")).doubleValue());
			regEntradaActualiza.setIdTipoUbicacion(Long.valueOf(request.getParameter("idTipUbic")));			
			regEntradaActualiza.setNotasIncidencias(request.getParameter("notasInc"));

			// Listas con los estados e incidencias
			String est = request.getParameter("estados");
			List listaEstados = obtieneLista(est);
			System.out.println("Estados: " + listaEstados);
			
			String inc = request.getParameter("inci");
			List listaIncidencias = obtieneLista(inc);
			System.out.println("Estados: " + listaIncidencias);
						
			new GestionRegistroEntradaHelper().
				updateRegistroEntradaTmp(regEntradaFind, regEntradaActualiza, listaIncidencias, listaEstados);
	
			//request.getSession().setAttribute("listaregistrosupd",(new GestionRegistroEntradaHelper()).loadRegistroEntradaTmp(regEntradaFind));
			//Carga los RE asociados a ese n�m de Orden
			Vector <RegistroEntrada>listareorden = new GestionRegistroEntradaHelper().getRegistrosEntradaOrdenTmp((String)request.getSession().getAttribute("codigoorden"));
			request.getSession().setAttribute("listareorden", listareorden);
			return SUCCESS;	
		}
	
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
	
	// Funci�n que, dado un String, lo convierte en List
	private List obtieneLista (String l){
		List lista = new ArrayList();
		String elto;
		StringTokenizer tokenizer= new StringTokenizer(l, ",");
		while (tokenizer.hasMoreTokens()) {
			elto = tokenizer.nextToken();
			lista.add(elto);
		}
		return lista;
	}
}