package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ConsDetaGPENJRAction extends ActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private GestionProduccion envasado= new GestionProduccion();
	private GestionProduccion re = new GestionProduccion();

	public GestionProduccion getRe(){
		return re;
	}

	public Object getModel() {
		// System.out.println("procesando el execute de reporte registro de envasado!");
		return envasado;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entradades en la request.
		re = new GestionEnvasadoHelper().getMaestroEN(envasado.getOrden());
		List<GestionProduccion> lista = new ArrayList<GestionProduccion>(); 
        lista.add(re);
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/envasados/ConsDetaGPENJR.jasper");       
        JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		return SUCCESS;
	}
}