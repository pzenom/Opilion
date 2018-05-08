package es.induserco.opilion.presentacion.registroentrada;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class UpdateBultoREAction extends ActionSupport
	implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object> {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Bulto bulto = new Bulto();
	//private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Update Bultos Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		//return entry;
		return null;
	}

	public String execute() throws Exception {		
		try {
			String unico = request.getParameter("unico");
			if (unico == null || unico.compareTo("true") != 0){
				Map <String, String[]> parametros = request.getParameterMap();
				String nombreParametro = "";
				Iterator iterator = parametros.entrySet().iterator();
				long idPalet = 0;
				int numBulto = 0;
				double neto, bruto;
				Vector<Bulto> bultos = new Vector<Bulto>();
				boolean rompe = false;
				while(iterator.hasNext()) {
					Map.Entry e = (Map.Entry) iterator.next();
					nombreParametro = (String) e.getKey();
					if((nombreParametro.indexOf("numBulto") == 0)){
						Bulto bulto = new Bulto();
						idPalet = Long.parseLong(nombreParametro.split("numBulto")[1]);
						numBulto = Integer.parseInt(parametros.get(e.getKey())[0]);
						bulto.setIdPalet(idPalet);
						bulto.setNumBulto((int) idPalet);
						bulto.setNumBultosPalet(numBulto);
						Iterator iterator2 = parametros.entrySet().iterator();
						while(iterator2.hasNext()) {
							Map.Entry entry = (Map.Entry) iterator2.next();
							nombreParametro = (String) entry.getKey();
							if((nombreParametro.compareTo("pBruto" + idPalet) == 0)){
								bruto = Double.parseDouble(parametros.get(entry.getKey())[0]);
								bulto.setPBruto(bruto);
								Iterator iterator3 = parametros.entrySet().iterator();
								while(iterator3.hasNext()) {
									Map.Entry entry3 = (Map.Entry) iterator3.next();
									nombreParametro = (String) entry3.getKey();
									if((nombreParametro.compareTo("pNeto" + idPalet) == 0)){
										neto = Double.parseDouble(parametros.get(entry3.getKey())[0]);
										bulto.setPNeto(neto);
										bultos.add(bulto);
										rompe = true;
										break;
									}
								}
							}
							if (rompe){
								rompe = false;
								break;
							}
						}
					}
				}
				String codigoEntrada = (String) request.getSession().getAttribute("codigoEntrada");
				bulto.setCodigoEntrada(codigoEntrada);
				for (int i = 0; i < bultos.size(); i++){
					((Bulto)bultos.get(i)).setCodigoEntrada(codigoEntrada);
					new GestionRegistroEntradaHelper().updateBultoRE(bultos.get(i));
				}
			}else {
				String primero = request.getParameter("primero");
				request.getSession().setAttribute("primero", primero);
				System.out.println("UpdateBultoREAction execute");
				bulto.setCodigoEntrada((String)(request.getParameter("idEntrada")));
				//bulto.setNumBulto(Integer.parseInt((String)request.getParameter("nombre")));
				bulto.setNumBulto(Integer.parseInt(request.getParameter("numBulto")));
				//bulto.setPeso(Double.valueOf(request.getParameter("peso")).doubleValue());
				bulto.setPBruto(Double.valueOf(request.getParameter("pBruto")).doubleValue());
				bulto.setPNeto(Double.valueOf(request.getParameter("pNeto")).doubleValue());
				bulto.setNumBultosPalet(Integer.parseInt(request.getParameter("numBultosPalet")));
				if (new GestionRegistroEntradaHelper().updateBultoRE(bulto)) {
					//System.out.println("TRUE");
				}
			}
			//como vamos a cargar la misma página hay que volver a cargar los datos						
			RegistroEntrada entry = new RegistroEntrada();
			entry.setCodigoEntrada(bulto.getCodigoEntrada());
			//obtienes el registro en cuestión
			request.getSession().setAttribute("listaregistros",(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));
			//miramos los bultos creados
			request.getSession().setAttribute("listabultos",(new GestionRegistroEntradaHelper()).getBultosRegistroEntrada(entry));
			request.getSession().setAttribute("updateBulto", true);
			request.getSession().setAttribute("netoAjustar",
					((Bulto)(new GestionRegistroEntradaHelper()).getBultosRegistroEntrada(entry).get(0)).getPNeto());
			request.getSession().setAttribute("brutoAjustar",
					((Bulto)(new GestionRegistroEntradaHelper()).getBultosRegistroEntrada(entry).get(0)).getPBruto());
			request.getSession().setAttribute("modificados", true);
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}