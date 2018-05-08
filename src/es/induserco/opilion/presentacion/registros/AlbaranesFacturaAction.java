package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class AlbaranesFacturaAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware {

	private static final long serialVersionUID = 422840826723506717L;
	private HttpServletRequest request;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("AlbaranesFacturaAction Action...");
		this.request=request;	
	} 

 	public String execute() throws IOException {
		try {
			//1. Leemos los identificadores de los pedidos que vamos a unir en el albarán
			Map <String, String[]> parametros = request.getParameterMap();
			String nombreParametro = "";
			List<String> codigosAlbaranes = new ArrayList<String>();
			Iterator iterator = parametros.entrySet().iterator();
			String cod = "";
			DTM dtm = new DTM();
			while(iterator.hasNext()) {
				cod = "";
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if (nombreParametro.indexOf("codigoAlbaran_") > -1){
					cod = parametros.get(e.getKey())[0];
					codigosAlbaranes.add(cod);
				}else{
					if (nombreParametro.indexOf("fechaEntrega") > -1){
						//1.1 Leemos la nueva fecha de entrega para el albarán
						String fechaEntrega = parametros.get(e.getKey())[0];
						if (fechaEntrega != null && !fechaEntrega.equals("") && !fechaEntrega.equals("undefined")){
							String frag[] = fechaEntrega.split("/");
							String fechaFormato = frag[2] + "-" + frag[1] + "-" + frag[0];
							dtm.setDtmFech(fechaFormato);
						}
					}
				}
			}
			
			//2. Creamos el nuevo albaran oculto
			Albaran albaran = new GestionRegistroSalidaHelper().unirAlbaranes(codigosAlbaranes, dtm);
			request.getSession().setAttribute("albaran", albaran);
			Entidad e = new Entidad();
			e.setIdUsuario(albaran.getIdCliente());
			request.getSession().setAttribute("listaDireccionesFacturacion", new GestionEntidadesHelper().getDireccionesEntidad(e, "F"));
			Entidad entidad = new GestionEntidadesHelper().loadEntidad(e);
			request.getSession().setAttribute("formasDePago", entidad.getFormasPagoEntidad());
			request.getSession().setAttribute("telefonos", entidad.getTelefonos());
			Factura nuevaFactura = new GestionRegistroSalidaHelper().getNuevaFactura();
			request.getSession().setAttribute("factura", nuevaFactura);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;	 
	 }
}