package es.induserco.opilion.presentacion.consultaGestionProduccion;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;

public class ConsGPDesgJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	private Vector<GestionProduccion> consulta = new Vector<GestionProduccion>();

	public Vector<GestionProduccion> getConsulta() {
		return consulta;
	}		

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Desgranado!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		System.out.println("fecha es"+fechaConsulta);		
		request.getSession().setAttribute("orden",(gprod.getOrden()));
		consulta = new GestionDesgranadoHelper().getRegistroDesgranados(gprod.getOrden(),fechaConsulta,gprod.getIdProducto());
		try {
            /*JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsGPDesgJR.jrxml",
            		"C:\\reportes\\ConsGPDesgJR.jasper");*/
			Connection conn = (new DatabaseConfig()).bddConexion();
			conn.setAutoCommit(false);
			//Creamos el mapa de parámetros
			Map parameters = new HashMap();
			//Obtenemos la orden del proceso de envasado
			//Añadimos la orden al mapa de parámetros
			//parameters.put("orden", orden);
			parameters.put("consulta", consulta);
			//Compilamos el reporte
			//JasperCompileManager.compileReportToFile("C:\\reportes\\ConsGPDesgJR.jrxml", "C:\\reportes\\ConsDetaGPENJR.jasper");
			JasperReport report = JasperCompileManager.compileReport("/reportes/ConsGPDesgJR.jrxml");//C:\\reportes\\
			//Preparamos la impresión del reporte
			JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
			// Exporta el informe a PDF
			String rutaReport = "";
			JasperExportManager.exportReportToPdfFile(print, "/reportes/report.pdf");
			//Para visualizar el pdf directamente desde java
			JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }			
		return (SUCCESS);
	}
}