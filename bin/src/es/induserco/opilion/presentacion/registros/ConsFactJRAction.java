package es.induserco.opilion.presentacion.registros;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;

public class ConsFactJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Factura factura = new Factura();
	private String url;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request =request;	
	}

	public Factura getFactura(){
		return factura;
	}
	
	//@Override
	public Object getModel() {
		System.out.println("consFactJRAction!!");
		return factura;
	}
	
	public String execute() throws Exception {
		factura.setCodigoFactura(request.getParameter("codigoFactura"));
		System.out.println("Imprimir factura con id: " + factura.getCodigoFactura());
		JasperReport reporte = null;
		factura = new RegistroSalidaDataHelper().getFactura(factura.getCodigoFactura());
		List lista = new ArrayList();
		lista.add(factura);
		boolean encabezado = Boolean.parseBoolean(request.getParameter("encabezado"));
		if (encabezado){
			reporte = (JasperReport) JRLoader.loadObject("/reportes/facturas/repFact.jasper");
			setUrl("reportes/facturas/repFact.jasper");
		}else{
			reporte = (JasperReport) JRLoader.loadObject("/reportes/facturas/repFactNOEncabezado.jasper");
			setUrl("reportes/facturas/repFactNOEncabezado.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		return SUCCESS;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}