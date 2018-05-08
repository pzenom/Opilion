package es.induserco.edifact.presentacion.orders;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsStatics;
import org.apache.commons.io.FileUtils;

public class UploadFormAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware {
  	
	private HttpServletRequest request;
    private File upload;//El archivo actual
    private String uploadContentType; //Tipo de contenido del archivo
    private String uploadFileName; //Nombre del archivo subido
    private String fileCaption;//El detalle introducido por el usuario
    private String fileSize;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Struts File Upload Action...");
		this.request = request;
	}    

    public String execute() throws IOException {
    	System.out.println("Struts File Upload Action...");
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
		File uploadDir = new File(sc.getRealPath("/WEB-INF/upload"));
        if(uploadDir.exists() == false){
        	uploadDir.mkdirs();
        }
        FileUtils.copyFile(upload, new File(uploadDir, uploadFileName));
        System.out.println("el nombre del archivo es " + uploadFileName);
        fileSize = FileUtils.byteCountToDisplaySize(upload.length());
        request.getSession().setAttribute("filename", uploadFileName);
        return SUCCESS;
   }
    
  public String getFileCaption() 				{ return fileCaption; }
  public File getUpload() 						{ return upload; }
  public String getUploadContentType() 			{ return uploadContentType; }
  public String getUploadFileName() 			{ return uploadFileName; }
  public String getFileSize() 					{ return fileSize; }

  public void setFileCaption(String fileCaption) 			 { this.fileCaption = fileCaption; }
  public void setUpload(File upload) 						 { this.upload = upload; }
  public void setUploadContentType(String uploadContentType) { this.uploadContentType = uploadContentType; }
  public void setUploadFileName(String uploadFileName) 		 { this.uploadFileName = uploadFileName; }
  public void setFileSize(String fileSize) 					 { this.fileSize = fileSize; }
}