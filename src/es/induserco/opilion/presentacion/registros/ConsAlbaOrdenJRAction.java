package es.induserco.opilion.presentacion.registros;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;

public class ConsAlbaOrdenJRAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();
	private String url;
	private String codigoAlbaran;

	public String getCodigoAlbaran() {
		return codigoAlbaran;
	}

	public void setCodigoAlbaran(String codigoAlbaran) {
		this.codigoAlbaran = codigoAlbaran;
	}

	public Albaran getAlbaran() {
		return albaran;
	}

	public String execute() throws Exception {
		//System.out.println("Procesando el execute de ConsAlbaOrdenJRAction (Reporte del albarán)");
		boolean nuevaVersionAlbaran = Boolean.parseBoolean(request.getParameter("nuevo"));
		if (nuevaVersionAlbaran){
			albaran.setCodigoAlbaran(request.getParameter("codigoAlbaran"));
			boolean lineaCarrefour = Boolean.parseBoolean(request.getParameter("lineaCarrefour"));
			albaran = new RegistroSalidaDataHelper().getAlbaran(albaran.getCodigoAlbaran(), lineaCarrefour);
			if (albaran.getNombreCliente() == null || albaran.getNombreCliente().equals(""))
				albaran.setNombreCliente(albaran.getDescripcionNombreEntrega());
			List<Object> lista = new ArrayList<Object>();
			lista.add(albaran);
			JasperReport reporte = null;
			boolean precios = Boolean.parseBoolean(request.getParameter("precios"));
			boolean encabezado = Boolean.parseBoolean(request.getParameter("encabezado"));
			if (encabezado && precios){
				reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaranes/albaNuevo.jasper");
				setUrl("reportes/albaranes/albaNuevo.jasper");
			}else
				if (!encabezado && !precios){
					reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaranes/albaNOEncabezadoNOPreciosNuevo.jasper");
					setUrl("reportes/albaranes/albaNOEncabezadoNOPreciosNuevo.jasper");
				}else
					if (encabezado){
						reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaranes/albaNOPreciosNuevo.jasper");
						setUrl("reportes/albaranes/albaNOPreciosNuevo.jasper");
					}else
						if (precios){
							reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaranes/albaNOEncabezadoNuevo.jasper");
							setUrl("reportes/albaranes/albaNOEncabezadoNuevo.jasper");
						}
			JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		}else{
			albaran.setCodigoAlbaran(request.getParameter("codigoAlbaran"));
			albaran = new RegistroSalidaDataHelper().getAlbaran(albaran.getCodigoAlbaran(), false);
			List<Object> lista = new ArrayList<Object>();
			lista.add(albaran);
			JasperReport reporte = null;
			boolean precios = Boolean.parseBoolean(request.getParameter("precios"));
			boolean encabezado = Boolean.parseBoolean(request.getParameter("encabezado"));
			if (encabezado && precios){
				reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/alba.jasper");
				setUrl("reportes/alba.jasper");
			}else
				if (!encabezado && !precios){
					reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaNOEncabezadoNOPrecios.jasper");
					setUrl("reportes/albaNOEncabezadoNOPrecios.jasper");
				}else
					if (encabezado){
						reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaNOPrecios.jasper");
						setUrl("reportes/albaNOPrecios.jasper");
					}else
						if (precios){
							reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/albaNOEncabezado.jasper");
							setUrl("reportes/albaNOEncabezado.jasper");
						}
			JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		}
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) { this.request = request; }
	public Object getModel() { return albaran; }
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}