package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idFactura;
	private String codigoFactura;
	private String fecha;
	private String pedido;
	private Albaran albaran;
	private Double importeTotal;
	private Double descuento, valorDescuento;
	private Double ivaAplicable, valorIva;
	private Double subtotal, total;
	private Double cargosTotal;
	private String fechaVencimiento, estado, enCuotas, observaciones, esCuota;
	private List<Cargo> cargos;
	private List<CuotaFactura> cuotas;
	private String codigoAlbaran, usuarioResponsable;
	private String eanCliente;
	private String nombreCliente;
	private String nifCliente;
	private String descripcionFormaPago, descripcionEstado, descripcionDestino, telefonoCliente, lopd;
	//private List<ItemFactura> items;
	private long idCliente, idFormaPago, idDestino;
	private double cargoTran, ivaCargoTran, totalCargoTran, cargoBanc, ivaCargoBanc, totalCargoBanc,
		cargoDevo, ivaCargoDevo, totalCargoDevo;
	//private List<RegistroSalida> registrosSalida;
	private List<ItemFactura> items, graneles, itemsAgrupaciones;
	private int zonasImprimir = 0;//flag que determina si tenemos que imprimir la zona de productos, graneles, o ambos

	public Factura(){}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getCargosTotal() {
		return cargosTotal;
	}

	public void setCargosTotal(Double cargosTotal) {
		this.cargosTotal = cargosTotal;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Factura(Long idFactura, String codigoFactura, String fecha, String pedido, Albaran albaran) {
		this.idFactura = idFactura;
		this.codigoFactura = codigoFactura;
		this.fecha = fecha;
		this.pedido = pedido;
		this.albaran=albaran;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	public String getCodigoFactura() {
		return codigoFactura;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getPedido() {
		return pedido;
	}

	public void setAlbaran(Albaran albaran) {
		this.albaran = albaran;
	}

	public Albaran getAlbaran() {
		return albaran;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		return total;
	}

	public void setCodigoAlbaran(String codigoAlbaran) {
		this.codigoAlbaran = codigoAlbaran;
	}

	public String getCodigoAlbaran() {
		return codigoAlbaran;
	}

	public void setEanCliente(String eanCliente) {
		this.eanCliente = eanCliente;
	}

	public String getEanCliente() {
		return eanCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setIdCliente(long idCliente) { this.idCliente = idCliente; }
	public long getIdCliente() { return this.idCliente; }

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setIdFormaPago(long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public long getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdDestino(long idDestino) {
		this.idDestino = idDestino;
	}

	public long getIdDestino() {
		return idDestino;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setIvaAplicable(Double ivaAplicable) {
		this.ivaAplicable = ivaAplicable;
	}

	public Double getIvaAplicable() {
		return ivaAplicable;
	}

	public void setValorDescuento(Double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public Double getValorDescuento() {
		return valorDescuento;
	}

	public void setCargoTran(double cargoTran) {
		this.cargoTran = cargoTran;
	}

	public double getCargoTran() {
		return cargoTran;
	}

	public void setIvaCargoTran(double ivaCargoTran) {
		this.ivaCargoTran = ivaCargoTran;
	}

	public double getIvaCargoTran() {
		return ivaCargoTran;
	}

	public void setCargoDevo(double cargoDevo) {
		this.cargoDevo = cargoDevo;
	}

	public double getCargoDevo() {
		return cargoDevo;
	}

	public void setTotalCargoTran(double totalCargoTran) {
		this.totalCargoTran = totalCargoTran;
	}

	public double getTotalCargoTran() {
		return totalCargoTran;
	}

	public void setCargoBanc(double cargoBanc) {
		this.cargoBanc = cargoBanc;
	}

	public double getCargoBanc() {
		return cargoBanc;
	}

	public void setIvaCargoBanc(double ivaCargoBanc) {
		this.ivaCargoBanc = ivaCargoBanc;
	}

	public double getIvaCargoBanc() {
		return ivaCargoBanc;
	}

	public void setTotalCargoBanc(double totalCargoBanc) {
		this.totalCargoBanc = totalCargoBanc;
	}

	public double getTotalCargoBanc() {
		return totalCargoBanc;
	}

	public void setIvaCargoDevo(double ivaCargoDevo) {
		this.ivaCargoDevo = ivaCargoDevo;
	}

	public double getIvaCargoDevo() {
		return ivaCargoDevo;
	}

	public void setTotalCargoDevo(double totalCargoDevo) {
		this.totalCargoDevo = totalCargoDevo;
	}

	public double getTotalCargoDevo() {
		return totalCargoDevo;
	}

	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}

	public void setCuotas(List<CuotaFactura> cuotas) {
		this.cuotas = cuotas;
	}

	public List<CuotaFactura> getCuotas() {
		return cuotas;
	}

	public void setEnCuotas(String enCuotas) {
		this.enCuotas = enCuotas;
	}

	public String getEnCuotas() {
		return enCuotas;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setEsCuota(String esCuota) {
		this.esCuota = esCuota;
	}

	public String getEsCuota() {
		return esCuota;
	}

	public void setDescripcionDestino(String descripcionDestino) {
		this.descripcionDestino = descripcionDestino;
	}

	public String getDescripcionDestino() {
		return descripcionDestino;
	}

	public void setLopd(String lopd) {
		this.lopd = lopd;
	}

	public String getLopd() {
		return lopd;
	}

	public void setGraneles(List<ItemFactura> graneles) {
		this.graneles = graneles;
	}

	public List<ItemFactura> getGraneles() {
		return graneles;
	}

	public void setItemsAgrupaciones(List<ItemFactura> itemsAgrupaciones) {
		this.itemsAgrupaciones = itemsAgrupaciones;
	}

	public List<ItemFactura> getItemsAgrupaciones() {
		return itemsAgrupaciones;
	}
	
    public JRDataSource getItemsAgrupacionesDS(){
        return new JRBeanCollectionDataSource(this.itemsAgrupaciones);
    }
    
    public JRDataSource getGranelesDS(){
        return new JRBeanCollectionDataSource(this.graneles);
    }

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setZonasImprimir(int zonasImprimir) {
		this.zonasImprimir = zonasImprimir;
	}

	public int getZonasImprimir() {
		return zonasImprimir;
	}
}