package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class EtiqENJRAction extends ActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private GestionProduccion envasado = new GestionProduccion();
	private GestionProduccion re = new GestionProduccion();
	private String rutaReporte = "reportes/envasados/EtiqENJR.jasper";

	public GestionProduccion getRe() {
		return re;
	}

	public Object getModel() {
		// System.out.println("procesando el execute de Etiqueta Registro Entrada!");
		return envasado;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entradades en la request.
		re = new GestionEnvasadoHelper().getEtiquetaEN(envasado.getOrden());
		String reporte = "EtiqENJR";
		if (new GestionEnvasadoHelper().esFabaFresca(envasado.getOrden())){
			reporte = "EtiqENJR_fabafresca";
			setRutaReporte("reportes/envasados/EtiqENJR_fabafresca.jasper");
		}
		//generaci�n del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		"/reportes/envasados/" + reporte + ".jrxml",
    				"/reportes/envasados/" + reporte + ".jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}

	public void setRutaReporte(String rutaReporte) {
		this.rutaReporte = rutaReporte;
	}

	public String getRutaReporte() {
		return rutaReporte;
	}
}