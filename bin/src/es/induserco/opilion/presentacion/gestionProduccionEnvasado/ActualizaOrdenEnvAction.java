package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ActualizaOrdenEnvAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>, SessionAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map session;
	private RegistroEnvasado envasado = new RegistroEnvasado();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar RE MP Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		//System.out.println(envasado.setc);
		return envasado;
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
	
	public String execute() throws Exception {
		System.out.println("ACTUALIZA ORDEN ENV Action");
		
		//obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		ArrayList envases = new ArrayList();
		ArrayList materias = new ArrayList();
		ArrayList envasesIniciales = new ArrayList();
		ArrayList materiasIniciales = new ArrayList();
		
		String orden = request.getParameter("orden");
		String codigoEntrada = "";
		//Paso por todos los parametros
		String nombreParametro = "";
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro=(String) e.getKey();
			if(nombreParametro.indexOf("ing") > -1) {
				if (nombreParametro.compareTo("cuantosIngre") != 0){
					String[] frag = nombreParametro.split("_");
					codigoEntrada = frag[2];
					System.out.println("Hemos encontrado un ingrediente ");
					System.out.println(" INGREDIENTE: "+((String[])parametros.get(e.getKey()))[0]);
					double cantidad = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
					
					LineaProducto producto = new LineaProducto ();
					producto.setTeorica(cantidad);
					producto.setEntrada(codigoEntrada);
					
					materias.add(producto);
				}
			} else if(nombreParametro.indexOf("env") > -1) {
				if (nombreParametro.compareTo("cuantosenva") != 0){
					System.out.println("Hemos encontrado un envase");
					String[] frag = nombreParametro.split("_");
					codigoEntrada = frag[2];
					System.out.println(" ENVASE: "+((String[])parametros.get(e.getKey()))[0] );
					double cantidad = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
					

					LineaProducto producto = new LineaProducto ();
					producto.setTeorica(cantidad);
					producto.setEntrada(codigoEntrada);
					
					envases.add(producto);
				}
			} else if(nombreParametro.indexOf("oculto") > -1) {
				LineaProducto p = new LineaProducto();
				String[] frag = nombreParametro.split("_");
				p.setEntrada(frag[2]);
				p.setTeorica(Double.parseDouble(parametros.get(e.getKey())[0]));
				if(nombreParametro.indexOf("MP") > -1) {
					materiasIniciales.add(p);
				} else if(nombreParametro.indexOf("EN") > -1) {
					envasesIniciales.add(p);
				}
			} else {
				System.out.println("No se trata de un ingrediente ni de un envase");
			}
		}
		
		//Crear Vector/array con los envases y las materias primas que hay que eliminar del proceso
		//Mirar, para cada uno de los iniciales, si lo encontramos en envases y materias
		ArrayList materiasLimpiar = new ArrayList();
		for (int i = 0; i < materiasIniciales.size(); i++){
			LineaProducto l = (LineaProducto) materiasIniciales.get(i);
			boolean flag = false;
			for (int j = 0; j < materias.size(); j++)
			{
				LineaProducto lp = (LineaProducto) materias.get(j);
				if (l.getEntrada().compareTo(lp.getEntrada()) == 0)
				{
					flag = true;
					break;
				}		
			}
			if (!flag)
				materiasLimpiar.add(l);
		}
		
		ArrayList envasesLimpiar = new ArrayList();
		for (int i = 0; i < envasesIniciales.size(); i++){
			LineaProducto l = (LineaProducto) envasesIniciales.get(i);
			boolean flag = false;
			for (int j = 0; j < envases.size(); j++)
			{
				LineaProducto lp = (LineaProducto) envases.get(j);
				if (l.getEntrada().compareTo(lp.getEntrada()) == 0)
				{
					flag = true;
					break;
				}
			}
			if (!flag)
				envasesLimpiar.add(l);
		}
		
		Map attributes = ActionContext.getContext().getSession();
		Object o = attributes.get("usuario");
		Usuario usuario = (Usuario)o;
		String operario = usuario.getLogin();
        
		//En gp_envasado, actualizar: cantidad
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String fecha = request.getParameter("fechaRegistro");
		String observaciones = "Actualizaci�n del proceso de envasado";
		//Damos formato a la fecha
		String[] split = fecha.split("/");
		//0: dia, 1: mes, 2: anio
		fecha = "" + split[2] + "-" + split[1] + "-" + split[0];
		System.out.println("Fecha formateada: " + fecha);
		if ((new GestionEnvasadoHelper()).actualizaProcesoEnvasado(cantidad, orden, fecha,
				operario, observaciones, materias, envases, materiasLimpiar, envasesLimpiar)){
			System.out.println("Proceso de envasado actualizado");
			return SUCCESS;
		} else
			return ERROR;
	}
}