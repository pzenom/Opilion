package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionesEspecialesHelper;

public class EtiquetaTodosBultosREAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RegistroEntrada re = new RegistroEntrada();
	private Map<String, Object> params;
	private String url;
	
	public Map getParams(){
		return params;
	}

	public RegistroEntrada getRe() {
		return re;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de EtiquetaTodosBultosREAction!");
		return re;
	}

	public String execute() throws Exception {
		re = new GestionRegistroEntradaHelper().getRegistroEntrada(re.getCodigoEntrada());
		Vector<Bulto> bultos = new GestionRegistroEntradaHelper().getBultosRegistroEntrada(re);
		List<Bulto> listaBultos = new ArrayList();
		long numeroBultos = bultos.get(0).getNumeroBultos();
		for (int i = 0; i < numeroBultos; i++){
			Bulto bAux = bultos.get(0);
			String registroSanitario = new GestionesEspecialesHelper().getRegistroSanitario(1);
			bAux.setDescVehiculo(registroSanitario);//En descVehiculo le paso el registro sanitario
			listaBultos.add(bAux);
		}
		re.setBultos(listaBultos);
		List lista = new ArrayList();
		lista.add(re);
		String tipoRegistro = re.getIdTipoRegistro();
		if (tipoRegistro.equals("P")){
			JasperReport reporte = (JasperReport) JRLoader.loadObject("C:/reportes/EtiqBultosProdFinPrincipal.jasper");
			setUrl("reportes/EtiqBultosProdFinPrincipal.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		}else{
			JasperReport reporte = (JasperReport) JRLoader.loadObject("C:/reportes/EtiqBultosREPrincipal.jasper");
			setUrl("reportes/EtiqBultosREPrincipal.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		}
		return SUCCESS;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}