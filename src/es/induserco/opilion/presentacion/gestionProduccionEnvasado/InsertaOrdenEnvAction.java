package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

/**
 * Clase InsertaOrdenEnvAction. Inserta una nueva orden de envasado.
 * @author Induserco, Andrés (04/03/2011)
 */
public class InsertaOrdenEnvAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map session;
	private GestionProduccion gpro = new GestionProduccion();
	private RegistroEnvasado envasado = new RegistroEnvasado();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar RE MP Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		envasado.setIdProducto(Long.parseLong(this.request.getParameter("idProd")));
		return envasado;
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}

	public String execute() throws Exception {
		System.out.println("INSERTA ORDEN ENV Action");
		String tipoEan = request.getParameter("tipoEan");
		long idAgrupacion = Long.parseLong(request.getParameter("idAgrupacion"));
		envasado.setIdAgrupacion(idAgrupacion);
		System.out.println("Tipo de EAN seleccionado: " + tipoEan);
		String nombreParametro;
		List<LineaProducto> materiaPrimas = new ArrayList();
		List<LineaProducto> eans13 = new ArrayList();
		List<LineaProducto> envases = new ArrayList();
		//List<LineaProducto> envasesAgrupar = new ArrayList();
		//obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		// paso por todos los parametros
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			LineaProducto producto = new LineaProducto();
			//System.out.println("K: "+e.getKey()+" V: "+((String[])parametros.get(e.getKey()))[0]);
			nombreParametro=(String) e.getKey();
			if (tipoEan.compareTo("13") == 0){
				if(nombreParametro.indexOf("ing") > -1) {
					System.out.println("Hemos encontrado un ingrediente");
					producto.setRegistroEntrada(nombreParametro.substring(nombreParametro.indexOf("ing") + 4));
					producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
					System.out.println("INGREDIENTE: " + ((String[])parametros.get(e.getKey()))[0]);
					materiaPrimas.add(producto);
				}else if(nombreParametro.indexOf("env") > -1) {
					System.out.println("Hemos encontrado un envase");
					String entradaIdAgrupacion = nombreParametro.substring(nombreParametro.indexOf("env") + 4);
					String frag[] = entradaIdAgrupacion.split("_");
					String entrada = frag[0];
					int idAgrupacionAux = Integer.parseInt(frag[1]);
					producto.setIdProducto(idAgrupacionAux);
					producto.setRegistroEntrada(entrada);
					producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
					System.out.println(" ENVASE: " + ((String[])parametros.get(e.getKey()))[0] );
					envases.add(producto);
				}else{
					if(nombreParametro.indexOf("EAN13") > -1) {
						System.out.println("Hemos encontrado un EAN13");
						producto.setLote(nombreParametro.substring(nombreParametro.indexOf("EAN13") + 4).split("_")[1]);
						producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
						System.out.println("EAN13: " + ((String[])parametros.get(e.getKey()))[0]);
						eans13.add(producto);
					}
				}
			}else{
				if (tipoEan.compareTo("14") == 0){
					if(nombreParametro.indexOf("ing") > -1) {
						System.out.println("Hemos encontrado un EAN13");
						producto.setLote(nombreParametro.substring(nombreParametro.indexOf("ing") + 4).split("_")[0]);
						producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
						System.out.println("EAN13: " + ((String[])parametros.get(e.getKey()))[0]);
						eans13.add(producto);
					} else if(nombreParametro.indexOf("env") > -1) {
						System.out.println("Hemos encontrado un envase");
						String entradaIdAgrupacion = nombreParametro.substring(nombreParametro.indexOf("env") + 4);
						String frag[] = entradaIdAgrupacion.split("_");
						String entrada = frag[0];
						producto.setRegistroEntrada(entrada);
						producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
						System.out.println(" ENVASE: " + ((String[])parametros.get(e.getKey()))[0] );
						envases.add(producto);
					}
				}
			}
		}
		//Vamos a comprobar:
		/* Si estamos envasando un EAN13 y agrupandolo, y tenemos el mismo envase en el EAN13 y en el EAN14,
		 *  vamos a tratarlos por igual, asi que buscamos si se repiten registros de entrada de envases y los juntamos */
		System.out.println("COMPROBACION!!");
		List<LineaProducto> envasesFinales = new ArrayList();
		if (tipoEan.compareTo("13") == 0){
			List<LineaProducto> comprobados = new ArrayList();
			for (int i = 0; i < envases.size(); i++){
				LineaProducto envase = envases.get(i);
				String codigoEntrada = envase.getRegistroEntrada();
				boolean yaComprobado = false;
				LineaProducto envaseAux = new LineaProducto();
				for (int h = 0; h < comprobados.size(); h++){
					envaseAux = comprobados.get(h);
					String codigoEntradaAux = envaseAux.getRegistroEntrada();
					if (codigoEntrada.equals(codigoEntradaAux)){
						yaComprobado = true;
						break;
					}
				}
				if (!yaComprobado){
					comprobados.add(envase);
					LineaProducto envaseSuma = new LineaProducto();
					envaseSuma.setTeorica((double)0);
					envaseSuma.setRegistroEntrada(codigoEntrada);
					boolean repetido = false;
					for (int j = 0; j < envases.size(); j++){
						LineaProducto envase2 = envases.get(j);
						String codigoEntrada2 = envase2.getRegistroEntrada();
						if (codigoEntrada.equals(codigoEntrada2)){
							envaseSuma.setTeorica(envaseSuma.getTeorica() + envase2.getTeorica());
							//envases.get(j).setHabilitado("N");//Los sustituimos por envaseSuma
							repetido = true;
						}
					}
					if (repetido){
						//envases.get(i).setHabilitado("N");
						envasesFinales.add(envaseSuma);
					}else{
						envaseSuma.setTeorica(envase.getTeorica());
						envasesFinales.add(envaseSuma);
					}
				}
			}
			envases = null;
			envases = envasesFinales;
		}
		if (tipoEan.compareTo("13") == 0){
			envasado.setMateriaPrima(materiaPrimas);
			envasado.setEnvases(envases);
			envasado.setEans13(eans13);
			envasado.setTipoEan(13);
		}else{
			envasado.setEans13(eans13);
			envasado.setEnvases(envases);
			envasado.setTipoEan(14);
		}
		envasado.setOperario(((Usuario) request.getSession().getAttribute("usuario")).getLogin());
		if (envasado.getObservaciones() == null)
			envasado.setObservaciones((String)request.getParameter("explica"));
		if (envasado.getCantidad() == 0)
			envasado.setCantidad(Integer.parseInt((String) request.getParameter("c")));
		if (envasado.getFecha() != null){
			Date fechaCaducidad = new Date();
			Calendar c = Calendar.getInstance();
			c.set(envasado.getFecha().getYear(), envasado.getFecha().getMonth(),
					envasado.getFecha().getDay());
			c.add(Calendar.MONTH, 18);
			fechaCaducidad = c.getTime();
			//System.out.println(c.getTime());
			System.out.println("Dia de caducidad: " + fechaCaducidad.getDay());
			System.out.println("Mes de caducidad: " + fechaCaducidad.getMonth());
			int dif = fechaCaducidad.getYear() + 1900;
			int anio = 0;
			System.out.println("Año caducidad: " + (anio = (dif + 1900)));
			fechaCaducidad.setYear(anio);
			envasado.setFechaCaducidad(fechaCaducidad);
		}
		envasado.setEanProducto(request.getParameter("productoSeleccionado"));
		//insertar en la BD como proceso pendiente
		if((new GestionEnvasadoHelper()).addOrdenEnvasado(envasado))
			System.out.println("Orden de envasado creada");
		request.getSession().setAttribute("envasadoPendientes",
				(new GestionEnvasadoHelper()).getProcesosPendientes(gpro, "E"));
		request.getSession().setAttribute("cboproductospresentacion",
				(new GestionEnvasadoHelper()).getPresentacionProductos(true));
		request.getSession().setAttribute("listaregistroingredientes",
				null);
		request.getSession().setAttribute("listaproductos",
				(new GestionProductosHelper()).getProductos(null, false));
		return SUCCESS;
	}
}