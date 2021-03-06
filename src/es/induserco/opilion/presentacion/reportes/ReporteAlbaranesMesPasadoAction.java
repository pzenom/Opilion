package es.induserco.opilion.presentacion.reportes;

import java.io.File;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import java.sql.Connection;
import com.opensymphony.xwork2.ActionSupport;

public class ReporteAlbaranesMesPasadoAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String url;
	private Connection conn;
	private HashMap<String, Object> reportParams = new HashMap<String, Object>();

	public String execute() throws Exception {
		setConn(DriverManager.getConnection("jdbc:mysql://localhost:3306/tierrina", "opilion_app", "opilion_app"));
		conn.setAutoCommit(false);
		String path = request.getSession().getServletContext().getRealPath("/") + "reportes" + File.separator + "reportes" + File.separator + "ventasmespasado.jrxml";
		//System.out.println(path);
		JasperReport report = JasperCompileManager.compileReport(path);
		setUrl("/reportes/reportes/ventasmespasado.jasper");
		JasperFillManager.fillReport(report, reportParams, conn);
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) { this.request = request; }
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
	public Connection getConn() { return conn; }
	public void setConn(Connection conn){ this.conn = conn; }
}