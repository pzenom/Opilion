package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase que representa un registro de salida
 * @author Induserco, andres (09/05/2011)
 * @version 0.3
 */
public class RegistroSalida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idRegistroSalida;
	private Albaran albaran;
	private String ordenEnvasado;
	private String codigoSalida;
	private Double pesoNeto;
	private Long numeroBultos;
	private Double cantidadUnitaria, cantidad;
	private Double precioUnitario, precioKilo, precioTotal;
	private String idOperario;
	private Date fecha;
	private Long idProducto, idBulto, idDireccion;
	private Long numLinea;
	private List<Bulto> bultos;
	private long idCliente;
	private String nombreCliente;
	private String codigoEan;
	private String descripcion;
	private String codigoAlbaran;
	private String nifCliente;
	private String eanCliente;
	private String emailCliente;
	private String tfnoCliente;
	private String webCliente;
	private String lote;
	private String descripcionHueco;
	
	public Long getIdRegistroSalida() {
		return idRegistroSalida;
	}
	public void setIdRegistroSalida(Long idRegistroSalida) {
		this.idRegistroSalida = idRegistroSalida;
	}
	public Albaran getAlbaran() {
		return albaran;
	}
	public void setAlbaran(Albaran albaran) {
		this.albaran = albaran;
	}
	public String getOrdenEnvasado() {
		return ordenEnvasado;
	}

	public void setOrdenEnvasado(String ordenEnvasado) {
		this.ordenEnvasado = ordenEnvasado;
	}

	public Double getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public Long getNumeroBultos() {
		return numeroBultos;
	}

	public void setNumeroBultos(Long numeroBultos) {
		this.numeroBultos = numeroBultos;
	}

	public Double getCantidadUnitaria() {
		return cantidadUnitaria;
	}

	public void setCantidadUnitaria(Double cantidadUnitaria) {
		this.cantidadUnitaria = cantidadUnitaria;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setCodigoSalida(String codigoSalida) {
		this.codigoSalida = codigoSalida;
	}

	public String getCodigoSalida() {
		return codigoSalida;
	}

	public void setIdOperario(String idOperario) {
		this.idOperario = idOperario;
	}

	public String getIdOperario() {
		return idOperario;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setNumLinea(Long numLinea) {
		this.numLinea = numLinea;
	}

	public Long getNumLinea() {
		return numLinea;
	}

	public void setBultos(List<Bulto> bultos) {
		this.bultos = bultos;
	}

	public List<Bulto> getBultos() {
		return bultos;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}
	public String getCodigoEan() {
		return codigoEan;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setCodigoAlbaran(String codigoAlbaran) {
		this.codigoAlbaran = codigoAlbaran;
	}
	public String getCodigoAlbaran() {
		return codigoAlbaran;
	}
	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}
	public String getNifCliente() {
		return nifCliente;
	}
	public void setEanCliente(String eanCliente) {
		this.eanCliente = eanCliente;
	}
	public String getEanCliente() {
		return eanCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setTfnoCliente(String tfnoCliente) {
		this.tfnoCliente = tfnoCliente;
	}
	public String getTfnoCliente() {
		return tfnoCliente;
	}
	public void setWebCliente(String webCliente) {
		this.webCliente = webCliente;
	}
	public String getWebCliente() {
		return webCliente;
	}
	
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	public String getLote(){
		return lote;
	}
	
	public void setDescripcionHueco(String descripcionHueco) {
		this.descripcionHueco = descripcionHueco;
	}
	
	public String getDescripcionHueco() {
		return descripcionHueco;
	}
	
    public JRDataSource getBultosDS(){
        return new JRBeanCollectionDataSource(bultos);
    }
	public void setPrecioKilo(Double precioKilo) {
		this.precioKilo = precioKilo;
	}
	public Double getPrecioKilo() {
		return precioKilo;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setIdBulto(Long idBulto) {
		this.idBulto = idBulto;
	}
	public Long getIdBulto() {
		return idBulto;
	}
	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}
	public Long getIdDireccion() {
		return idDireccion;
	}
}