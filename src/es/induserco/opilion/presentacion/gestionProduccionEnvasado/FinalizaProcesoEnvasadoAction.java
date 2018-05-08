/*************************************************************
 *  VISUALIZACION DE UN PROCESO DE ENVASADO
 * @version 0.1 
 * @author Administrador - Induserco
**************************************************************/	
package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class FinalizaProcesoEnvasadoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private RegistroEnvasado regi = new RegistroEnvasado();
	private String url;
	
	//@Override
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	//@Override
	public Object getModel() {
		System.out.println("GetModel FinalizaProcesoEnvasado");
		return regi;
	}

	public String execute() throws Exception {
		System.out.println("Execute FinalizaProcesoEnvasado");

		//1. Damos valor a orden
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "";
		String orden = "";
		double merma = 0, envasado = regi.getElaborado();
		ArrayList<LineaProducto> envases = new ArrayList<LineaProducto>();
		ArrayList<LineaProducto> materias = new ArrayList<LineaProducto>();
		ArrayList<LineaProducto> EANS13 = new ArrayList<LineaProducto>();
		
		String anulaString = request.getParameter("anula");
		Boolean anula = Boolean.parseBoolean(anulaString);
		
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if(nombreParametro.compareTo("orden") == 0){
				orden = parametros.get(e.getKey())[0];
			}else{
				if(nombreParametro.indexOf("real_env") > -1) {
					String[] frag = nombreParametro.split("_");
					String codigoEntrada = frag[2];
					long ubicacion = Long.parseLong(frag[3]);
					long idPalet = Long.parseLong(frag[4]);
					System.out.println("Hemos encontrado un envase ");
					System.out.println("ENVASE: " + ((String[])parametros.get(e.getKey()))[0]);
					double cantidad = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
					Iterator i = parametros.entrySet().iterator();
					while(i.hasNext()){
						Map.Entry entry = (Map.Entry) i.next();
						String parametro = (String) entry.getKey();
						if(parametro.compareTo("mermas_env_" + codigoEntrada + "_" + ubicacion + "_" + idPalet) == 0){
							System.out.println("Hemos encontrado MERMAS de un envase ");
							System.out.println("ENVASE: " + ((String[])parametros.get(entry.getKey()))[0]);
							merma = Double.parseDouble(((String[])parametros.get(entry.getKey()))[0]);
							break;
						}
					}
					LineaProducto producto = new LineaProducto ();
					producto.setReal(cantidad);
					producto.setEntrada(codigoEntrada);
					producto.setMermas(merma);
					producto.setIdUbicacion(ubicacion);
					producto.setIdPalet(idPalet);
					envases.add(producto);
				}else{
					if(nombreParametro.indexOf("real_mp") > -1) {
						String[] frag = nombreParametro.split("_");
						String codigoEntrada = frag[2];
						long ubicacion = Long.parseLong(frag[3]);
						long idPalet = Long.parseLong(frag[4]);
						System.out.println("Hemos encontrado un ingrediente ");
						System.out.println("INGREDIENTE: " + ((String[])parametros.get(e.getKey()))[0]);
						double cantidad = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
						Iterator i = parametros.entrySet().iterator();
						while(i.hasNext()) {
							Map.Entry entry = (Map.Entry) i.next();
							String parametro = (String) entry.getKey();
							if(parametro.compareTo("mermas_mp_" + codigoEntrada + "_" + ubicacion + "_" + idPalet) == 0){
								System.out.println("Hemos encontrado MERMAS de un ingrediente ");
								System.out.println("INGREDIENTE: " + ((String[])parametros.get(entry.getKey()))[0]);
								merma = Double.parseDouble(((String[])parametros.get(entry.getKey()))[0]);
								break;
							}
						}
						LineaProducto producto = new LineaProducto ();
						producto.setReal(cantidad);
						producto.setEntrada(codigoEntrada);
						producto.setMermas(merma);
						producto.setIdUbicacion(ubicacion);
						producto.setIdPalet(idPalet);
						materias.add(producto);
					}else{
						if(nombreParametro.indexOf("real_prod") > -1) {
							String[] frag = nombreParametro.split("_");
							String codigoEntrada = frag[2];
							long ubicacion = Long.parseLong(frag[3]);
							System.out.println("Hemos encontrado un producto ");
							System.out.println("PRODUCTO: " + ((String[])parametros.get(e.getKey()))[0]);
							double cantidad = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
							Iterator i = parametros.entrySet().iterator();
							while(i.hasNext()) {
								Map.Entry entry = (Map.Entry) i.next();
								String parametro = (String) entry.getKey();
								if(parametro.compareTo("mermas_prod_" + codigoEntrada + "_" + ubicacion) == 0){
									System.out.println("Hemos encontrado MERMAS de un producto ");
									System.out.println("PRODUCTO: " + ((String[])parametros.get(entry.getKey()))[0]);
									merma = Double.parseDouble(((String[])parametros.get(entry.getKey()))[0]);
									break;
								}
							}
							LineaProducto producto = new LineaProducto ();
							producto.setReal(cantidad);
							producto.setEntrada(codigoEntrada);
							producto.setMermas(merma);
							producto.setIdUbicacion(ubicacion);
							EANS13.add(producto);
						}
					}
				}
			}
		}
		request.getSession().setAttribute("orden", orden);
		if (!anula)
			request.getSession().setAttribute("estado", "Finalizado");
		else
			request.getSession().setAttribute("estado", "Anulado");
		//2. Modificaciones en BD
		//Modificar: envasado en tabla gp_envasado
		//Ingredientes usados
		//Mermas de cada ingrediente
		//Envases usados
		//Mermas de cada tipo de envases

		//Calculamos el precio de coste del envasado
		double precioCoste = 0;
		GestionProduccion info = new GestionEnvasadoHelper().getInfoProcesosEnv(orden, "");
		boolean ean13 = true;
		if (info.getTipoEan() == 14)
			ean13 = false;
		if (ean13){//1. Envasando items
			//Necesitamos los costosUnitarios de cada registroentrada para cada envase y cada materiaprima
			Iterator iter = materias.iterator();
			while (iter.hasNext()){
				LineaProducto m = (LineaProducto)iter.next();
				String registroEntrada = m.getEntrada();
				RegistroEntrada reg = (RegistroEntrada) new GestionRegistroEntradaHelper().getRegistroEntrada(registroEntrada);
				//Coste unitario de la materia
				double costeUnitario = 0;
				if (reg != null)
					costeUnitario = reg.getCostoUnitario();
				double cantidad = m.getReal() + m.getMermas();
				double costeMateria = costeUnitario * cantidad;
				precioCoste += costeMateria;
			}
			iter = envases.iterator();
			while (iter.hasNext()){
				LineaProducto e = (LineaProducto)iter.next();
				String registroEntrada = e.getEntrada();
				RegistroEntrada reg = (RegistroEntrada) new GestionRegistroEntradaHelper().getRegistroEntrada(registroEntrada);
				//Coste unitario del envase
				double costeUnitario = reg.getCostoUnitario();
				double cantidad = e.getReal() + e.getMermas();
				double costeEnvase = costeUnitario * cantidad;
				precioCoste += costeEnvase;
			}
		}
		String operario = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		regi.setEans13(EANS13);
		if (EANS13.size() > 0)
			regi.setTipoEan(14);
		else
			regi.setTipoEan(13);
		regi.setEnvases(envases);
		regi.setMateriaPrima(materias);
		regi.setProceso(orden);
		regi.setOperario(operario);
		//regi.setObservaciones(observaciones);
		regi.setElaborado(envasado);
		regi.setPrecioCoste(precioCoste);
		if (regi.getLoteAsignado() == null || regi.getLoteAsignado().compareTo("") == 0)
			regi.setLoteAsignado((String) request.getSession().getAttribute("loteAsingado"));
		boolean finalizado =
			new GestionEnvasadoHelper().finalizaProcesoEnvasado(regi, anula);
		/*boolean finalizado = new GestionEnvasadoHelper().finalizaProcesoEnvasado(orden,
				operario, observaciones, materias, envases, envasado);*/
		//Actualizar el coste del producto, que sera la media de todos los envasados de dicho producto
		new GestionEnvasadoHelper().actualizarPrecioCosteProducto(orden);
		request.getSession().setAttribute("envasado", regi.getElaborado());
		request.getSession().setAttribute("observaciones", regi.getObservaciones());
		request.getSession().setAttribute("envasado", regi.getElaborado());
		request.getSession().setAttribute("idProducto", regi.getIdProducto());
		request.setAttribute("lote", regi.getLoteAsignado());
		request.setAttribute("estado", "F");
		this.request.setAttribute("orden", orden);
		this.request.setAttribute("loteAsignado", regi.getLoteAsignado());
		this.request.setAttribute("estadoI", "F");
		setUrl("VisualizaProcesoEnvasado?orden=" + orden + "&estado=F&lote=" + regi.getLoteAsignado());
		if (finalizado)
			return SUCCESS;
		else
			return ERROR;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}