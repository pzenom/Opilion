package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class EtiquetaBultoREAction extends ActionSupport
	implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Bulto> {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Bulto bulto = new Bulto();	
	private RegistroEntrada re = new RegistroEntrada();
	
	public RegistroEntrada getRe() {
		return re;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Bulto getModel(){
		System.out.println("execute de Etiqueta Registro Entrada!");
		return bulto;
	}

	public String execute() throws Exception {
		bulto.setCodigoEntrada((String)(request.getParameter("idEntrada")));
		System.out.println("ID: " + bulto.getCodigoEntrada());
		bulto.setPBruto(Double.parseDouble(request.getParameter("pBruto")));
		bulto.setPNeto(Double.parseDouble(request.getParameter("pNeto")));
		//Colocamos la lista de entradades en la request.
		re = new GestionRegistroEntradaHelper().getEtiquetaRE(bulto.getCodigoEntrada());
		if (re.getDescCategoria() == null){
			re.setOrigen("");
		}
		else{
			if(re.getDescCategoria().equals("Extra")){
				re.setOrigen("E");
			} else if(re.getDescCategoria().equals("Extra B")){
				re.setOrigen("B");
			} else if(re.getDescCategoria().equals("Primera")){
				re.setOrigen("P");
			} else if(re.getDescCategoria().equals("Segunda")){
				re.setOrigen("S");
			}
		}
		re.setHumedad(bulto.getPBruto());
		re.setCostoUnitario(bulto.getPNeto());
		re.setAlbaran(String.valueOf(bulto.getNumBulto()));
		//generaci�n del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		"/reportes/entradas/EtiqREB2JR.jrxml",
            		"/reportes/entradas/EtiqREB2JR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}
}